package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class TambangCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim() == "#tambang"
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
            return Respon.tambang.tambang1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val currentEp = userJson.optInt("ep", 0)
            val epCost = 5

            if (currentEp < epCost) {
                return Respon.tambang.tambang2.format(epCost, currentEp)
            }

            val newEp = currentEp - epCost
            userJson.put("ep", newEp)

            val totalRolls = Random.nextInt(1, 4)
            val minedResults = mutableMapOf<String, Int>()

            repeat(totalRolls) {
                val chance = Random.nextInt(100)
                val (material, count) = when {
                    chance < 40 -> "tembaga" to Random.nextInt(3, 9)
                    chance < 70 -> "besi" to Random.nextInt(2, 7)
                    chance < 85 -> "emas" to Random.nextInt(1, 4)
                    chance < 93 -> "ruby" to Random.nextInt(1, 3)
                    chance < 98 -> "zamrud" to Random.nextInt(1, 3)
                    else -> "berlian" to 1
                }
                minedResults[material] = (minedResults[material] ?: 0) + count
            }

            val resultDetail = StringBuilder()
            var first = true

            for ((matKey, gainedQty) in minedResults) {
                val oldQty = userJson.optInt(matKey, 0)
                val newQty = oldQty + gainedQty

                userJson.put(matKey, newQty)

                if (!first) {
                    resultDetail.append("\n")
                }
                first = false

                resultDetail.append(
                    Respon.tambang.tambang3.format(
                        getMaterialEmoji(matKey),
                        capitalize(matKey),
                        gainedQty,
                        newQty
                    )
                )
            }

            Const.writeFile(userFile, userJson.toString())

            Respon.tambang.tambang4.format(
                sender,
                newEp,
                resultDetail.toString()
            )
        } catch (e: Exception) {
            Respon.tambang.tambang5
        }
    }

    private fun getMaterialEmoji(material: String): String {
        return when (material) {
            "tembaga" -> "🟤"
            "besi" -> "⚪️"
            "emas" -> "🟡"
            "ruby" -> "🔴"
            "zamrud" -> "🟢"
            "berlian" -> "🔵"
            else -> "📦"
        }
    }

    private fun capitalize(str: String): String {
        return str.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}
