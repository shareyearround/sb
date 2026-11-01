package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class HarianCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#daily" || lower == "#harian" || lower == "#absen"
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
            return Respon.harian.harian1
        }

        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val currentMinuteOfDay = (hour * 60) + minute

        val startMinute = 19 * 60
        val endMinute = 22 * 60

        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val currentTimeStr = timeFormat.format(Date())

        if (currentMinuteOfDay < startMinute || currentMinuteOfDay > endMinute) {
            return Respon.harian.harian2.format(currentTimeStr)
        }

        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val todayDateStr = dateFormat.format(Date())

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val lastDailyDate = userJson.optString("last_daily_date", "")

            if (todayDateStr == lastDailyDate) {
                return Respon.harian.harian3
            }

            val rewardKoin = Random.nextInt(50, 121)
            val rewardExp = Random.nextInt(50, 121)

            val currentKoin = userJson.optInt("koin", 0)
            val currentExp = userJson.optInt("exp", 0)

            val newKoin = currentKoin + rewardKoin
            val newExp = currentExp + rewardExp

            userJson.put("koin", newKoin)
            userJson.put("exp", newExp)
            userJson.put("last_daily_date", todayDateStr)

            Const.writeFile(userFile, userJson.toString())

            Respon.harian.harian4.format(
                currentTimeStr, rewardKoin, rewardExp, newKoin, newExp
            )
        } catch (e: Exception) {
            Respon.harian.harian5
        }
    }
}
