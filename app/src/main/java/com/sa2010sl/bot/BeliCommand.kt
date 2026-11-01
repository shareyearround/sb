package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class BeliCommand constructor(
    private val context: Context? = null
) : Command {

    enum class ItemType {
        FOOD,
        WEAPON,
        ARMOR,
        SPECIAL
    }

    data class ShopItem(
        val key: String,
        val name: String,
        val emoji: String,
        val type: ItemType,
        val price: Int,
        val statValue: Int,
        val description: String
    )

    private val shopDatabase: Map<String, ShopItem> = mapOf(
        "sayur" to ShopItem("sayur", "Sayuran Hutan", "🥗", ItemType.FOOD, 20, 10, "+10 HP"),
        "buah" to ShopItem("buah", "Buah Segar", "🍎", ItemType.FOOD, 25, 5, "+5 HP"),
        "eliksir_naga" to ShopItem("eliksir_naga", "Eliksir Jiwa Naga", "🧪", ItemType.FOOD, 65, 50, "+50 HP (Epic)"),

        "pedang_kayu" to ShopItem("pedang_kayu", "Pedang Kayu", "🗡️", ItemType.WEAPON, 30, 3, "3 ATK"),
        "pedang_besi" to ShopItem("pedang_besi", "Pedang Besi", "⚔️", ItemType.WEAPON, 50, 5, "5 ATK"),
        "pedang_aether" to ShopItem("pedang_aether", "Pedang Aetheria", "⚡", ItemType.WEAPON, 70, 9, "9 ATK (Epic)"),

        "armor_kulit" to ShopItem("armor_kulit", "Armor Kulit", "🛡️", ItemType.ARMOR, 35, 2, "Reduksi Damage 2%"),
        "armor_besi" to ShopItem("armor_besi", "Armor Besi", "🥷", ItemType.ARMOR, 60, 3, "Reduksi Damage 3%"),
        "perisai_aegis" to ShopItem("perisai_aegis", "Zirah Pelindung Aegis", "🔱", ItemType.ARMOR, 70, 12, "Reduksi Damage 12% (Epic)"),

        "jimat_fortuna" to ShopItem("jimat_fortuna", "Jimat Dewi Fortuna", "🔮", ItemType.SPECIAL, 65, 100, "+100 Instant EXP (Epic)")
    )

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#beli" || msg.startsWith("#beli ") || msg == "#toko" || msg == "#shop"
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
            return Respon.beli.beli1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val input = message.trim()
            val initialParts = input.split(Regex("\\s+"))

            if (initialParts.size < 2) {
                val koin = userJson.optInt("koin", 0)
                return Respon.beli.beli2.format(
                    koin,
                    getShopCategoryString(ItemType.FOOD),
                    getShopCategoryString(ItemType.WEAPON),
                    getShopCategoryString(ItemType.ARMOR),
                    getShopCategoryString(ItemType.SPECIAL)
                )
            }

            val rawArgs = input.substring(initialParts[0].length).trim()
            val tokens = rawArgs.split(Regex("\\s+"))

            var amount = 1
            val targetKey: String

            if (tokens.size > 1) {
                val lastNum = tokens.last().toIntOrNull()
                if (lastNum != null && lastNum > 0) {
                    amount = lastNum
                    targetKey = tokens.dropLast(1).joinToString("_").lowercase()
                } else {
                    targetKey = rawArgs.lowercase().replace(" ", "_")
                }
            } else {
                targetKey = rawArgs.lowercase().replace(" ", "_")
            }

            var item = shopDatabase[targetKey]
            if (item == null) {
                item = shopDatabase.values.firstOrNull {
                    it.key.equals(targetKey, ignoreCase = true) ||
                            it.name.lowercase().replace(" ", "_") == targetKey
                }
            }

            if (item == null) {
                return Respon.beli.beli4.format(targetKey)
            }

            val totalPrice = item.price * amount
            val userKoin = userJson.optInt("koin", 0)

            if (userKoin < totalPrice) {
                return Respon.beli.beli5.format(amount, item.name, totalPrice, userKoin)
            }

            userJson.put("koin", userKoin - totalPrice)
            val remainingKoin = userJson.optInt("koin", 0)
            val amountStr = if (amount > 1) " ×$amount" else ""
            var effectMessage = ""

            when (item.type) {
                ItemType.FOOD -> {
                    val currentQty = userJson.optInt(item.key, 0)
                    userJson.put(item.key, currentQty + amount)
                    effectMessage = Respon.beli.beli7
                }
                ItemType.WEAPON -> {
                    val currentAtk = userJson.optInt("weapon_atk", 1)
                    val currentWpn = userJson.optString("weapon", "Tinju")

                    if (item.statValue > currentAtk) {
                        userJson.put("weapon", item.name)
                        userJson.put("weapon_atk", item.statValue)
                        effectMessage = Respon.beli.beli8.format(item.name, item.statValue, currentWpn)
                    } else {
                        val currentQty = userJson.optInt(item.key, 0)
                        userJson.put(item.key, currentQty + amount)
                        effectMessage = Respon.beli.beli9
                    }
                }
                ItemType.ARMOR -> {
                    val currentDef = userJson.optInt("armor_def", 0)
                    val currentArmor = userJson.optString("armor", "Tanpa Armor")

                    if (item.statValue > currentDef) {
                        userJson.put("armor", item.name)
                        userJson.put("armor_def", item.statValue)
                        effectMessage = Respon.beli.beli10.format(item.name, currentArmor, item.statValue)
                    } else {
                        val currentQty = userJson.optInt(item.key, 0)
                        userJson.put(item.key, currentQty + amount)
                        effectMessage = Respon.beli.beli11
                    }
                }
                ItemType.SPECIAL -> {
                    if (item.key == "jimat_fortuna") {
                        val currentExp = userJson.optInt("exp", 0) + (item.statValue * amount)
                        userJson.put("exp", currentExp)
                        effectMessage = Respon.beli.beli12.format(item.statValue * amount)
                    } else {
                        val currentQty = userJson.optInt(item.key, 0)
                        userJson.put(item.key, currentQty + amount)
                        effectMessage = Respon.beli.beli7
                    }
                }
            }

            Const.writeFile(userFile, userJson.toString())

            Respon.beli.beli6.format(
                item.emoji, item.name, amountStr, totalPrice, remainingKoin, effectMessage
            )
        } catch (e: Exception) {
            Respon.beli.beli13
        }
    }

    private fun getShopCategoryString(type: ItemType): String {
        return shopDatabase.values
            .filter { it.type == type }
            .joinToString("\n") { item ->
                Respon.beli.beli3.format(item.key, item.name, item.price, item.description)
            }
    }
}
