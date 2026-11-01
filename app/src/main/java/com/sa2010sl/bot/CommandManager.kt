package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.util.Calendar

class CommandManager(private val context: Context) {

    private val commands: List<Command> = listOf(
        MenuCommand(context),
        PingCommand(),
        ProfileCommand(context),
        SlotCommand(context),
        MulaiCommand(context),
        LevelUpCommand(context),
        TambangCommand(context),
        TebangCommand(context),
        BerburuCommand(context),
        RankCommand(context),
        JelajahCommand(context),
        SerangCommand(context),
        MakanCommand(context),
        JualCommand(context),
        InventoriCommand(context),
        BuatCommand(context),
        BeliCommand(context),
        KodeCommand(context),
        PakaiCommand(context),
        BantuanCommand(context),
        HarianCommand(context),
        BossCommand(context),
        MagicalWareCommand(context),
        TransferCommand(context),
        BankCommand(context),
        CreditCommand(),
        WarningCommand(),
        IntroCommand(),
        UpdateCommand(),
        MatematikaCommand(),
        MtkGameCommand(context),
        RampokCommand(context),
        RuntimeCommand(),
        DukungCommand(),
        VipCommand(),
        PvpCommand(context),
        LemparKoinCommand(context),
        RaidCommand(context),
        MancingCommand(context),
        FoodCommand(context),
        TriviaGameCommand(context, "#caklontong", "Cak Lontong", "caklontong.json"),
        TriviaGameCommand(context, "#siapakahaku", "Siapakah Aku", "siapakahaku.json"),
        TriviaGameCommand(context, "#tebakkalimat", "Tebak Kalimat", "tebakkalimat.json"),
        TriviaGameCommand(context, "#tebakkata", "Tebak Kata", "tebakkata.json"),
        TriviaGameCommand(context, "#tebaklirik", "Tebak Lirik", "tebaklirik.json"),
        TriviaGameCommand(context, "#tekateki", "Teka Teki", "tekateki.json"),
        TriviaGameCommand(context, "#susunkata", "Susun Kata", "susunkata.json"),
        TriviaGameCommand(context, "#asahotak", "Asah Otak", "asahotak.json"),
        TriviaGameCommand(context, "#tebakbendera", "Tebak Bendera", "tebakbendera.json"),
        TriviaGameCommand(context, "#tebakkimia", "Tebak Kimia", "tebakkimia.json"),
        TriviaGameCommand(context, "#tebaknegara", "Tebak Negara", "tebaknegara.json")
    )

    fun process(sender: String, message: String, isGroup: Boolean, groupName: String?): String? {
        val lowerMsg = message.lowercase().trim()

        if (isGroup) {
            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            val isBossTime = hour == 17

            if (isBossTime) {
                if (isBossAlive()) {
                    val isAllowed = lowerMsg.startsWith("#serangbos") ||
                            lowerMsg.startsWith("#boss") ||
                            lowerMsg.startsWith("#menu") ||
                            lowerMsg.startsWith("#bantuan") ||
                            lowerMsg.startsWith("#help") ||
                            lowerMsg.startsWith("#ping") ||
                            lowerMsg.startsWith("#mulai") ||
                            lowerMsg.startsWith("#session")

                    if (!isAllowed && lowerMsg.startsWith("#")) {
                        return Respon.commandmanager.commandmanager1 + Const.FOOTER
                    }
                }
            } else if (lowerMsg.startsWith("#serangbos")) {
                return Respon.commandmanager.commandmanager2 + Const.FOOTER
            }
        }

        if (PvpManager.isUserLocked(context, sender, message)) {
            return Respon.commandmanager.commandmanager3 + Const.FOOTER
        }

        if (isLockedCommand(lowerMsg)) {
            if (!isUserStarted(sender)) {
                return Respon.commandmanager.commandmanager4 + Const.FOOTER
            }
            checkAndCleanExpiredEquipment(sender)
            VipManager.processUserVipFile(context, sender)
            if (!lowerMsg.startsWith("#belanja") && lowerMsg != "#konsumsi") {
                val starvationResult = HungerManager.processHungerAction(context, sender)
                if (starvationResult != null) {
                    return starvationResult + Const.FOOTER
                }
            }
        }

        for (cmd in commands) {
            if (cmd.canHandle(message)) {
                val result = cmd.execute(sender, message, isGroup, groupName)
                return if (!result.isNullOrEmpty()) {
                    result + Const.FOOTER
                } else {
                    result
                }
            }
        }
        return null
    }

    private fun checkAndCleanExpiredEquipment(sender: String) {
        val cleanName = sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) return

        runCatching {
            val content = userFile.readText(Charsets.UTF_8)
            val userJson = JSONObject(content)
            val now = System.currentTimeMillis()
            var isModified = false

            val weaponExpire = userJson.optLong("weapon_expire", 0L)
            if (weaponExpire > 0L && now >= weaponExpire) {
                userJson.put("weapon", "Tinju")
                userJson.put("weapon_atk", 1)
                userJson.put("weapon_expire", 0L)
                isModified = true
            }

            val armorExpire = userJson.optLong("armor_expire", 0L)
            if (armorExpire > 0L && now >= armorExpire) {
                userJson.put("armor", "Tanpa Armor")
                userJson.put("armor_def", 0)
                userJson.put("armor_expire", 0L)
                isModified = true
            }

            if (isModified) {
                userFile.writeText(userJson.toString(), Charsets.UTF_8)
            }
        }
    }

    private fun isBossAlive(): Boolean {
        val baseDir = Const.getDatabaseDir(context)
        val bossFile = File(baseDir, "boss_active.json")

        if (!bossFile.exists()) return false

        return runCatching {
            val bossJson = JSONObject(bossFile.readText(Charsets.UTF_8))
            val isActive = bossJson.optBoolean("is_active", false)
            val currentHp = bossJson.optInt("current_hp", 0)
            isActive && currentHp > 0
        }.getOrDefault(false)
    }

    private fun isLockedCommand(message: String): Boolean {
        if (message.startsWith("#mulai") ||
            message.startsWith("#ping") ||
            message.startsWith("#session")
        ) {
            return false
        }
        return message.startsWith("#")
    }

    private fun isUserStarted(sender: String): Boolean {
        val cleanName = sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) return false

        return runCatching {
            val userJson = JSONObject(userFile.readText(Charsets.UTF_8))
            userJson.optBoolean("startuser", false)
        }.getOrDefault(false)
    }

    private fun sanitizeUsername(username: String): String {
        return username.replace(Regex("[^a-zA-Z0-9\\s]"), "").replace(Regex("\\s+"), "_")
    }
}
