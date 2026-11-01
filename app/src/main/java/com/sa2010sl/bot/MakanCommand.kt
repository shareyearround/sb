package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class MakanCommand constructor(
    private val context: Context? = null
) : Command {

    private data class FoodItem(
        val key: String,
        val name: String,
        val emoji: String,
        val healValue: Int
    )

    private val foodDatabase: Map<String, FoodItem> = mapOf(
        "buah" to FoodItem("buah", "Buah Segar", "🍎", 5),
        "sayur" to FoodItem("sayur", "Sayuran Hutan", "🥗", 10),
        "madu" to FoodItem("madu", "Madu Hutan", "🍯", 25),
        "eliksir_naga" to FoodItem("eliksir_naga", "Eliksir Jiwa Naga", "🧪", 50)
    )

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#makan" || msg.startsWith("#makan ")
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
            return Respon.makan.makan1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val userHp = userJson.optInt("hp", 20)
            val userMaxHp = userJson.optInt("max_hp", 20)

            if (userHp >= userMaxHp) {
                return Respon.makan.makan2.format(userHp, userMaxHp)
            }

            val parts = message.trim().split(Regex("\\s+"), 2)

            if (parts.size < 2 || parts[1].trim().isEmpty()) {
                val foodList = StringBuilder()
                var hasFood = false

                for (food in foodDatabase.values) {
                    val count = userJson.optInt(food.key, 0)
                    if (count > 0) {
                        if (hasFood) foodList.append("\n")
                        hasFood = true
                        foodList.append(Respon.makan.makan4.format(food.emoji, food.key, count, food.healValue))
                    }
                }

                if (!hasFood) {
                    foodList.append(Respon.makan.makan5)
                }

                return Respon.makan.makan3.format(foodList.toString())
            }

            val requestedFood = parts[1].lowercase().trim().replace(" ", "_")

            val food = foodDatabase[requestedFood]
                ?: return Respon.makan.makan6.format(parts[1])

            val foodCount = userJson.optInt(food.key, 0)

            if (foodCount <= 0) {
                return Respon.makan.makan7.format(food.name)
            }

            val newHp = minOf(userMaxHp, userHp + food.healValue)
            val hpRecovered = newHp - userHp
            val remainingFood = foodCount - 1

            userJson.put("hp", newHp)
            userJson.put(food.key, remainingFood)

            Const.writeFile(userFile, userJson.toString())

            Respon.makan.makan8.format(
                sender, food.emoji, food.name, hpRecovered, newHp, userMaxHp, food.name, remainingFood
            )
        } catch (e: Exception) {
            Respon.makan.makan9
        }
    }
}
