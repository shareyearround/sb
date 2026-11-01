package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ProfileCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#profil" || msg == "#profile" || msg == "#me"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        if (!isGroup) {
            return Respon.profil.profil1
        }

        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val file = File(baseDir, "$cleanName.json")

        if (!file.exists()) {
            return Respon.profil.profil2
        }

        return try {
            val content = Const.readFile(file)
            val json = JSONObject(content)

            val id = json.optInt("id", 0)
            val level = json.optInt("level", 1)
            val exp = json.optInt("exp", 0)
            val requiredExp = level * 100

            val ep = json.optInt("ep", 0)
            val koin = json.optInt("koin", 0)
            val hp = json.optInt("hp", 20)
            val maxHp = json.optInt("max_hp", 20)

            val weapon = json.optString("weapon", "Tinju")
            val weaponAtk = json.optInt("weapon_atk", json.optInt("weapon_dmg", 1))

            val armor = json.optString("armor", "Tanpa Armor")
            val armorDef = json.optInt("armor_def", 0)

            val currentTime = System.currentTimeMillis()
            var vipTier = json.optString("vip_tier", "none").lowercase()
            val vipExpired = json.optLong("vip_expired", 0L)

            if (vipTier != "none" && vipExpired > 0L && currentTime > vipExpired) {
                vipTier = "none"
            }

            val localeId = Locale("id", "ID")
            val fullDateFormat = SimpleDateFormat("EEEE, dd MMMM yyyy | HH:mm 'WIB'", localeId)
            val expiryDateFormat = SimpleDateFormat("dd MMMM yyyy", localeId)

            val nowFormatted = fullDateFormat.format(Date())
            val expiredFormatted = if (vipExpired > 0L) expiryDateFormat.format(Date(vipExpired)) else "Tidak Aktif"

            when (vipTier) {
                "megafinal" -> {
                    Respon.profil.profil3.format(
                        nowFormatted,
                        id,
                        sender,
                        expiredFormatted,
                        level,
                        exp,
                        requiredExp,
                        ep,
                        hp,
                        maxHp,
                        koin,
                        weapon,
                        weaponAtk,
                        armor,
                        armorDef
                    )
                }
                "premium", "pluspro" -> {
                    val badge = if (vipTier == "pluspro") "🥈 VIP PLUSPRO" else "🥉 VIP PREMIUM"
                    val chatLimit = if (vipTier == "pluspro") 70 else 50

                    Respon.profil.profil4.format(
                        nowFormatted,
                        id,
                        sender,
                        badge,
                        expiredFormatted,
                        level,
                        exp,
                        requiredExp,
                        ep,
                        hp,
                        maxHp,
                        koin,
                        weapon,
                        weaponAtk,
                        armor,
                        armorDef,
                        chatLimit
                    )
                }
                else -> {
                    Respon.profil.profil5.format(
                        id,
                        sender,
                        level,
                        exp,
                        requiredExp,
                        ep,
                        hp,
                        maxHp,
                        koin,
                        weapon,
                        weaponAtk,
                        armor,
                        armorDef
                    )
                }
            }
        } catch (e: Exception) {
            Respon.profil.profil6
        }
    }
}
