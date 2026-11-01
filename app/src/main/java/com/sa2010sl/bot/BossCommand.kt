package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class BossCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#boss") || lower.startsWith("#serangbos")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val lower = message.lowercase().trim()
        return when {
            lower == "#boss" -> getBossStatus()
            lower.startsWith("#serangbos") -> handleAttack(sender)
            else -> null
        }
    }

    @Synchronized
    private fun getBossStatus(): String {
        return try {
            val bossJson = getOrSyncBossState() ?: return Respon.boss.boss1

            val isActive = bossJson.optBoolean("is_active", false)
            val currentHp = bossJson.optInt("current_hp", 0)
            val maxHp = bossJson.optInt("max_hp", 10000)
            val bossName = bossJson.optString("boss_name", "Raja Naga Hitam")

            val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            if (hour != 17 || !isActive || currentHp <= 0) {
                return Respon.boss.boss2.format(bossName)
            }

            val percent = ((currentHp.toDouble() / maxHp) * 100).toInt()
            val hpBar = generateHpBar(percent)

            Respon.boss.boss3.format(bossName, currentHp, maxHp, percent, hpBar)
        } catch (e: Exception) {
            Respon.boss.boss4
        }
    }

    @Synchronized
    private fun handleAttack(sender: String): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        if (hour != 17) {
            return Respon.boss.boss5
        }

        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val bossFile = File(baseDir, "boss_active.json")

        return try {
            val bossJson = getOrSyncBossState() ?: return Respon.boss.boss9

            val isActive = bossJson.optBoolean("is_active", false)
            val currentHp = bossJson.optInt("current_hp", 0)

            if (!isActive || currentHp <= 0) {
                return Respon.boss.boss6
            }

            val playerLevel = getPlayerLevel(cleanName)
            val baseDamage = Random.nextInt(150, 301)
            val totalDamage = baseDamage + (playerLevel * 10)

            val newHp = maxOf(0, currentHp - totalDamage)
            bossJson.put("current_hp", newHp)

            val contributors = bossJson.optJSONObject("contributors") ?: JSONObject().also {
                bossJson.put("contributors", it)
            }
            val lastPlayerDamage = contributors.optInt(cleanName, 0)
            contributors.put(cleanName, lastPlayerDamage + totalDamage)

            if (newHp <= 0) {
                bossJson.put("is_active", false)
                Const.writeFile(bossFile, bossJson.toString())

                distributeRewards(contributors)

                Respon.boss.boss7.format(totalDamage)
            } else {
                Const.writeFile(bossFile, bossJson.toString())

                val maxHp = bossJson.optInt("max_hp", 10000)
                val percent = ((newHp.toDouble() / maxHp) * 100).toInt()

                Respon.boss.boss8.format(
                    totalDamage, newHp, maxHp, percent, generateHpBar(percent)
                )
            }
        } catch (e: Exception) {
            Respon.boss.boss9
        }
    }

    @Synchronized
    private fun getOrSyncBossState(): JSONObject? {
        val baseDir = Const.getDatabaseDir(context)
        val bossFile = File(baseDir, "boss_active.json")

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val todayStr = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        var bossJson: JSONObject? = if (bossFile.exists()) {
            runCatching { JSONObject(Const.readFile(bossFile)) }.getOrNull()
        } else null

        if (hour == 17) {
            if (bossJson == null || todayStr != bossJson.optString("created_date", "")) {
                bossJson = createNewBoss(todayStr)
                Const.writeFile(bossFile, bossJson.toString())
            }
        } else {
            if (bossJson != null && bossJson.optBoolean("is_active", false)) {
                bossJson.put("is_active", false)
                Const.writeFile(bossFile, bossJson.toString())
            }
        }

        return bossJson
    }

    private fun createNewBoss(todayStr: String): JSONObject {
        return JSONObject().apply {
            put("is_active", true)
            put("boss_name", "Raja Naga Hitam")
            put("max_hp", 10000)
            put("current_hp", 10000)
            put("created_date", todayStr)
            put("contributors", JSONObject())
        }
    }

    private fun distributeRewards(contributors: JSONObject) {
        val baseDir = Const.getDatabaseDir(context)
        val keys = contributors.keys()

        while (keys.hasNext()) {
            val cleanName = keys.next()
            val userDamage = contributors.optInt(cleanName, 0)

            val userFile = File(baseDir, "$cleanName.json")
            if (userFile.exists()) {
                runCatching {
                    val userJson = JSONObject(Const.readFile(userFile))

                    val bonusKoin = userDamage + 200
                    val bonusExp = userDamage + 200

                    val currentKoin = userJson.optInt("koin", 0)
                    val currentExp = userJson.optInt("exp", 0)

                    userJson.put("koin", currentKoin + bonusKoin)
                    userJson.put("exp", currentExp + bonusExp)

                    Const.writeFile(userFile, userJson.toString())
                }
            }
        }
    }

    private fun getPlayerLevel(cleanName: String): Int {
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")
        if (userFile.exists()) {
            return runCatching {
                JSONObject(Const.readFile(userFile)).optInt("level", 1)
            }.getOrDefault(1)
        }
        return 1
    }

    private fun generateHpBar(percent: Int): String {
        val filled = percent / 10
        return buildString {
            for (i in 0 until 10) {
                if (i < filled) append("█") else append("░")
            }
        }
    }
}
