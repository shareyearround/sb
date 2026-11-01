package com.sa2010sl.bot

import android.Manifest
import android.content.ContentProviderOperation
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        checkAppPermissions()

        setContent {
            BlueArchiveBotTheme {
                MainApp()
            }
        }
    }

    private fun checkAppPermissions() {
        // Akses Notifikasi
        val enabledListeners = Settings.Secure.getString(contentResolver, "enabled_notification_listeners")
        if (enabledListeners == null || !enabledListeners.contains(packageName)) {
            Toast.makeText(this, "Akses Notifikasi Diperlukan", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS))
        }

        // Izin Notifikasi & Kontak Android
        val neededPermissions = mutableListOf<String>()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                neededPermissions.add(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            neededPermissions.add(Manifest.permission.WRITE_CONTACTS)
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            neededPermissions.add(Manifest.permission.READ_CONTACTS)
        }

        if (neededPermissions.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, neededPermissions.toTypedArray(), 101)
        }
    }
}

val AppFont = FontFamily(Font(R.font.font))

val BACyan = Color(0xFF00D1FF)
val BABlueDark = Color(0xFF0A162B)
val BAGlassWhite = Color(0x33FFFFFF)
val BAGlassPanel = Color(0xCC0D1B2A)
val BAAccentYellow = Color(0xFFFFD600)

val SkewedShape = GenericShape { size, _ ->
    val skewWidth = size.height * 0.25f
    moveTo(skewWidth, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width - skewWidth, size.height)
    lineTo(0f, size.height)
    close()
}

@Composable
fun BlueArchiveBotTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = BACyan,
            secondary = BAAccentYellow,
            background = BABlueDark,
            surface = BAGlassPanel,
            onBackground = Color.White,
            onSurface = Color.White
        ),
        typography = Typography(
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(fontFamily = AppFont),
            bodyMedium = MaterialTheme.typography.bodyMedium.copy(fontFamily = AppFont),
            titleLarge = MaterialTheme.typography.titleLarge.copy(fontFamily = AppFont),
            labelLarge = MaterialTheme.typography.labelLarge.copy(fontFamily = AppFont)
        ),
        content = content
    )
}

fun Modifier.bounceClick(onClick: () -> Unit): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.92f else 1f,
        animationSpec = tween(durationMillis = 100),
        label = ""
    )

    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onClick
        )
}

enum class AppRoute { HOME, REQUESTS, USERS, SYSTEM }

@Composable
fun MainApp() {
    var currentRoute by remember { mutableStateOf(AppRoute.HOME) }
    var isOverlayLoadingVisible by remember { mutableStateOf(true) }
    var isMenuOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Crossfade(targetState = currentRoute, animationSpec = tween(400), label = "") { route ->
            when (route) {
                AppRoute.HOME -> HomeScreen(onMenuClick = { isMenuOpen = true })
                AppRoute.REQUESTS -> RequestsScreen(onMenuClick = { isMenuOpen = true })
                AppRoute.USERS -> UsersScreen(onMenuClick = { isMenuOpen = true })
                AppRoute.SYSTEM -> SystemScreen(onMenuClick = { isMenuOpen = true })
            }
        }

        RightMenuDrawer(
            isOpen = isMenuOpen,
            onClose = { isMenuOpen = false },
            onNavigate = { currentRoute = it }
        )

        if (isOverlayLoadingVisible) {
            SequentialLoadingOverlay(onFinished = { isOverlayLoadingVisible = false })
        }
    }
}

@Composable
fun SequentialLoadingOverlay(onFinished: () -> Unit) {
    val steps = listOf("INITIALIZING ENGINE...", "CONNECTING TO DB...", "LOADING MODULES...", "SCHALE OS READY")
    var currentStep by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        for (i in steps.indices) {
            delay(500)
            currentStep = i
        }
        delay(600)
        onFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BABlueDark),
        contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            for (i in 0..currentStep) {
                Box(
                    modifier = Modifier
                        .width(280.dp)
                        .height(48.dp)
                        .clip(SkewedShape)
                        .background(BACyan.copy(alpha = 0.15f))
                        .border(1.dp, BACyan, SkewedShape)
                        .padding(horizontal = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(steps[i], color = BACyan, fontSize = 14.sp, fontFamily = AppFont)
                }
            }
        }
    }
}

