package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class JualCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#jual" || msg.startsWith("#jual ")
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
            return Respon.jual.jual1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val parts = message.trim().split(Regex("\\s+"))

            if (parts.size < 2) {
                return Respon.jual.jual2
            }

            val itemKey = parts[1].lowercase().trim().replace(" ", "_")
            val currentQty = userJson.optInt(itemKey, 0)

            if (currentQty <= 0) {
                return Respon.jual.jual3.format(formatItemName(itemKey))
            }

            var amountToSell = 1

            if (parts.size >= 3) {
                val qtyArg = parts[2].lowercase().trim()
                if (qtyArg == "all") {
                    amountToSell = currentQty
                } else {
                    amountToSell = qtyArg.toIntOrNull() ?: return Respon.jual.jual4
                }
            }

            if (amountToSell <= 0) {
                return Respon.jual.jual5
            }

            if (amountToSell > currentQty) {
                return Respon.jual.jual6.format(
                    formatItemName(itemKey), currentQty, amountToSell
                )
            }

            val pricePerUnit = Random.nextInt(5, 31)
            val totalEarned = pricePerUnit * amountToSell

            val currentKoin = userJson.optInt("koin", 0)
            val newKoin = currentKoin + totalEarned
            val newQty = currentQty - amountToSell

            userJson.put(itemKey, newQty)
            userJson.put("koin", newKoin)

            Const.writeFile(userFile, userJson.toString())

            Respon.jual.jual7.format(
                sender,
                formatItemName(itemKey),
                amountToSell,
                pricePerUnit,
                totalEarned,
                newQty,
                newKoin
            )
        } catch (e: Exception) {
            Respon.jual.jual8
        }
    }

    private fun formatItemName(str: String): String {
        return str.split("_").joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
}
