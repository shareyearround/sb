package com.sa2010sl.bot

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ServiceInfo
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.ContactsContract
import android.provider.Settings
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.Executors
import org.json.JSONArray
import org.json.JSONObject

class NotificationReceiver : NotificationListenerService() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var commandManager: CommandManager
    private var wakeLock: PowerManager.WakeLock? = null
    private val executorService = Executors.newCachedThreadPool()

    companion object {
        var isRunning: Boolean = false
        private const val CHANNEL_ID = "syahrularmadatech_bot_channel"
        private const val NOTIFICATION_ID = 1001
        private val userCache = ConcurrentHashMap<String, Long>()
    }

    override fun onCreate() {
        super.onCreate()
        isRunning = true
        sharedPreferences = getSharedPreferences(Const.BOT, Context.MODE_PRIVATE)
        commandManager = CommandManager(this)
        initWakeLock()
        startForegroundServiceNotification()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        if (sbn == null || sbn.isOngoing) return

        val pkg = sbn.packageName ?: return
        val isMainPkg = pkg == Const.PKG && isMainOn()
        val isSubPkg = pkg == Const.PKG2 && isSubOn()

        if (!isMainPkg && !isSubPkg) return

        val enabledListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        if (enabledListeners == null || !enabledListeners.contains(packageName)) return

        executorService.execute {
            try {
                val currentNotification = sbn.notification ?: return@execute
                val currentBundle = currentNotification.extras ?: return@execute
                val currentRemoteInputs = getRemoteInputs(currentNotification)

                if (currentRemoteInputs.isEmpty()) return@execute

                val isGroupConversation = currentBundle.get("android.isGroupConversation") as? Boolean ?: false
                val conversationTitle = currentBundle.getString("android.conversationTitle")
                val titleObj = currentBundle.get("android.title")
                val textObj = currentBundle.get("android.text")

                if (titleObj != null && textObj != null) {
                    val messageText = textObj.toString()
                    val isGroup = isGroupConversation && !conversationTitle.isNullOrEmpty()

                    // ==========================================
                    // 1. PENANGANAN PESAN PRIBADI (JAPRI)
                    // ==========================================
                    if (!isGroup) {
                        if (isMainPkg) {
                            handlePrivateMessage(
                                messageText,
                                currentNotification,
                                currentBundle,
                                currentRemoteInputs
                            )
                        }
                        return@execute
                    }

                    // ==========================================
                    // 2. PENANGANAN PESAN GRUP
                    // ==========================================
                    val titleSplit = parseTitle(titleObj.toString())
                    val groupName = titleSplit.firstOrNull() ?: ""

                    // 1. Ekstrak nomor HP asli pengirim via URI Kontak WhatsApp
                    val senderPhone = getSenderPhoneNumberFromNotification(currentNotification)

                    // 2. Dapatkan nama karakter resmi dari listdb.json
                    val officialCharacterName = if (senderPhone != null) getRegisteredNameByPhone(senderPhone) else null

                    // 3. Verifikasi Keamanan: Cegat jika belum disetujui / nomor palsu
                    if (officialCharacterName == null) {
                        val trimmedMsg = messageText.trim()
                        if (trimmedMsg.startsWith("#")) {
                            sendMsg(
                                "Akses Ditolak! Nomor WhatsApp Anda belum terdaftar / disetujui oleh Owner. Silakan chat bot secara pribadi (Japri) untuk mendaftar:\n#session <nomor_hp> <nama>",
                                currentNotification,
                                currentBundle,
                                currentRemoteInputs
                            )
                        }
                        return@execute // Hentikan eksekusi, peniru tidak bisa memproses data siapapun
                    }

                    val lowerMsg = messageText.lowercase().trim()

                    if (isMainPkg) {
                        if (lowerMsg.startsWith("#sess")) {
                            sendMsg(
                                "Akun Anda sudah aktif dan terverifikasi di database sebagai '$officialCharacterName'!",
                                currentNotification,
                                currentBundle,
                                currentRemoteInputs
                            )
                        } else {
                            // Oper officialCharacterName sebagai 'sender', bukan nama profil WhatsApp
                            val reply = commandManager.process(officialCharacterName, messageText, true, groupName)
                            if (reply != null) {
                                sendMsg(reply, currentNotification, currentBundle, currentRemoteInputs)
                            }
                        }
                    } else if (isSubPkg) {
                        val reply = handleSubBotCommand(officialCharacterName, messageText)
                        if (reply != null) {
                            sendMsg(reply, currentNotification, currentBundle, currentRemoteInputs)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(Const.LOG, "Error processing notification: ${e.localizedMessage}")
            }
        }
    }

    // ==========================================
    // RESOLVER NOMOR TELEPON DARI NOTIFIKASI
    // ==========================================
    private fun getSenderPhoneNumberFromNotification(notification: Notification): String? {
        val extras = notification.extras ?: return null

        // Coba lewat MessagingStyle
        try {
            val messagingStyle = NotificationCompat.MessagingStyle.extractMessagingStyleFromNotification(notification)
            val lastPerson = messagingStyle?.messages?.lastOrNull()?.person
            val uriString = lastPerson?.uri
            if (!uriString.isNullOrEmpty()) {
                if (uriString.startsWith("tel:")) {
                    return normalisasiNomor(uriString.substring(4))
                }
                val phone = resolvePhoneNumber(Uri.parse(uriString))
                if (phone != null) return phone
            }
        } catch (_: Exception) {}

        // Coba lewat raw bundle Notification.EXTRA_MESSAGES (Android P+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            try {
                val messages = extras.getParcelableArray(Notification.EXTRA_MESSAGES)
                if (messages != null && messages.isNotEmpty()) {
                    val lastMsg = messages.last() as? Bundle
                    val person = lastMsg?.getParcelable<android.app.Person>("sender_person")
                    val uriString = person?.uri
                    if (!uriString.isNullOrEmpty()) {
                        if (uriString.startsWith("tel:")) {
                            return normalisasiNomor(uriString.substring(4))
                        }
                        val phone = resolvePhoneNumber(Uri.parse(uriString))
                        if (phone != null) return phone
                    }
                }
            } catch (_: Exception) {}
        }

        // Coba lewat Notification.EXTRA_PEOPLE_LIST
        try {
            val people = extras.getParcelableArrayList<android.app.Person>(Notification.EXTRA_PEOPLE_LIST)
            if (!people.isNullOrEmpty()) {
                val uriString = people[0].uri
                if (!uriString.isNullOrEmpty()) {
                    if (uriString.startsWith("tel:")) {
                        return normalisasiNomor(uriString.substring(4))
                    }
                    return resolvePhoneNumber(Uri.parse(uriString))
                }
            }
        } catch (_: Exception) {}

        return null
    }

    private fun resolvePhoneNumber(contactUri: Uri): String? {
        var phoneNumber: String? = null
        try {
            // Coba query langsung kolom Phone.NUMBER
            val directCursor = contentResolver.query(
                contactUri,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                null, null, null
            )
            directCursor?.use {
                if (it.moveToFirst()) {
                    val idx = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                    if (idx != -1) {
                        val raw = it.getString(idx)
                        if (!raw.isNullOrEmpty()) {
                            phoneNumber = normalisasiNomor(raw)
                            return phoneNumber
                        }
                    }
                }
            }

            // Jika bukan direct URI, cari Contact ID lalu query ke ContactsContract.Phone
            val cursor = contentResolver.query(
                contactUri,
                arrayOf(ContactsContract.Contacts._ID),
                null, null, null
            )
            cursor?.use {
                if (it.moveToFirst()) {
                    val contactId = it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    val phoneCursor = contentResolver.query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
                        "${ContactsContract.CommonDataKinds.Phone.CONTACT_ID} = ?",
                        arrayOf(contactId),
                        null
                    )
                    phoneCursor?.use { pCur ->
                        if (pCur.moveToFirst()) {
                            val raw = pCur.getString(pCur.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
                            phoneNumber = normalisasiNomor(raw)
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e(Const.LOG, "Gagal query kontak: ${e.message}")
        }
        return phoneNumber
    }

    // ==========================================
    // LOGIKA PERMINTAAN SESI DARI CHAT PRIBADI
    // ==========================================
    private fun handlePrivateMessage(
        messageText: String,
        notification: Notification,
        bundle: Bundle,
        remoteInputs: ArrayList<RemoteInput>
    ) {
        val trimmed = messageText.trim()
        val lower = trimmed.lowercase()

        if (lower.startsWith("#session") || lower.startsWith("#sesi")) {
            val parts = trimmed.split(Regex("\\s+"))
            if (parts.size < 3) {
                sendMsg(
                    "Format salah! Ketik:\n#session <nomor_hp> <nama>\nContoh:\n#session 081234567890 Nama",
                    notification,
                    bundle,
                    remoteInputs
                )
                return
            }

            val rawNomor = parts[1]
            val namaKarakter = parts.subList(2, parts.size).joinToString(" ").trim()
            val cleanNomor = normalisasiNomor(rawNomor)

            // 1. Validasi Format Nomor Indonesia
            if (!cleanNomor.matches(Regex("^628[0-9]{8,12}$"))) {
                sendMsg(
                    "Format nomor tidak valid! Gunakan format nomor telepon Indonesia yang benar (contoh: 081234567890 atau 6281234567890).",
                    notification,
                    bundle,
                    remoteInputs
                )
                return
            }

            // 2. Validasi Nama Karakter
            if (namaKarakter.length < 2 || namaKarakter.length > 20) {
                sendMsg(
                    "Nama karakter harus antara 2 hingga 20 karakter!",
                    notification,
                    bundle,
                    remoteInputs
                )
                return
            }

            // 3. Cek apakah nomor sudah ada di Database Master
            if (isNumberInMasterDb(cleanNomor)) {
                sendMsg(
                    "Nomor $cleanNomor sudah terdaftar dalam sistem dan pernah disimpan sebelumnya!",
                    notification,
                    bundle,
                    remoteInputs
                )
                return
            }

            // 4. Cek apakah nama karakter sudah dipakai oleh user lain di database
            if (isCharacterNameTaken(namaKarakter)) {
                sendMsg(
                    "Nama '$namaKarakter' sudah dipakai pemain lain. Silakan pilih nama karakter lain agar database tidak bentrok!",
                    notification,
                    bundle,
                    remoteInputs
                )
                return
            }

            // 5. Cek apakah nomor sudah ada di Antrean Pending
            if (isNumberInPendingQueue(cleanNomor)) {
                sendMsg(
                    "Nomor $cleanNomor saat ini sudah ada di antrean persetujuan Owner. Mohon menunggu konfirmasi!",
                    notification,
                    bundle,
                    remoteInputs
                )
                return
            }

            // 6. Masukkan ke Antrean Approval Owner
            val success = addToPendingQueue(cleanNomor, namaKarakter)
            if (success) {
                sendMsg(
                    "Permintaan sesi untuk '$namaKarakter' ($cleanNomor) telah masuk ke antrean persetujuan Owner. Silakan tunggu konfirmasi!",
                    notification,
                    bundle,
                    remoteInputs
                )
            } else {
                sendMsg(
                    "Terjadi kesalahan saat memproses antrean pendaftaran. Silakan coba beberapa saat lagi.",
                    notification,
                    bundle,
                    remoteInputs
                )
            }
        }
    }

    private fun normalisasiNomor(raw: String): String {
        var clean = raw.replace(Regex("[^0-9]"), "")
        if (clean.startsWith("0")) {
            clean = "62" + clean.substring(1)
        }
        return clean
    }

    @Synchronized
    private fun getRegisteredNameByPhone(nomor: String): String? {
        val file = File(Const.getDatabaseDir(this), "listdb.json")
        if (!file.exists()) return null
        return try {
            val content = file.readText(Charsets.UTF_8).trim()
            if (content.isEmpty()) return null
            val json = JSONObject(content)
            if (json.has(nomor)) {
                val userObj = json.getJSONObject(nomor)
                userObj.optString("nama", null)
            } else null
        } catch (_: Exception) {
            null
        }
    }

    @Synchronized
    private fun isCharacterNameTaken(namaKarakter: String): Boolean {
        val file = File(Const.getDatabaseDir(this), "listdb.json")
        if (!file.exists()) return false
        return try {
            val content = file.readText(Charsets.UTF_8).trim()
            if (content.isEmpty()) return false
            val json = JSONObject(content)
            val keys = json.keys()
            while (keys.hasNext()) {
                val no = keys.next()
                val userObj = json.getJSONObject(no)
                if (userObj.optString("nama").equals(namaKarakter, ignoreCase = true)) {
                    return true
                }
            }
            false
        } catch (_: Exception) {
            false
        }
    }

    @Synchronized
    private fun isNumberInMasterDb(nomor: String): Boolean {
        val file = File(Const.getDatabaseDir(this), "listdb.json")
        if (!file.exists()) return false
        return try {
            val content = file.readText(Charsets.UTF_8).trim()
            if (content.isEmpty()) return false
            val json = JSONObject(content)
            json.has(nomor)
        } catch (_: Exception) {
            false
        }
    }

    @Synchronized
    private fun isNumberInPendingQueue(nomor: String): Boolean {
        val file = File(Const.getDatabaseDir(this), "pending_queue.json")
        if (!file.exists()) return false
        return try {
            val content = file.readText(Charsets.UTF_8).trim()
            if (content.isEmpty()) return false
            val arr = JSONArray(content)
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                if (obj.optString("nomor") == nomor) return true
            }
            false
        } catch (_: Exception) {
            false
        }
    }

    @Synchronized
    private fun addToPendingQueue(nomor: String, nama: String): Boolean {
        val file = File(Const.getDatabaseDir(this), "pending_queue.json")
        return try {
            val arr = if (file.exists() && file.readText(Charsets.UTF_8).isNotBlank()) {
                JSONArray(file.readText(Charsets.UTF_8))
            } else {
                JSONArray()
            }

            val newObj = JSONObject().apply {
                put("id", System.currentTimeMillis().toString())
                put("nomor", nomor)
                put("nama", nama)
                put("timestamp", System.currentTimeMillis())
            }

            arr.put(newObj)
            file.writeText(arr.toString(), Charsets.UTF_8)
            true
        } catch (e: Exception) {
            Log.e(Const.LOG, "Gagal simpan ke pending queue: ${e.message}")
            false
        }
    }

    private fun handleSubBotCommand(sender: String, message: String): String? {
        val lower = message.lowercase().trim()
        if (lower == "#membangkitkan" || lower == "#revive" || lower == "#hidup") {
            val userFile = getUserFile(sender)
            if (!userFile.exists()) {
                return "Profil Anda belum terdaftar! Silakan hubungi bot via chat pribadi untuk pendaftaran."
            }
            try {
                val content = readFileToString(userFile)
                val userJson = JSONObject(content)

                val isDead = userJson.optBoolean("dead", false)
                val currentHp = userJson.optInt("hp", 0)
                val maxHp = userJson.optInt("max_hp", 20)

                if (!isDead && currentHp > 0) {
                    return "Karakter Anda ($sender) masih hidup dengan HP $currentHp/$maxHp."
                }

                userJson.apply {
                    put("dead", false)
                    put("hp", maxHp)
                    put("in_battle", false)
                    remove("monster_name")
                    remove("monster_hp")
                    remove("monster_max_hp")
                }

                writeStringToFile(userFile, userJson.toString())
                return "@$sender berhasil dibangkitkan kembali dengan HP penuh ($maxHp/$maxHp)!"

            } catch (e: Exception) {
                return "Gagal memproses kebangkitan karakter Anda."
            }
        }
        return null
    }

    private fun initWakeLock() {
        val powerManager = getSystemService(Context.POWER_SERVICE) as? PowerManager
        powerManager?.let {
            wakeLock = it.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "Sa2010sl::NotificationWakeLock").apply {
                setReferenceCounted(false)
                if (!isHeld) acquire(10 * 60 * 1000L)
            }
        }
    }

    private fun startForegroundServiceNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "SL Bot Notification Listener",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Bot Service Operational")
            .setContentText("Mendengarkan Notifikasi WhatsApp")
            .setSmallIcon(R.drawable.app_icon)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(NOTIFICATION_ID, notification, ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC)
        } else {
            startForeground(NOTIFICATION_ID, notification)
        }
    }

    override fun onDestroy() {
        isRunning = false
        wakeLock?.let {
            if (it.isHeld) it.release()
        }
        wakeLock = null
        executorService.shutdown()
        super.onDestroy()
    }

    private fun isMainOn(): Boolean = sharedPreferences.getBoolean(Const.STATUS, false)
    private fun isSubOn(): Boolean = sharedPreferences.getBoolean(Const.STATUS2, false)

    private fun getRemoteInputs(notification: Notification): ArrayList<RemoteInput> {
        val remoteInputs = ArrayList<RemoteInput>()
        val wearableExtender = NotificationCompat.WearableExtender(notification)
        for (act in wearableExtender.actions) {
            val inputs = act?.remoteInputs
            if (inputs != null) {
                remoteInputs.addAll(inputs)
            }
        }
        return remoteInputs
    }

    private fun sendMsg(
        msg: String,
        notification: Notification,
        bundle: Bundle,
        remoteInputs: ArrayList<RemoteInput>
    ) {
        if (remoteInputs.isEmpty()) return
        val allRemoteInputs = remoteInputs.toTypedArray()
        val localIntent = Intent().apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        for (input in allRemoteInputs) {
            bundle.putCharSequence(input.resultKey, msg)
        }

        RemoteInput.addResultsToIntent(allRemoteInputs, localIntent, bundle)
        try {
            val act = replyAction(notification)
            act?.actionIntent?.send(this, 0, localIntent)
        } catch (e: PendingIntent.CanceledException) {
            Log.e(Const.LOG, "Gagal mengirim balasan notifikasi: ${e.localizedMessage}")
        }
    }

    private fun replyAction(notification: Notification): NotificationCompat.Action? {
        val wearableActions = NotificationCompat.WearableExtender(notification).actions
        for (action in wearableActions) {
            if (isAllowFreeFormInput(action)) return action
        }
        if (notification.actions != null) {
            for (i in 0 until NotificationCompat.getActionCount(notification)) {
                val action = NotificationCompat.getAction(notification, i)
                if (action != null && isAllowFreeFormInput(action)) return action
            }
        }
        return null
    }

    private fun isAllowFreeFormInput(action: NotificationCompat.Action): Boolean {
        val inputs = action.remoteInputs ?: return false
        return inputs.any { it.allowFreeFormInput }
    }

    private fun parseTitle(title: String): Array<String> {
        val titleSplit = title.split(Regex("[^\\s\\w\\d]"))
        return titleSplit.filter { it.isNotBlank() }.map { it.trim() }.toTypedArray()
    }

    private fun sanitizeUsername(username: String): String {
        return username.replace(Regex("[^a-zA-Z0-9\\s]"), "").replace(Regex("\\s+"), "_")
    }

    private fun getUserFile(username: String): File {
        val cleanName = sanitizeUsername(username)
        val baseDir = Const.getDatabaseDir(this)
        return File(baseDir, "$cleanName.json")
    }

    private fun readFileToString(file: File): String {
        return file.readText(Charsets.UTF_8)
    }

    private fun writeStringToFile(file: File, data: String) {
        file.writeText(data, Charsets.UTF_8)
    }
}
