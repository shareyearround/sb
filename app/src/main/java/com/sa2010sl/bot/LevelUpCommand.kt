package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class LevelUpCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#levelup" || lower == "#levelup semua" || lower == "#levelup all"
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
            return Respon.levelup.levelup1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            var currentLevel = userJson.optInt("level", 1)
            var currentExp = userJson.optInt("exp", 0)
            val requiredExp = currentLevel * 100

            val lowerMsg = message.lowercase().trim()
            val isAll = lowerMsg == "#levelup semua" || lowerMsg == "#levelup all"

            if (isAll) {
                val initialLevel = currentLevel
                var levelsGained = 0
                var totalBonusKoin = 0
                var totalBonusExp = 0

                while (currentExp >= currentLevel * 100) {
                    val req = currentLevel * 100
                    currentExp -= req
                    currentLevel++
                    levelsGained++

                    val bonusKoin = Random.nextInt(50, 151)
                    val bonusExp = Random.nextInt(5, 21)

                    totalBonusKoin += bonusKoin
                    totalBonusExp += bonusExp
                    currentExp += bonusExp
                }

                if (levelsGained == 0) {
                    return Respon.levelup.levelup2.format(
                        currentLevel, currentExp, requiredExp, requiredExp - currentExp
                    )
                }

                val totalKoin = userJson.optInt("koin", 0) + totalBonusKoin
                userJson.put("level", currentLevel)
                userJson.put("exp", currentExp)
                userJson.put("koin", totalKoin)

                Const.writeFile(userFile, userJson.toString())

                Respon.levelup.levelup3.format(
                    sender,
                    initialLevel,
                    currentLevel,
                    levelsGained,
                    currentExp,
                    currentLevel * 100,
                    totalBonusKoin,
                    totalBonusExp
                )
            } else {
                if (currentExp < requiredExp) {
                    return Respon.levelup.levelup2.format(
                        currentLevel, currentExp, requiredExp, requiredExp - currentExp
                    )
                }

                currentExp -= requiredExp
                currentLevel++

                val bonusKoin = Random.nextInt(50, 151)
                val bonusExp = Random.nextInt(5, 21)

                val totalKoin = userJson.optInt("koin", 0) + bonusKoin
                currentExp += bonusExp

                userJson.put("level", currentLevel)
                userJson.put("exp", currentExp)
                userJson.put("koin", totalKoin)

                Const.writeFile(userFile, userJson.toString())

                Respon.levelup.levelup4.format(
                    sender,
                    currentLevel,
                    currentExp,
                    currentLevel * 100,
                    bonusKoin,
                    bonusExp
                )
            }
        } catch (e: Exception) {
            Respon.levelup.levelup5
        }
    }
}
