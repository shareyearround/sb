package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class RampokCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith("#rampok")
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
            return Respon.rampok.rampok1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            if (userJson.optBoolean("dead", false)) {
                return Respon.rampok.rampok2
            }

            val calendar = Calendar.getInstance()
            val currentHour = calendar.get(Calendar.HOUR_OF_DAY)

            if (currentHour < 7 || currentHour >= 14) {
                return Respon.rampok.rampok3
            }

            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val todayDate = dateFormat.format(Date())

            val lastDate = userJson.optString("rampok_date", "")
            var rampokCount = userJson.optInt("rampok_count", 0)

            if (todayDate != lastDate) {
                rampokCount = 0
            }

            rampokCount++

            userJson.put("rampok_date", todayDate)
            userJson.put("rampok_count", rampokCount)

            if (rampokCount > 6) {
                userJson.put("dead", true)

                var currentKoin = userJson.optInt("koin", 0)
                var currentExp = userJson.optInt("exp", 0)

                var lostKoin = 0
                var lostExp = 0

                if (currentKoin > 0) {
                    lostKoin = (currentKoin * 0.30).toInt()
                    currentKoin -= lostKoin
                    userJson.put("koin", currentKoin)
                } else if (currentExp > 0) {
                    lostExp = (currentExp * 0.20).toInt()
                    currentExp -= lostExp
                    userJson.put("exp", currentExp)
                }

                Const.writeFile(userFile, userJson.toString())

                val lostMsg = when {
                    lostKoin > 0 -> Respon.rampok.rampok4.format(lostKoin) + "\n"
                    lostExp > 0 -> Respon.rampok.rampok5.format(lostExp) + "\n"
                    else -> ""
                }

                return Respon.rampok.rampok6.format(sender, rampokCount, lostMsg)
            }

            val isSuccess = Random.nextBoolean()

            var currentKoin = userJson.optInt("koin", 0)
            var currentExp = userJson.optInt("exp", 0)

            if (isSuccess) {
                val earnedKoin = Random.nextInt(100, 301)
                val earnedExp = Random.nextInt(15, 41)

                currentKoin += earnedKoin
                currentExp += earnedExp

                userJson.put("koin", currentKoin)
                userJson.put("exp", currentExp)

                Const.writeFile(userFile, userJson.toString())

                Respon.rampok.rampok7.format(sender, rampokCount, earnedKoin, earnedExp)
            } else {
                val lostKoin = Random.nextInt(20, 51)
                currentKoin = maxOf(0, currentKoin - lostKoin)

                userJson.put("koin", currentKoin)
                Const.writeFile(userFile, userJson.toString())

                Respon.rampok.rampok8.format(sender, rampokCount, lostKoin)
            }
        } catch (e: Exception) {
            Respon.rampok.rampok9
        }
    }
}
