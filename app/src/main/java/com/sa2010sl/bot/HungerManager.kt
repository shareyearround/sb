package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

object HungerManager {

    
    fun processHungerAction(context: Context?, username: String?): String? {
        if (username.isNullOrEmpty()) return null

        val cleanName = Const.sanitizeUsername(username)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) return null

        return runCatching {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            if (userJson.optBoolean("dead", false)) return null

            var currentHunger = userJson.optInt("hunger", 100)
            val drain = Random.nextInt(4, 7)
            currentHunger -= drain

            if (currentHunger <= 0) {
                resetUserDataOnStarvation(userJson)
                Const.writeFile(userFile, userJson.toString())
                return Respon.hunger.hunger1
            }

            userJson.put("hunger", currentHunger)
            Const.writeFile(userFile, userJson.toString())

            if (currentHunger <= 20) {
                Respon.hunger.hunger2.format(currentHunger)
            } else {
                null
            }
        }.getOrNull()
    }

    private fun resetUserDataOnStarvation(userJson: JSONObject) {
        val originalId = userJson.optInt("id", 0)
        val originalUsername = userJson.optString("username", "")

        userJson.apply {
            put("id", originalId)
            put("username", originalUsername)
            put("level", 1)
            put("exp", 0)
            put("ep", 50)
            put("hp", 20)
            put("max_hp", 20)
            put("koin", 100)
            put("hunger", 100)
            put("dead", true)
            put("weapon", "Tinju")
            put("weapon_atk", 1)
            put("armor", "Tanpa Armor")
            put("armor_def", 0)
            put("in_raid_battle", false)
            put("in_guardian_battle", false)
            put("pvp_state", "none")
            put("inventory", JSONObject())
            put("mancing_data", JSONObject())
        }
    }

    
    fun sanitizeUsername(username: String): String {
        return Const.sanitizeUsername(username)
    }

    
    fun readFile(file: File): String {
        return Const.readFile(file)
    }

    
    fun writeFile(file: File, data: String) {
        Const.writeFile(file, data)
    }
}
