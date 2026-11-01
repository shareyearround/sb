package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class BuatCommand constructor(
    private val context: Context? = null
) : Command {

    enum class ItemType {
        WEAPON,
        ARMOR
    }

    data class Ingredient(
        val itemKey: String,
        val itemName: String,
        val amount: Int
    )

    data class Recipe(
        val key: String,
        val name: String,
        val emoji: String,
        val type: ItemType,
        val statValue: Int,
        val ingredients: List<Ingredient>
    )

    private val recipeDatabase: Map<String, Recipe> = mapOf(
        "pedang_emas" to Recipe(
            key = "pedang_emas",
            name = "Pedang Emas",
            emoji = "⚔️",
            type = ItemType.WEAPON,
            statValue = 10,
            ingredients = listOf(
                Ingredient("kayu", "Kayu", 2),
                Ingredient("emas", "Emas", 4),
                Ingredient("tembaga", "Tembaga", 6)
            )
        ),
        "pedang_berlian" to Recipe(
            key = "pedang_berlian",
            name = "Pedang Berlian",
            emoji = "💎",
            type = ItemType.WEAPON,
            statValue = 20,
            ingredients = listOf(
                Ingredient("kayu", "Kayu", 3),
                Ingredient("berlian", "Berlian", 5)
            )
        ),
        "baju_kayu" to Recipe(
            key = "baju_kayu",
            name = "Baju Pelindung Kayu",
            emoji = "🪵",
            type = ItemType.ARMOR,
            statValue = 10,
            ingredients = listOf(
                Ingredient("kayu", "Kayu", 10)
            )
        ),
        "zirah_tembaga" to Recipe(
            key = "zirah_tembaga",
            name = "Zirah Tembaga",
            emoji = "🛡️",
            type = ItemType.ARMOR,
            statValue = 20,
            ingredients = listOf(
                Ingredient("kayu", "Kayu", 3),
                Ingredient("tembaga", "Tembaga", 8)
            )
        ),
        "zirah_berlian" to Recipe(
            key = "zirah_berlian",
            name = "Zirah Berlian Royale",
            emoji = "🥷",
            type = ItemType.ARMOR,
            statValue = 35,
            ingredients = listOf(
                Ingredient("zamrud", "Zamrud", 2),
                Ingredient("berlian", "Berlian", 5)
            )
        )
    )

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#buat" || msg.startsWith("#buat ") || msg == "#craft"
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
            return Respon.buat.buat1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val parts = message.trim().split(Regex("\\s+"), 2)

            if (parts.size < 2 || parts[1].trim().isEmpty()) {
                return Respon.buat.buat2.format(
                    getCategoryRecipes(ItemType.WEAPON),
                    getCategoryRecipes(ItemType.ARMOR)
                )
            }

            val targetKey = parts[1].lowercase().trim().replace(" ", "_")

            val recipe = recipeDatabase[targetKey]
                ?: return Respon.buat.buat5.format(targetKey)

            for (ing in recipe.ingredients) {
                val userStock = userJson.optInt(ing.itemKey, 0)
                if (userStock < ing.amount) {
                    return Respon.buat.buat6.format(
                        recipe.name, ing.itemName, userStock, ing.amount
                    )
                }
            }

            for (ing in recipe.ingredients) {
                val currentStock = userJson.optInt(ing.itemKey, 0)
                userJson.put(ing.itemKey, currentStock - ing.amount)
            }

            var effectMessage = ""

            when (recipe.type) {
                ItemType.WEAPON -> {
                    val currentAtk = userJson.optInt("weapon_atk", 1)
                    val currentWpn = userJson.optString("weapon", "Tinju")

                    if (recipe.statValue > currentAtk) {
                        userJson.put("weapon", recipe.name)
                        userJson.put("weapon_atk", recipe.statValue)
                        effectMessage = Respon.buat.buat8.format(recipe.name, recipe.statValue, currentWpn)
                    } else {
                        val oldQty = userJson.optInt(recipe.key, 0)
                        userJson.put(recipe.key, oldQty + 1)
                        effectMessage = Respon.buat.buat9.format(recipe.statValue)
                    }
                }
                ItemType.ARMOR -> {
                    val currentDef = userJson.optInt("armor_def", 0)
                    val currentArmor = userJson.optString("armor", "Tidak Ada")

                    if (recipe.statValue > currentDef) {
                        userJson.put("armor", recipe.name)
                        userJson.put("armor_def", recipe.statValue)
                        effectMessage = Respon.buat.buat10.format(recipe.name, recipe.statValue)
                    } else {
                        val oldQty = userJson.optInt(recipe.key, 0)
                        userJson.put(recipe.key, oldQty + 1)
                        effectMessage = Respon.buat.buat11.format(recipe.statValue, currentArmor)
                    }
                }
            }

            Const.writeFile(userFile, userJson.toString())

            Respon.buat.buat7.format(recipe.emoji, recipe.name, effectMessage)
        } catch (e: Exception) {
            Respon.buat.buat12
        }
    }

    private fun getCategoryRecipes(type: ItemType): String {
        val sb = StringBuilder()
        for (r in recipeDatabase.values) {
            if (r.type == type) {
                val ingredientsSb = r.ingredients.joinToString(", ") { "${it.amount} ${it.itemName}" }
                if (r.type == ItemType.WEAPON) {
                    sb.append(Respon.buat.buat3.format(r.key, r.name, r.statValue, ingredientsSb)).append("\n\n")
                } else {
                    sb.append(Respon.buat.buat4.format(r.key, r.name, r.statValue, ingredientsSb)).append("\n\n")
                }
            }
        }
        return sb.toString()
    }
}
