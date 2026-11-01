package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.math.min

class TSPCommand(private val context: Context? = null) : Command {

    private companion object {
        private const val WATER_COOLDOWN = 5 * 60 * 1000L
        private val USERNAME_REGEX = Regex("[^a-zA-Z0-9\\s]")
        private val WHITESPACE_REGEX = Regex("\\s+")

        private val PLANT_DATABASE = mapOf(
            "padi" to PlantInfo("Padi", 3, false, 5),
            "apel" to PlantInfo("Apel", 4, true, 2),
            "anggur" to PlantInfo("Anggur", 4, true, 2),
            "mangga" to PlantInfo("Mangga", 5, true, 2),
            "sawit" to PlantInfo("Kelapa Sawit", 8, false, 3),
            "melon" to PlantInfo("Melon", 5, true, 3),
            "kubis" to PlantInfo("Kubis", 3, true, 2),
            "brokoli" to PlantInfo("Brokoli", 4, true, 2),
            "cengkeh" to PlantInfo("Cengkeh", 7, false, 4)
        )
    }

    private data class PlantInfo(
        val name: String,
        val requiredWater: Int,
        val isFood: Boolean,
        val yieldAmount: Int
    )

    override fun canHandle(message: String): Boolean {
        val lower = message.trim().lowercase()
        return lower.startsWith("#tanam") || lower == "#siram" || lower == "#panen"
    }

    override fun execute(sender: String, message: String, isGroup: Boolean, groupName: String?): String {
        val lower = message.trim().lowercase()
        return when {
            lower.startsWith("#tanam") -> handleTanam(sender, lower)
            lower == "#siram" -> handleSiram(sender)
            lower == "#panen" -> handlePanen(sender)
            else -> Respon.tsp.tsp1
        }
    }

    private fun handleTanam(sender: String, message: String): String {
        val userFile = getUserFile(sender)
        if (!userFile.exists()) return Respon.tsp.tsp2

        return runCatching {
            val userJson = JSONObject(userFile.readText())

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return@runCatching Respon.tsp.tsp3
            }

            val farmData = userJson.optJSONObject("farm_data")
            if (farmData != null && farmData.optBoolean("is_planted", false)) {
                val currentPlant = farmData.optString("plant_name", "Tanaman")
                val currentWaterStr = farmData.optString("water_count", "0")
                val currentWater = if (currentWaterStr.isEmpty()) 0 else farmData.optInt("water_count", 0)
                val targetWater = farmData.optInt("target_water", 3)

                return@runCatching Respon.tsp.tsp4.format(currentPlant, currentWater, targetWater)
            }

            val plantKey = message.removePrefix("#tanam").trim().lowercase()
            val selected = PLANT_DATABASE[plantKey]

            if (selected == null) {
                return@runCatching buildString {
                    append(Respon.tsp.tsp5)
                    PLANT_DATABASE.forEach { (key, p) ->
                        val category = if (p.isFood) "🍱 Makanan" else "📦 Item/Material"
                        append(Respon.tsp.tsp6.format(p.name, key, p.requiredWater, p.yieldAmount, category))
                    }
                }
            }

            val newFarmData = JSONObject().apply {
                put("is_planted", true)
                put("plant_key", plantKey)
                put("plant_name", selected.name)
                put("water_count", 0)
                put("target_water", selected.requiredWater)
                put("last_water_time", 0)
                put("is_food", selected.isFood)
                put("yield_amount", selected.yieldAmount)
            }

            userJson.put("farm_data", newFarmData)
            userFile.writeText(userJson.toString())

            Respon.tsp.tsp7.format(selected.name, selected.requiredWater)

        }.getOrElse {
            Respon.tsp.tsp8
        }
    }

    private fun handleSiram(sender: String): String {
        val userFile = getUserFile(sender)
        if (!userFile.exists()) return Respon.tsp.tsp9

        return runCatching {
            val userJson = JSONObject(userFile.readText())

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return@runCatching Respon.tsp.tsp10
            }

            val farmData = userJson.optJSONObject("farm_data")
            if (farmData == null || !farmData.optBoolean("is_planted", false)) {
                return@runCatching Respon.tsp.tsp11
            }

            var currentWater = farmData.optInt("water_count", 0)
            val targetWater = farmData.optInt("target_water", 3)
            val plantName = farmData.optString("plant_name", "Tanaman")

            if (currentWater >= targetWater) {
                return@runCatching Respon.tsp.tsp12.format(plantName)
            }

            val lastWaterTime = farmData.optLong("last_water_time", 0)
            val currentTime = System.currentTimeMillis()
            val timePassed = currentTime - lastWaterTime

            if (timePassed < WATER_COOLDOWN) {
                val remainingMillis = WATER_COOLDOWN - timePassed
                val minutes = (remainingMillis / 1000) / 60
                val seconds = (remainingMillis / 1000) % 60

                return@runCatching Respon.tsp.tsp13.format(plantName, minutes, seconds)
            }

            currentWater++
            farmData.put("water_count", currentWater)
            farmData.put("last_water_time", currentTime)
            userJson.put("farm_data", farmData)

            userFile.writeText(userJson.toString())

            if (currentWater >= targetWater) {
                Respon.tsp.tsp14.format(plantName, currentWater, targetWater)
            } else {
                Respon.tsp.tsp15.format(plantName, currentWater, targetWater)
            }

        }.getOrElse {
            Respon.tsp.tsp16
        }
    }

    private fun handlePanen(sender: String): String {
        val userFile = getUserFile(sender)
        if (!userFile.exists()) return Respon.tsp.tsp9

        return runCatching {
            val userJson = JSONObject(userFile.readText())

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return@runCatching Respon.tsp.tsp17
            }

            val farmData = userJson.optJSONObject("farm_data")
            if (farmData == null || !farmData.optBoolean("is_planted", false)) {
                return@runCatching Respon.tsp.tsp18
            }

            val currentWater = farmData.optInt("water_count", 0)
            val targetWater = farmData.optInt("target_water", 3)
            val plantName = farmData.optString("plant_name", "Tanaman")
            val isFood = farmData.optBoolean("is_food", false)
            val yieldAmount = farmData.optInt("yield_amount", 1)

            if (currentWater < targetWater) {
                return@runCatching Respon.tsp.tsp19.format(plantName, currentWater, targetWater)
            }

            if (isFood) {
                val currentHunger = userJson.optInt("hunger", 100)
                val restoreAmount = yieldAmount * 25
                val newHunger = min(100, currentHunger + restoreAmount)
                userJson.put("hunger", newHunger)

                userJson.put("farm_data", JSONObject())
                userFile.writeText(userJson.toString())

                Respon.tsp.tsp20.format(plantName, restoreAmount, newHunger)
            } else {
                val inventory = userJson.optJSONObject("inventory") ?: JSONObject()

                val currentItemCount = inventory.optInt(plantName, 0)
                inventory.put(plantName, currentItemCount + yieldAmount)
                userJson.put("inventory", inventory)

                userJson.put("farm_data", JSONObject())
                userFile.writeText(userJson.toString())

                Respon.tsp.tsp21.format(plantName, yieldAmount, plantName)
            }

        }.getOrElse {
            Respon.tsp.tsp22
        }
    }

    private fun getUserFile(sender: String): File {
        val baseDir = Const.getDatabaseDir(context)
        return File(baseDir, "${sanitizeUsername(sender)}.json")
    }

    private fun sanitizeUsername(username: String): String {
        return username.replace(USERNAME_REGEX, "").replace(WHITESPACE_REGEX, "_")
    }
}
