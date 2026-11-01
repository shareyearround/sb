package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class LemparKoinCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith("#lemparkoin")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val lowerMsg = message.lowercase().trim()
        val choice = lowerMsg.replace("#lemparkoin", "").trim()

        if (choice != "angka" && choice != "gambar") {
            return Respon.lemparkoin.lemparkoin1
        }

        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) {
            return Respon.lemparkoin.lemparkoin2
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return Respon.lemparkoin.lemparkoin3
            }

            val maxLK = userJson.optInt("maxLK", 5)
            var sisaLK = userJson.optInt("lemparkoin", maxLK)
            var lastLKDate = userJson.optString("last_lk_date", "")
            val todayDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            if (todayDate != lastLKDate) {
                sisaLK = maxLK
                lastLKDate = todayDate
            }

            if (sisaLK <= 0) {
                return Respon.lemparkoin.lemparkoin4.format(maxLK)
            }

            val isSuccess = Random.nextInt(100) < 40
            val actualResult = if (isSuccess) {
                choice
            } else {
                if (choice == "angka") "gambar" else "angka"
            }

            sisaLK--

            userJson.put("lemparkoin", sisaLK)
            userJson.put("maxLK", maxLK)
            userJson.put("last_lk_date", lastLKDate)

            val resultDetail = if (isSuccess) {
                val rewardKoin = Random.nextInt(50, 101)
                val rewardExp = Random.nextInt(50, 101)

                val currentKoin = userJson.optInt("koin", 0)
                val currentExp = userJson.optInt("exp", 0)

                userJson.put("koin", currentKoin + rewardKoin)
                userJson.put("exp", currentExp + rewardExp)

                Respon.lemparkoin.lemparkoin5.format(rewardKoin, rewardExp)
            } else {
                Respon.lemparkoin.lemparkoin6
            }

            Const.writeFile(userFile, userJson.toString())

            Respon.lemparkoin.lemparkoin7.format(
                choice.uppercase(),
                actualResult.uppercase(),
                resultDetail,
                sisaLK,
                maxLK
            )
        } catch (e: Exception) {
            Respon.lemparkoin.lemparkoin8
        }
    }
}
