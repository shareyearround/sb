package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

object PvpManager {

    private const val CHALLENGE_TIMEOUT = 5 * 60 * 1000L
    private const val BATTLE_TIMEOUT = 10 * 60 * 1000L

    
    fun isUserLocked(context: Context?, sender: String, message: String): Boolean {
        return runCatching {
            val cleanSender = Const.sanitizeUsername(sender)
            val baseDir = Const.getDatabaseDir(context)
            val userFile = File(baseDir, "$cleanSender.json")

            if (!userFile.exists()) return false

            val userJson = JSONObject(Const.readFile(userFile))
            val state = userJson.optString("pvp_state", "none")
            val timestamp = userJson.optLong("pvp_time", 0L)
            val currentTime = System.currentTimeMillis()

            if (state == "challenged" || state == "challenging") {
                if (currentTime - timestamp > CHALLENGE_TIMEOUT) {
                    resetPvpState(context, cleanSender)
                    return false
                }
                val lowerMsg = message.lowercase().trim()
                return lowerMsg != "#pvpm terima" && lowerMsg != "#pvpm tolak"
            }

            if (state == "in_battle") {
                if (currentTime - timestamp > BATTLE_TIMEOUT) {
                    val opponent = userJson.optString("pvp_opponent", "")
                    resetPvpState(context, cleanSender)
                    if (opponent.isNotEmpty()) {
                        resetPvpState(context, opponent)
                    }
                    return false
                }
                val lowerMsg = message.lowercase().trim()
                return lowerMsg != "#pvps" && !lowerMsg.startsWith("#pvpn")
            }

            false
        }.getOrDefault(false)
    }

    
    fun resetPvpState(context: Context?, cleanUsername: String) {
        runCatching {
            val baseDir = Const.getDatabaseDir(context)
            val userFile = File(baseDir, "$cleanUsername.json")

            if (!userFile.exists()) return

            val userJson = JSONObject(Const.readFile(userFile))
            userJson.put("pvp_state", "none")
            userJson.put("pvp_opponent", "")
            userJson.put("pvp_time", 0L)
            userJson.put("pvp_surrender_ask", false)

            Const.writeFile(userFile, userJson.toString())
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
