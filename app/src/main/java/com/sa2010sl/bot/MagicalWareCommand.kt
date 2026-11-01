package com.sa2010sl.bot

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.Calendar

class MagicalWareCommand constructor(
    private val context: Context? = null
) : Command {

    data class MWItem(
        val key: String,
        val name: String,
        val type: String,
        val statValue: Int,
        val price: Int,
        val tier: Int,
        val durationHours: Int
    )

    companion object {
        val CATALOG: Map<String, MWItem> = mapOf(
            "sword inz" to MWItem("sword inz", "Sword Inz", "WEAPON", 50, 3000, 1, 0),
            "sword v" to MWItem("sword v", "Sword V", "WEAPON", 80, 5500, 1, 0),
            "armor inz" to MWItem("armor inz", "Armor Inz", "ARMOR", 30, 3000, 1, 0),
            "armor v" to MWItem("armor v", "Armor V", "ARMOR", 40, 5500, 1, 0),
            "potion xp-a" to MWItem("potion xp-a", "Potion XP-A", "POTION", 50, 500, 1, 0),
            "potion xp-b" to MWItem("potion xp-b", "Potion XP-B", "POTION", 80, 800, 1, 0),
            "potion xp-c" to MWItem("potion xp-c", "Potion XP-C", "POTION", 100, 1000, 1, 0),
            "potion xp-d" to MWItem("potion xp-d", "Potion XP-D", "POTION", 120, 1200, 1, 0),
            "potion xp-v" to MWItem("potion xp-v", "Potion XP-V", "POTION", 300, 2800, 1, 0),
            "elemen api" to MWItem("elemen api", "Elemen Api", "WEAPON", 200, 12000, 2, 5),
            "inferno scythe" to MWItem("inferno scythe", "Inferno Scythe", "WEAPON", 300, 25000, 2, 10),
            "potion xp-z" to MWItem("potion xp-z", "Potion XP-Z", "POTION", 1000, 8000, 2, 0)
        )

        
        fun checkExpiredEquipment(userJson: JSONObject) {
            val now = System.currentTimeMillis()
            val weaponExpire = userJson.optLong("weapon_expire", 0L)
            if (weaponExpire > 0L && now >= weaponExpire) {
                runCatching {
                    userJson.put("weapon", "Tinju")
                    userJson.put("weapon_atk", 1)
                    userJson.put("weapon_expire", 0L)
                }
            }

            val armorExpire = userJson.optLong("armor_expire", 0L)
            if (armorExpire > 0L && now >= armorExpire) {
                runCatching {
                    userJson.put("armor", "Tanpa Armor")
                    userJson.put("armor_def", 0)
                    userJson.put("armor_expire", 0L)
                }
            }
        }
    }

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#mw") || lower.startsWith("#magicalware")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) {
            return Respon.mw.mw1
        }

        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val isOpen = hour in 21..21

        val parts = message.trim().split(Regex("\\s+"), 2)

        if (parts.size < 2) {
            if (!isOpen) {
                return Respon.mw.mw2
            }
            return getCatalogMenu()
        }

        val inputArg = parts[1].lowercase().trim()

        return try {
            val userJson = JSONObject(Const.readFile(userFile))
            checkExpiredEquipment(userJson)

            when (inputArg) {
                "iya", "ya" -> {
                    if (!isOpen) {
                        userJson.remove("pending_mw")
                        Const.writeFile(userFile, userJson.toString())
                        return Respon.mw.mw3
                    }
                    processConfirmPurchase(userFile, userJson)
                }
                "tidak", "batal" -> {
                    if (userJson.has("pending_mw")) {
                        userJson.remove("pending_mw")
                        Const.writeFile(userFile, userJson.toString())
                        return Respon.mw.mw4
                    }
                    Respon.mw.mw5
                }
                else -> {
                    if (!isOpen) {
                        return Respon.mw.mw6
                    }
                    initiatePurchase(userFile, userJson, inputArg)
                }
            }
        } catch (e: Exception) {
            Respon.mw.mw7
        }
    }

    private fun getCatalogMenu(): String = Respon.mw.mw8

    private fun initiatePurchase(userFile: File, userJson: JSONObject, itemNameKey: String): String {
        val item = CATALOG[itemNameKey] ?: return Respon.mw.mw9.format(itemNameKey)

        val currentKoin = userJson.optInt("koin", userJson.optInt("ep", 0))
        if (currentKoin < item.price) {
            return Respon.mw.mw10.format(item.price, currentKoin)
        }

        if (item.type != "POTION" && isItemAlreadyOwned(userJson, item)) {
            return Respon.mw.mw11.format(item.name)
        }

        val pending = JSONObject().apply {
            put("item_key", item.key)
            put("timestamp", System.currentTimeMillis())
        }

        userJson.put("pending_mw", pending)
        Const.writeFile(userFile, userJson.toString())

        val durasiText = if (item.durationHours > 0) Respon.mw.mw13.format(item.durationHours) else ""
        val masaAktifLine = if (item.durationHours > 0) Respon.mw.mw14.format(item.durationHours) else ""

        return Respon.mw.mw12.format(
            item.name,
            item.price,
            item.statValue,
            getStatLabel(item.type),
            masaAktifLine,
            durasiText
        )
    }

    private fun processConfirmPurchase(userFile: File, userJson: JSONObject): String {
        if (!userJson.has("pending_mw")) {
            return Respon.mw.mw15
        }

        val pending = userJson.getJSONObject("pending_mw")
        val itemKey = pending.getString("item_key")
        val item = CATALOG[itemKey]

        userJson.remove("pending_mw")

        if (item == null) {
            Const.writeFile(userFile, userJson.toString())
            return Respon.mw.mw16
        }

        val currentKoin = userJson.optInt("koin", userJson.optInt("ep", 0))
        if (currentKoin < item.price) {
            Const.writeFile(userFile, userJson.toString())
            return Respon.mw.mw17
        }

        if (item.type != "POTION" && isItemAlreadyOwned(userJson, item)) {
            Const.writeFile(userFile, userJson.toString())
            return Respon.mw.mw18
        }

        userJson.put("koin", currentKoin - item.price)

        return when (item.type) {
            "POTION" -> {
                val currentExp = userJson.optInt("exp", 0)
                userJson.put("exp", currentExp + item.statValue)
                Const.writeFile(userFile, userJson.toString())
                Respon.mw.mw19.format(item.name, item.price, item.statValue)
            }
            "WEAPON" -> {
                userJson.put("weapon", item.name)
                userJson.put("weapon_atk", item.statValue)
                if (item.durationHours > 0) {
                    val expireAt = System.currentTimeMillis() + (item.durationHours.toLong() * 3600 * 1000)
                    userJson.put("weapon_expire", expireAt)
                } else {
                    userJson.put("weapon_expire", 0L)
                }
                addToInventory(userJson, item.name)
                Const.writeFile(userFile, userJson.toString())
                val infoDurasi = if (item.durationHours > 0) Respon.mw.mw23.format(item.durationHours) else ""
                Respon.mw.mw20.format(item.name, item.statValue, infoDurasi)
            }
            "ARMOR" -> {
                userJson.put("armor", item.name)
                userJson.put("armor_def", item.statValue)
                if (item.durationHours > 0) {
                    val expireAt = System.currentTimeMillis() + (item.durationHours.toLong() * 3600 * 1000)
                    userJson.put("armor_expire", expireAt)
                } else {
                    userJson.put("armor_expire", 0L)
                }
                addToInventory(userJson, item.name)
                Const.writeFile(userFile, userJson.toString())
                val infoDurasi = if (item.durationHours > 0) Respon.mw.mw23.format(item.durationHours) else ""
                Respon.mw.mw21.format(item.name, item.statValue, infoDurasi)
            }
            else -> Respon.mw.mw22
        }
    }

    private fun isItemAlreadyOwned(userJson: JSONObject, item: MWItem): Boolean {
        val equippedWeapon = userJson.optString("weapon", "")
        val equippedArmor = userJson.optString("armor", "")

        if (item.type == "WEAPON" && equippedWeapon.equals(item.name, ignoreCase = true)) {
            return true
        }
        if (item.type == "ARMOR" && equippedArmor.equals(item.name, ignoreCase = true)) {
            return true
        }

        val inv = userJson.optJSONArray("inventori")
        if (inv != null) {
            for (i in 0 until inv.length()) {
                if (inv.optString(i).equals(item.name, ignoreCase = true)) {
                    return true
                }
            }
        }
        return false
    }

    private fun addToInventory(userJson: JSONObject, itemName: String) {
        val inv = userJson.optJSONArray("inventori") ?: JSONArray()
        var exists = false
        for (i in 0 until inv.length()) {
            if (inv.optString(i).equals(itemName, ignoreCase = true)) {
                exists = true
                break
            }
        }

        if (!exists) {
            inv.put(itemName)
            runCatching {
                userJson.put("inventori", inv)
            }
        }
    }

    private fun getStatLabel(type: String): String {
        return when (type) {
            "WEAPON" -> "DMG"
            "ARMOR" -> "% DEF"
            "POTION" -> "EXP"
            else -> ""
        }
    }
}
