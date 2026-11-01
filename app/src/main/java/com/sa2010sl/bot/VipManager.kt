package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object VipManager {

    
    fun checkAndApplyVip(userJson: JSONObject?): Boolean {
        if (userJson == null) return false

        var isModified = false

        runCatching {
            val currentTime = System.currentTimeMillis()
            var vipTier = userJson.optString("vip_tier", "none")
            val vipExpired = userJson.optLong("vip_expired", 0L)

            if (!vipTier.equals("none", ignoreCase = true) && vipExpired > 0L && currentTime > vipExpired) {
                vipTier = "none"
                userJson.put("vip_tier", "none")
                userJson.put("MaxChat", 15)
                isModified = true
            }

            if (vipTier.equals("none", ignoreCase = true)) {
                return isModified
            }

            val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
            val lastClaim = userJson.optString("vip_last_claim", "")

            if (todayDate != lastClaim) {
                val currentExp = userJson.optInt("exp", 0)
                val currentEp = userJson.optInt("ep", 0)
                val currentKoin = userJson.optInt("koin", 0)
                val currentLevel = userJson.optInt("level", 1)

                when {
                    vipTier.equals("premium", ignoreCase = true) -> {
                        userJson.put("exp", currentExp + 100)
                        userJson.put("ep", currentEp + 50)
                        userJson.put("MaxChat", 50)
                    }
                    vipTier.equals("pluspro", ignoreCase = true) -> {
                        userJson.put("exp", currentExp + 300)
                        userJson.put("ep", currentEp + 150)
                        userJson.put("level", currentLevel + 2)
                        userJson.put("MaxChat", 70)
                    }
                    vipTier.equals("megafinal", ignoreCase = true) -> {
                        userJson.put("exp", currentExp + 400)
                        userJson.put("ep", currentEp + 200)
                        userJson.put("koin", currentKoin + 100)
                        userJson.put("level", currentLevel + 5)
                        userJson.put("MaxChat", 100)
                    }
                }

                userJson.put("vip_last_claim", todayDate)
                isModified = true
            }
        }

        return isModified
    }

    
    fun processUserVipFile(context: Context?, username: String) {
        runCatching {
            val cleanName = Const.sanitizeUsername(username)
            val baseDir = Const.getDatabaseDir(context)
            val userFile = File(baseDir, "$cleanName.json")

            if (!userFile.exists()) return

            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            val isModified = checkAndApplyVip(userJson)

            if (isModified) {
                Const.writeFile(userFile, userJson.toString())
            }
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
