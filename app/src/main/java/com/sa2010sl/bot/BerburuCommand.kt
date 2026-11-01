package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class BerburuCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim() == "#berburu"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        if (!isGroup) {
            return Respon.berburu.berburu1
        }

        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) {
            return Respon.berburu.berburu2
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val currentEp = userJson.optInt("ep", 0)
            val epCost = 4

            if (currentEp < epCost) {
                return Respon.berburu.berburu3.format(epCost, currentEp)
            }

            val remainingEp = currentEp - epCost
            userJson.put("ep", remainingEp)

            if (Random.nextInt(100) < 30) {
                Const.writeFile(userFile, userJson.toString())
                return Respon.berburu.berburu4.format(sender, remainingEp)
            }

            val totalRolls = Random.nextInt(1, 4)
            val huntResults = mutableMapOf<String, Int>()

            repeat(totalRolls) {
                val chance = Random.nextInt(100)
                val (prey, count) = when {
                    chance < 35 -> "burung" to Random.nextInt(1, 4)
                    chance < 65 -> "ular" to Random.nextInt(1, 3)
                    chance < 85 -> "babi_hutan" to Random.nextInt(1, 3)
                    chance < 95 -> "slime" to Random.nextInt(1, 3)
                    else -> "beruang" to 1
                }
                huntResults[prey] = (huntResults[prey] ?: 0) + count
            }

            val resultDetail = StringBuilder()
            for ((preyKey, gainedQty) in huntResults) {
                val oldQty = userJson.optInt(preyKey, 0)
                val newQty = oldQty + gainedQty

                userJson.put(preyKey, newQty)

                resultDetail.append(
                    Respon.berburu.berburu5.format(
                        getPreyEmoji(preyKey),
                        formatPreyName(preyKey),
                        gainedQty,
                        newQty
                    )
                ).append("\n")
            }

            Const.writeFile(userFile, userJson.toString())

            Respon.berburu.berburu6.format(
                sender,
                groupName ?: "",
                remainingEp,
                resultDetail.toString().trim()
            )
        } catch (e: Exception) {
            Respon.berburu.berburu7
        }
    }

    private fun getPreyEmoji(prey: String): String {
        return when (prey) {
            "burung" -> "🐦"
            "ular" -> "🐍"
            "babi_hutan" -> "🐗"
            "slime" -> "❇️"
            "beruang" -> "🐻"
            else -> "🥩"
        }
    }

    private fun formatPreyName(str: String): String {
        if (str == "babi_hutan") return "Babi Hutan"
        return str.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}