@Composable
fun RightMenuDrawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    onNavigate: (AppRoute) -> Unit
) {
    if (isOpen) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClose
                )
        )
    }

    AnimatedVisibility(
        visible = isOpen,
        enter = slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(300)),
        exit = slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(300)),
        modifier = Modifier.fillMaxSize()
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(300.dp)
                    .background(BABlueDark)
                    .border(1.dp, BACyan.copy(alpha = 0.3f))
                    .padding(24.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {}
                    ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text("NAVIGATION", color = BACyan, fontSize = 24.sp, fontFamily = AppFont)
                Spacer(modifier = Modifier.height(4.dp))
                BASkewButton(
                    text = "HOME",
                    subText = "DASHBOARD",
                    onClick = { onNavigate(AppRoute.HOME); onClose() }
                )
                BASkewButton(
                    text = "REQUESTS",
                    subText = "APPROVAL QUEUE",
                    onClick = { onNavigate(AppRoute.REQUESTS); onClose() }
                )
                BASkewButton(
                    text = "USERS",
                    subText = "MANAGEMENT",
                    onClick = { onNavigate(AppRoute.USERS); onClose() }
                )
                BASkewButton(
                    text = "SYSTEM",
                    subText = "CONFIG",
                    onClick = { onNavigate(AppRoute.SYSTEM); onClose() }
                )
            }
        }
    }
}

@Composable
fun TopBarBA(title: String, onMenuClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.Black.copy(alpha = 0.6f))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title, color = Color.White, fontSize = 20.sp, fontFamily = AppFont)
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(BACyan, RoundedCornerShape(8.dp))
                .bounceClick { onMenuClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun HomeScreen(onMenuClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.bg_main),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        TopBarBA(title = "SCHALE // OS", onMenuClick = onMenuClick)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.BottomStart)) {
                Text(
                    text = "SYSTEM ACTIVE",
                    fontSize = 14.sp,
                    color = BACyan,
                    fontFamily = AppFont
                )
                Text(
                    text = "BOT DASHBOARD",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontFamily = AppFont
                )
            }
        }
    }
}

// ==========================================
// HALAMAN ANTREAN PERSETUJUAN (REQUESTS SCREEN)
// ==========================================
data class QueueItem(
    val id: String,
    val nomor: String,
    val nama: String,
    val isDuplicate: Boolean
)

enum class ItemStatus { IDLE, SAVING, SUCCESS }

