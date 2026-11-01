package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class InventoriCommand constructor(
    private val context: Context? = null
) : Command {

    private enum class Category {
        FOOD,
        WEAPON,
        ARMOR,
        MATERIAL
    }

    private data class ItemMeta(
        val name: String,
        val emoji: String,
        val category: Category,
        val info: String = ""
    )

    private val itemRegistry: Map<String, ItemMeta> = mapOf(
        "buah" to ItemMeta("Buah Segar", "🍎", Category.FOOD, "+5 HP"),
        "sayur" to ItemMeta("Sayuran Hutan", "🥗", Category.FOOD, "+10 HP"),
        "madu" to ItemMeta("Madu Hutan", "🍯", Category.FOOD, "+25 HP"),
        "kayu" to ItemMeta("Kayu Unik", "🪵", Category.MATERIAL),
        "tembaga" to ItemMeta("Bijih Tembaga", "🪨", Category.MATERIAL),
        "zamrud" to ItemMeta("Batu Zamrud", "❇️", Category.MATERIAL),
        "berlian" to ItemMeta("Berlian Murni", "💎", Category.MATERIAL),
        "burung" to ItemMeta("Daging Burung", "🕊️", Category.MATERIAL),
        "ular" to ItemMeta("Daging Ular", "🐍", Category.MATERIAL),
        "babi_hutan" to ItemMeta("Daging Babi Hutan", "🐗", Category.MATERIAL),
        "slime" to ItemMeta("Slime Jelly", "🟢", Category.MATERIAL),
        "beruang" to ItemMeta("Daging Beruang", "🐻", Category.MATERIAL),
        "pisau_berburu" to ItemMeta("Pisau Berburu", "🗡️", Category.WEAPON, "3 ATK"),
        "pedang_besi" to ItemMeta("Pedang Besi", "⚔️", Category.WEAPON, "5 ATK"),
        "tombak_emas" to ItemMeta("Tombak Emas", "🔱", Category.WEAPON, "8 ATK")
    )

    private val blacklistedKeys = setOf(
        "name", "id", "password", "last_login", "level", "koin", "exp", "ep",
        "hp", "max_hp", "weapon", "weapon_atk", "armor", "armor_def",
        "in_battle", "inventory", "redeemed_codes"
    )

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#inventori" || msg == "#tas" || msg == "#inv"
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
            return Respon.inventori.inventori1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val activeWeapon = userJson.optString("weapon", "Tinju")
            val activeWeaponAtk = userJson.optInt("weapon_atk", 1)
            val activeArmor = userJson.optString("armor", "Tanpa Armor")
            val activeArmorDef = userJson.optInt("armor_def", 0)

            val foodList = mutableListOf<String>()
            val weaponList = mutableListOf<String>()
            val armorList = mutableListOf<String>()
            val materialList = mutableListOf<String>()

            weaponList.add(Respon.inventori.inventori2.format(activeWeapon, activeWeaponAtk))
            armorList.add(Respon.inventori.inventori3.format(activeArmor, activeArmorDef))

            val inventoryArray = userJson.optJSONArray("inventory")
            if (inventoryArray != null && inventoryArray.length() > 0) {
                val itemCounts = mutableMapOf<String, Int>()
                val itemDataMap = mutableMapOf<String, JSONObject>()

                for (i in 0 until inventoryArray.length()) {
                    val item = inventoryArray.getJSONObject(i)
                    val name = item.optString("name", "Unknown")
                    itemCounts[name] = (itemCounts[name] ?: 0) + 1
                    itemDataMap[name] = item
                }

                for ((itemName, count) in itemCounts) {
                    val itemObj = itemDataMap[itemName] ?: continue
                    val type = itemObj.optString("type", "")
                    val value = itemObj.optInt("value", 0)
                    val countStr = if (count > 1) " ×$count" else ""

                    when {
                        type.equals("Weapon", ignoreCase = true) -> {
                            weaponList.add(Respon.inventori.inventori4.format(itemName, value, countStr))
                        }
                        type.equals("Armor", ignoreCase = true) -> {
                            armorList.add(Respon.inventori.inventori5.format(itemName, value, countStr))
                        }
                        type.equals("Makanan", ignoreCase = true) || type.equals("Food", ignoreCase = true) -> {
                            foodList.add(Respon.inventori.inventori6.format(itemName, value, countStr))
                        }
                        else -> {
                            materialList.add(Respon.inventori.inventori7.format(itemName, countStr))
                        }
                    }
                }
            }

            val keys = userJson.keys()
            while (keys.hasNext()) {
                val key = keys.next()

                if (isBlacklisted(key)) continue

                val count = userJson.optInt(key, 0)
                if (count <= 0) continue

                val meta = itemRegistry[key]
                val displayName = meta?.name ?: formatItemName(key)
                val emoji = meta?.emoji ?: "📦"
                val info = if (!meta?.info.isNullOrEmpty()) " (${meta?.info})" else ""
                val category = meta?.category ?: Category.MATERIAL

                val entry = Respon.inventori.inventori8.format(emoji, displayName, count, info)

                when (category) {
                    Category.FOOD -> foodList.add(entry)
                    Category.WEAPON -> weaponList.add(entry)
                    Category.ARMOR -> armorList.add(entry)
                    Category.MATERIAL -> materialList.add(entry)
                }
            }

            Respon.inventori.inventori10.format(
                sender,
                buildListString(weaponList),
                buildListString(armorList),
                buildListString(foodList),
                buildListString(materialList)
            )
        } catch (e: Exception) {
            Respon.inventori.inventori11
        }
    }

    private fun buildListString(list: List<String>): String {
        if (list.isEmpty()) {
            return Respon.inventori.inventori9
        }
        return list.joinToString("\n") { "• $it" }
    }

    private fun isBlacklisted(key: String): Boolean {
        val k = key.lowercase()
        return k in blacklistedKeys || k.startsWith("last_login") || k.startsWith("last_relog") || k.startsWith("monster_")
    }

    private fun formatItemName(str: String): String {
        return str.split("_").joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
}
