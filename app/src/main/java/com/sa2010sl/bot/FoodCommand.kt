package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class FoodCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#konsumsi" || lower.startsWith("#belanja")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val lower = message.lowercase().trim()

        return when {
            lower == "#konsumsi" -> handleStatusMakan(sender)
            lower.startsWith("#belanja") -> handleBelanja(sender, lower)
            else -> Respon.food.food1
        }
    }

    private fun handleStatusMakan(sender: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.food.food2

        return try {
            val userJson = JSONObject(Const.readFile(userFile))
            val hunger = userJson.optInt("hunger", 100)

            val statusInfo = when {
                hunger > 70 -> Respon.food.food3
                hunger > 30 -> Respon.food.food4
                else -> Respon.food.food5
            }

            Respon.food.food6.format(sender, hunger, statusInfo)
        } catch (e: Exception) {
            Respon.food.food7
        }
    }

    private fun handleBelanja(sender: String, message: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.food.food8

        val item = message.replace("#belanja", "").trim().lowercase()

        if (item.isEmpty()) {
            return Respon.food.food9
        }

        return try {
            val userJson = JSONObject(Const.readFile(userFile))

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return Respon.food.food10
            }

            var currentKoin = userJson.optInt("koin", 0)
            val currentHunger = userJson.optInt("hunger", 100)

            if (currentHunger >= 100) {
                return Respon.food.food11
            }

            val (cost, restoreAmount, foodName) = when (item) {
                "roti" -> Triple(50, 25, "Roti Gandum")
                "nasgor" -> Triple(75, 60, "Nasi Goreng Spesial")
                "ayam" -> Triple(130, 100, "Ayam Bakar Raksasa")
                else -> return Respon.food.food12
            }

            if (currentKoin < cost) {
                return Respon.food.food13.format(foodName, cost, currentKoin)
            }

            currentKoin -= cost
            val newHunger = minOf(100, currentHunger + restoreAmount)

            userJson.put("koin", currentKoin)
            userJson.put("hunger", newHunger)

            Const.writeFile(userFile, userJson.toString())

            Respon.food.food14.format(foodName, cost, currentHunger, newHunger, currentKoin)
        } catch (e: Exception) {
            Respon.food.food15
        }
    }
}