@Composable
fun RequestsScreen(onMenuClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var queueList by remember { mutableStateOf(listOf<QueueItem>()) }
    var isLoading by remember { mutableStateOf(true) }
    val itemStatuses = remember { mutableStateMapOf<String, ItemStatus>() }

    fun loadQueue() {
        scope.launch(Dispatchers.IO) {
            isLoading = true
            val items = mutableListOf<QueueItem>()
            val baseDir = Const.getDatabaseDir(context)

            // Baca Master DB
            val masterFile = File(baseDir, "listdb.json")
            val masterJson = if (masterFile.exists() && masterFile.readText().isNotBlank()) {
                try { JSONObject(masterFile.readText()) } catch (_: Exception) { JSONObject() }
            } else JSONObject()

            // Baca Queue
            val queueFile = File(baseDir, "pending_queue.json")
            if (queueFile.exists() && queueFile.readText().isNotBlank()) {
                try {
                    val arr = JSONArray(queueFile.readText())
                    for (i in 0 until arr.length()) {
                        val obj = arr.getJSONObject(i)
                        val id = obj.optString("id")
                        val nomor = obj.optString("nomor")
                        val nama = obj.optString("nama")
                        val isDup = masterJson.has(nomor)
                        items.add(QueueItem(id, nomor, nama, isDup))
                    }
                } catch (_: Exception) {}
            }

            withContext(Dispatchers.Main) {
                queueList = items
                isLoading = false
            }
        }
    }

    LaunchedEffect(Unit) { loadQueue() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(BABlueDark, Color.Black)))
    ) {
        TopBarBA(title = "QUEUE APPROVALS", onMenuClick = onMenuClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "PENDING SESSIONS (${queueList.size})",
                    color = BACyan,
                    fontSize = 18.sp,
                    fontFamily = AppFont
                )
                IconButton(onClick = { loadQueue() }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Refresh", tint = BACyan)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = BACyan)
                }
            } else if (queueList.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Tidak ada antrean pendaftaran baru.", color = Color.Gray, fontFamily = AppFont)
                }
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(queueList, key = { it.id }) { item ->
                        val status = itemStatuses[item.id] ?: ItemStatus.IDLE

                        BAGlassCard {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                // Tampilan Baris Tabel [Nomor, Nama]
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White.copy(alpha = 0.05f), RoundedCornerShape(4.dp))
                                        .border(1.dp, BACyan.copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                        .padding(horizontal = 12.dp, vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Column(modifier = Modifier.weight(1.2f)) {
                                        Text("NOMOR HP", fontSize = 10.sp, color = BACyan, fontFamily = AppFont)
                                        Text(item.nomor, fontSize = 14.sp, color = Color.White, fontFamily = AppFont)
                                    }
                                    Box(
                                        modifier = Modifier
                                            .height(32.dp)
                                            .width(1.dp)
                                            .background(BACyan.copy(alpha = 0.3f))
                                    )
                                    Column(modifier = Modifier.weight(1f).padding(start = 12.dp)) {
                                        Text("NAMA KARAKTER", fontSize = 10.sp, color = BACyan, fontFamily = AppFont)
                                        Text(item.nama, fontSize = 14.sp, color = Color.White, fontFamily = AppFont)
                                    }
                                }

                                // Peringatan jika nomor sudah ada di database
                                if (item.isDuplicate) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFF3B151E), RoundedCornerShape(4.dp))
                                            .border(1.dp, Color(0xFFFF1744), RoundedCornerShape(4.dp))
                                            .padding(horizontal = 10.dp, vertical = 6.dp)
                                    ) {
                                        Text(
                                            "⚠️ Nomor sudah pernah disave di database!",
                                            color = Color(0xFFFF5252),
                                            fontSize = 11.sp,
                                            fontFamily = AppFont
                                        )
                                    }
                                }

                                // Baris Tombol Aksi (DELETE, SAVE / PROGRESS / CENTANG)
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    OutlinedButton(
                                        onClick = {
                                            processDeleteRequest(context, scope, item.id) {
                                                queueList = queueList.filter { it.id != item.id }
                                            }
                                        },
                                        enabled = status != ItemStatus.SAVING && status != ItemStatus.SUCCESS,
                                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFFF5252)),
                                        border = BorderStroke(1.dp, Color(0xFFFF5252).copy(alpha = 0.6f)),
                                        shape = RoundedCornerShape(4.dp),
                                        modifier = Modifier.height(38.dp)
                                    ) {
                                        Text("DELETE", fontSize = 12.sp, fontFamily = AppFont)
                                    }

                                    Spacer(modifier = Modifier.width(10.dp))

                                    when (status) {
                                        ItemStatus.IDLE -> {
                                            Button(
                                                onClick = {
                                                    itemStatuses[item.id] = ItemStatus.SAVING
                                                    processSaveRequest(context, scope, item, { newStatus ->
                                                        itemStatuses[item.id] = newStatus
                                                    }) {
                                                        queueList = queueList.filter { it.id != item.id }
                                                        itemStatuses.remove(item.id)
                                                    }
                                                },
                                                enabled = !item.isDuplicate,
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = BACyan,
                                                    disabledContainerColor = Color.DarkGray.copy(alpha = 0.5f)
                                                ),
                                                shape = RoundedCornerShape(4.dp),
                                                modifier = Modifier.height(38.dp)
                                            ) {
                                                Text(
                                                    "SAVE",
                                                    color = if (!item.isDuplicate) Color.Black else Color.LightGray,
                                                    fontSize = 12.sp,
                                                    fontFamily = AppFont
                                                )
                                            }
                                        }
                                        ItemStatus.SAVING -> {
                                            Box(
                                                modifier = Modifier
                                                    .height(38.dp)
                                                    .width(72.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.size(22.dp),
                                                    color = BACyan,
                                                    strokeWidth = 2.5.dp
                                                )
                                            }
                                        }
                                        ItemStatus.SUCCESS -> {
                                            Box(
                                                modifier = Modifier
                                                    .height(38.dp)
                                                    .width(72.dp)
                                                    .background(Color(0xFF00E676).copy(alpha = 0.2f), RoundedCornerShape(4.dp))
                                                    .border(1.dp, Color(0xFF00E676), RoundedCornerShape(4.dp)),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                Text("✓ OK", color = Color(0xFF00E676), fontSize = 13.sp, fontFamily = AppFont)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

private fun processSaveRequest(
    context: Context,
    scope: CoroutineScope,
    item: QueueItem,
    onStatusChange: (ItemStatus) -> Unit,
    onFinished: () -> Unit
) {
    scope.launch(Dispatchers.IO) {
        // 1. Simpan ke Buku Kontak Telepon Android
        saveContactToPhone(context, item.nama, item.nomor)

        // 2. Tambah ke Master Database (listdb.json)
        val baseDir = Const.getDatabaseDir(context)
        val masterFile = File(baseDir, "listdb.json")
        val masterJson = if (masterFile.exists() && masterFile.readText().isNotBlank()) {
            try { JSONObject(masterFile.readText()) } catch (_: Exception) { JSONObject() }
        } else JSONObject()

        val dataUser = JSONObject().apply {
            put("nama", item.nama)
            put("saved_at", System.currentTimeMillis())
        }
        masterJson.put(item.nomor, dataUser)
        masterFile.writeText(masterJson.toString(), Charsets.UTF_8)

        // 3. Buang dari Antrean (pending_queue.json)
        removeFromPendingQueue(context, item.id)

        withContext(Dispatchers.Main) {
            onStatusChange(ItemStatus.SUCCESS)
            delay(650) // Jeda waktu agar centang terlihat jelas oleh owner
            onFinished()
        }
    }
}

private fun processDeleteRequest(
    context: Context,
    scope: CoroutineScope,
    itemId: String,
    onFinished: () -> Unit
) {
    scope.launch(Dispatchers.IO) {
        removeFromPendingQueue(context, itemId)
        withContext(Dispatchers.Main) {
            onFinished()
        }
    }
}

private fun removeFromPendingQueue(context: Context, itemId: String) {
    val baseDir = Const.getDatabaseDir(context)
    val queueFile = File(baseDir, "pending_queue.json")
    if (!queueFile.exists()) return
    try {
        val content = queueFile.readText().trim()
        if (content.isEmpty()) return
        val arr = JSONArray(content)
        val newArr = JSONArray()
        for (i in 0 until arr.length()) {
            val obj = arr.getJSONObject(i)
            if (obj.optString("id") != itemId) {
                newArr.put(obj)
            }
        }
        queueFile.writeText(newArr.toString(), Charsets.UTF_8)
    } catch (_: Exception) {}
}

private fun saveContactToPhone(context: Context, nama: String, nomor: String): Boolean {
    return try {
        val ops = ArrayList<ContentProviderOperation>()

        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, "com.google")
.withValue(ContactsContract.RawContacts.ACCOUNT_NAME, "syahrularmada1@gmail.com")

                .build()
        )

        // Nama Kontak
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withYieldAllowed(true)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, nama)
                .build()
        )

        // Nomor Telepon
        ops.add(
            ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                .withYieldAllowed(true)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, nomor)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                .build()
        )

        context.contentResolver.applyBatch(ContactsContract.AUTHORITY, ops)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

@Composable
fun UsersScreen(onMenuClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    
    var giftTargetId by remember { mutableStateOf("") }
    var giftAmount by remember { mutableStateOf("") }
    var selectedGiftType by remember { mutableStateOf("EXP") }
    
    var reviveTargetId by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(BABlueDark, Color.Black)))
    ) {
        TopBarBA(title = "USER MANAGEMENT", onMenuClick = onMenuClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BAGlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("SEND GIFT", color = BACyan, fontSize = 20.sp, fontFamily = AppFont)
                    OutlinedTextField(
                        value = giftTargetId,
                        onValueChange = { giftTargetId = it },
                        label = { Text("Target ID", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = giftAmount,
                        onValueChange = { giftAmount = it },
                        label = { Text("Amount", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CyberDropdown(
                        label = "Gift Type",
                        options = listOf("EXP", "Level", "Ep", "Koin"),
                        selectedOption = selectedGiftType,
                        onOptionSelected = { selectedGiftType = it }
                    )
                    Button(
                        onClick = {
                            processSendGift(context, scope, giftTargetId, giftAmount, selectedGiftType) {
                                giftTargetId = ""
                                giftAmount = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BACyan)
                    ) {
                        Text("CONFIRM TRANSFER", color = Color.Black, fontFamily = AppFont)
                    }
                }
            }

            BAGlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("USER REVIVAL", color = BAAccentYellow, fontSize = 20.sp, fontFamily = AppFont)
                    OutlinedTextField(
                        value = reviveTargetId,
                        onValueChange = { reviveTargetId = it },
                        label = { Text("Target ID", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = {
                            processReviveUser(context, scope, reviveTargetId) {
                                reviveTargetId = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BAAccentYellow)
                    ) {
                        Text("INITIATE REVIVE", color = Color.Black, fontFamily = AppFont)
                    }
                }
            }
        }
    }
}

@Composable
fun SystemScreen(onMenuClick: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val sharedPreferences = remember { context.getSharedPreferences(Const.BOT, Context.MODE_PRIVATE) }
    
    var botStatus by remember { mutableStateOf(sharedPreferences.getBoolean(Const.STATUS, false)) }
    var botSubStatus by remember { mutableStateOf(sharedPreferences.getBoolean(Const.STATUS2, false)) }

    var redeemCode by remember { mutableStateOf("") }
    var redeemDuration by remember { mutableStateOf("") }
    var selectedDurationUnit by remember { mutableStateOf("Menit") }
    var itemName by remember { mutableStateOf("") }
    var selectedItemType by remember { mutableStateOf("Weapon") }
    var itemValue by remember { mutableStateOf("") }
    var bonusKoin by remember { mutableStateOf("") }
    var bonusExp by remember { mutableStateOf("") }
    var redeemCodesList by remember { mutableStateOf(listOf<String>()) }
    var selectedDeleteCode by remember { mutableStateOf("") }

    var vipTargetId by remember { mutableStateOf("") }
    var vipDuration by remember { mutableStateOf("") }
    var selectedVipTier by remember { mutableStateOf("Premium") }

    fun reloadRedeemCodes() {
        scope.launch(Dispatchers.IO) {
            val list = mutableListOf<String>()
            val redeemFile = File(Const.getDatabaseDir(context), "RedeemCode.json")
            if (redeemFile.exists()) {
                try {
                    val content = redeemFile.readText()
                    if (content.isNotBlank()) {
                        val json = JSONObject(content)
                        val keys = json.keys()
                        while (keys.hasNext()) {
                            list.add(keys.next())
                        }
                    }
                } catch (_: Exception) {}
            }
            if (list.isEmpty()) list.add("none")
            withContext(Dispatchers.Main) {
                redeemCodesList = list
                selectedDeleteCode = list.firstOrNull() ?: ""
            }
        }
    }

    LaunchedEffect(Unit) { reloadRedeemCodes() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(listOf(BABlueDark, Color.Black)))
    ) {
        TopBarBA(title = "SYSTEM CONFIGURATION", onMenuClick = onMenuClick)

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BAGlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("ENGINE STATUS", color = BACyan, fontSize = 20.sp, fontFamily = AppFont)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Main Engine", fontFamily = AppFont, color = Color.White)
                        Switch(
                            checked = botStatus,
                            onCheckedChange = { 
                                botStatus = it
                                sharedPreferences.edit().putBoolean(Const.STATUS, it).apply()
                            }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sub Engine", fontFamily = AppFont, color = Color.White)
                        Switch(
                            checked = botSubStatus,
                            onCheckedChange = { 
                                botSubStatus = it
                                sharedPreferences.edit().putBoolean(Const.STATUS2, it).apply()
                            }
                        )
                    }
                }
            }

            BAGlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("GENERATE REDEEM CODE", color = BACyan, fontSize = 20.sp, fontFamily = AppFont)
                    OutlinedTextField(
                        value = redeemCode,
                        onValueChange = { redeemCode = it },
                        label = { Text("Code String", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = redeemDuration,
                            onValueChange = { redeemDuration = it },
                            label = { Text("Duration", fontFamily = AppFont) },
                            modifier = Modifier.weight(1f)
                        )
                        Box(modifier = Modifier.weight(1f)) {
                            CyberDropdown(
                                label = "Unit",
                                options = listOf("Menit", "Jam", "Hari"),
                                selectedOption = selectedDurationUnit,
                                onOptionSelected = { selectedDurationUnit = it }
                            )
                        }
                    }
                    OutlinedTextField(
                        value = itemName,
                        onValueChange = { itemName = it },
                        label = { Text("Item Name", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CyberDropdown(
                        label = "Item Type",
                        options = listOf("Weapon", "Armor", "Makanan", "Material"),
                        selectedOption = selectedItemType,
                        onOptionSelected = { 
                            selectedItemType = it
                            if (it.equals("Material", ignoreCase = true)) itemValue = ""
                        }
                    )
                    OutlinedTextField(
                        value = itemValue,
                        onValueChange = { itemValue = it },
                        label = { Text("Item Value", fontFamily = AppFont) },
                        enabled = !selectedItemType.equals("Material", ignoreCase = true),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedTextField(
                            value = bonusKoin,
                            onValueChange = { bonusKoin = it },
                            label = { Text("Bonus Coin", fontFamily = AppFont) },
                            modifier = Modifier.weight(1f)
                        )
                        OutlinedTextField(
                            value = bonusExp,
                            onValueChange = { bonusExp = it },
                            label = { Text("Bonus EXP", fontFamily = AppFont) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Button(
                        onClick = {
                            processAddRedeem(
                                context, scope, redeemCode, redeemDuration, selectedDurationUnit,
                                itemName, selectedItemType, itemValue, bonusKoin, bonusExp
                            ) {
                                redeemCode = ""
                                redeemDuration = ""
                                itemName = ""
                                itemValue = ""
                                bonusKoin = ""
                                bonusExp = ""
                                reloadRedeemCodes()
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BACyan)
                    ) {
                        Text("GENERATE", color = Color.Black, fontFamily = AppFont)
                    }
                }
            }
            
            BAGlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("DELETE REDEEM", color = Color(0xFFFF1744), fontSize = 20.sp, fontFamily = AppFont)
                    CyberDropdown(
                        label = "Select Code",
                        options = redeemCodesList,
                        selectedOption = selectedDeleteCode,
                        onOptionSelected = { selectedDeleteCode = it }
                    )
                    Button(
                        onClick = {
                            processDeleteRedeem(context, scope, selectedDeleteCode) {
                                reloadRedeemCodes()
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF1744))
                    ) {
                        Text("DELETE CODE", color = Color.White, fontFamily = AppFont)
                    }
                }
            }

            BAGlassCard {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text("VIP ACTIVATION", color = BAAccentYellow, fontSize = 20.sp, fontFamily = AppFont)
                    OutlinedTextField(
                        value = vipTargetId,
                        onValueChange = { vipTargetId = it },
                        label = { Text("Target ID", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = vipDuration,
                        onValueChange = { vipDuration = it },
                        label = { Text("Duration (Days)", fontFamily = AppFont) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    CyberDropdown(
                        label = "VIP Tier",
                        options = listOf("Premium", "PlusPro", "MegaFinal"),
                        selectedOption = selectedVipTier,
                        onOptionSelected = { selectedVipTier = it }
                    )
                    Button(
                        onClick = {
                            processActivateVip(context, scope, vipTargetId, vipDuration, selectedVipTier) {
                                vipTargetId = ""
                                vipDuration = ""
                            }
                        },
                        modifier = Modifier.fillMaxWidth().height(50.dp),
                        shape = RoundedCornerShape(4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BAAccentYellow)
                    ) {
                        Text("ACTIVATE VIP", color = Color.Black, fontFamily = AppFont)
                    }
                }
            }
        }
    }
}

@Composable
fun BASkewButton(text: String, subText: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .width(260.dp)
            .height(64.dp)
            .bounceClick { onClick() }
            .clip(SkewedShape)
            .background(BAGlassWhite)
            .border(1.dp, BACyan.copy(alpha = 0.8f), SkewedShape)
            .padding(horizontal = 24.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Column(modifier = Modifier.graphicsLayer {}) {
            Text(text, color = Color.White, fontSize = 16.sp, fontFamily = AppFont)
            Text(subText, color = BACyan, fontSize = 12.sp, fontFamily = AppFont)
        }
    }
}

@Composable
fun BAGlassCard(content: @Composable () -> Unit) {
    Surface(
        modifier = Modifier.border(1.dp, BACyan.copy(alpha = 0.3f), RoundedCornerShape(8.dp)),
        shape = RoundedCornerShape(8.dp),
        color = BAGlassPanel,
        shadowElevation = 8.dp
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CyberDropdown(label: String, options: List<String>, selectedOption: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedOption,
            onValueChange = {},
            readOnly = true,
            label = { Text(label, fontFamily = AppFont) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    text = { Text(selectionOption, fontFamily = AppFont) },
                    onClick = {
                        onOptionSelected(selectionOption)
                        expanded = false
                    }
                )
            }
        }
    }
}

private fun processSendGift(context: Context, scope: CoroutineScope, targetId: String, amountStr: String, giftType: String, onSuccess: () -> Unit) {
    if (targetId.isBlank() || amountStr.isBlank()) {
        Toast.makeText(context, "Data Invalid", Toast.LENGTH_SHORT).show()
        return
    }
    val amount = amountStr.toIntOrNull() ?: return
    scope.launch(Dispatchers.IO) {
        try {
            val indexFile = File(Const.getDatabaseDir(context), "IndexIdNumber.json")
            if (!indexFile.exists()) return@launch
            val indexJson = JSONObject(indexFile.readText())
            if (!indexJson.has(targetId)) return@launch
            val targetUsername = indexJson.getString(targetId)
            val cleanName = targetUsername.replace(Regex("[^a-zA-Z0-9\\s]"), "").replace(Regex("\\s+"), "_")
            val userFile = File(Const.getDatabaseDir(context), "$cleanName.json")
            if (!userFile.exists()) return@launch
            val userJson = JSONObject(userFile.readText())
            when (giftType.lowercase()) {
                "exp" -> userJson.put("exp", userJson.optInt("exp", 0) + amount)
                "level" -> userJson.put("level", userJson.optInt("level", 1) + amount)
                "ep" -> userJson.put("ep", userJson.optInt("ep", 1) + amount)
                "koin" -> userJson.put("koin", userJson.optInt("koin", 1) + amount)
            }
            userFile.writeText(userJson.toString())
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                onSuccess()
            }
        } catch (e: Exception) {}
    }
}

private fun processAddRedeem(context: Context, scope: CoroutineScope, code: String, durationStr: String, unit: String, itemName: String, itemType: String, itemValueStr: String, bonusKoinStr: String, bonusExpStr: String, onSuccess: () -> Unit) {
    if (code.isBlank() || durationStr.isBlank() || itemName.isBlank()) return
    val durationVal = durationStr.toLongOrNull() ?: return
    val durationMillis = when (unit.lowercase()) {
        "hari" -> durationVal * 24 * 60 * 60 * 1000L
        "jam" -> durationVal * 60 * 60 * 1000L
        else -> durationVal * 60 * 1000L
    }
    val expiredTime = System.currentTimeMillis() + durationMillis
    val itemValue = if (!itemType.equals("Material", ignoreCase = true) && itemValueStr.isNotBlank()) itemValueStr.toIntOrNull() ?: 0 else 0
    val bonusKoin = bonusKoinStr.toIntOrNull() ?: 0
    val bonusExp = bonusExpStr.toIntOrNull() ?: 0
    scope.launch(Dispatchers.IO) {
        try {
            val redeemFile = File(Const.getDatabaseDir(context), "RedeemCode.json")
            val redeemJson = if (redeemFile.exists() && redeemFile.readText().isNotBlank()) JSONObject(redeemFile.readText()) else JSONObject()
            val codeDetails = JSONObject().apply {
                put("expired", expiredTime)
                put("item_name", itemName)
                put("item_type", itemType)
                put("item_value", itemValue)
                put("bonus_koin", bonusKoin)
                put("bonus_exp", bonusExp)
            }
            redeemJson.put(code, codeDetails)
            redeemFile.writeText(redeemJson.toString())
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Code Added", Toast.LENGTH_SHORT).show()
                onSuccess()
            }
        } catch (e: Exception) {}
    }
}

private fun processDeleteRedeem(context: Context, scope: CoroutineScope, selectedCode: String, onSuccess: () -> Unit) {
    if (selectedCode.isBlank() || selectedCode == "none") return
    scope.launch(Dispatchers.IO) {
        try {
            val redeemFile = File(Const.getDatabaseDir(context), "RedeemCode.json")
            if (!redeemFile.exists()) return@launch
            val redeemJson = JSONObject(redeemFile.readText())
            if (redeemJson.has(selectedCode)) {
                redeemJson.remove(selectedCode)
                redeemFile.writeText(redeemJson.toString())
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show()
                    onSuccess()
                }
            }
        } catch (e: Exception) {}
    }
}

private fun processReviveUser(context: Context, scope: CoroutineScope, targetId: String, onSuccess: () -> Unit) {
    if (targetId.isBlank()) return
    scope.launch(Dispatchers.IO) {
        try {
            val indexFile = File(Const.getDatabaseDir(context), "IndexIdNumber.json")
            if (!indexFile.exists()) return@launch
            val indexJson = JSONObject(indexFile.readText())
            if (!indexJson.has(targetId)) return@launch
            val targetUsername = indexJson.getString(targetId)
            val cleanName = targetUsername.replace(Regex("[^a-zA-Z0-9\\s]"), "").replace(Regex("\\s+"), "_")
            val userFile = File(Const.getDatabaseDir(context), "$cleanName.json")
            if (!userFile.exists()) return@launch
            val userJson = JSONObject(userFile.readText())
            val maxHp = userJson.optInt("max_hp", 20)
            userJson.put("dead", false)
            userJson.put("hp", maxHp)
            userJson.put("in_battle", false)
            userJson.remove("monster_name")
            userJson.remove("monster_hp")
            userJson.remove("monster_max_hp")
            userFile.writeText(userJson.toString())
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "Revived", Toast.LENGTH_SHORT).show()
                onSuccess()
            }
        } catch (e: Exception) {}
    }
}

private fun processActivateVip(context: Context, scope: CoroutineScope, targetId: String, durationStr: String, selectedTier: String, onSuccess: () -> Unit) {
    if (targetId.isBlank() || durationStr.isBlank()) return
    val days = durationStr.toLongOrNull() ?: return
    scope.launch(Dispatchers.IO) {
        try {
            val indexFile = File(Const.getDatabaseDir(context), "IndexIdNumber.json")
            if (!indexFile.exists()) return@launch
            val indexJson = JSONObject(indexFile.readText())
            if (!indexJson.has(targetId)) return@launch
            val targetUsername = indexJson.getString(targetId)
            val cleanName = targetUsername.replace(Regex("[^a-zA-Z0-9\\s]"), "").replace(Regex("\\s+"), "_")
            val userFile = File(Const.getDatabaseDir(context), "$cleanName.json")
            if (!userFile.exists()) return@launch
            val userJson = JSONObject(userFile.readText())
            val now = System.currentTimeMillis()
            val currentExpired = userJson.optLong("vip_expired", 0)
            val baseTime = if (currentExpired > now) currentExpired else now
            val newExpired = baseTime + (days * 24L * 60 * 60 * 1000)
            val tierLower = selectedTier.lowercase()
            var maxChat = 15
            when (tierLower) {
                "megafinal" -> {
                    maxChat = 100
                    userJson.put("weapon", "Senjata Eksklusif Custom")
                    userJson.put("weapon_dmg", 100)
                    userJson.put("armor", "Armor Eksklusif Custom")
                    userJson.put("armor_def", 100)
                }
                "pluspro" -> maxChat = 70
                "premium" -> maxChat = 50
            }
            userJson.put("vip_tier", tierLower)
            userJson.put("vip_expired", newExpired)
            userJson.put("MaxChat", maxChat)
            userFile.writeText(userJson.toString())
            withContext(Dispatchers.Main) {
                Toast.makeText(context, "VIP Activated", Toast.LENGTH_SHORT).show()
                onSuccess()
            }
        } catch (e: Exception) {}
    }
}
