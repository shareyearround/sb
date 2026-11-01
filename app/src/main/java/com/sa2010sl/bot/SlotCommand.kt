package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class SlotCommand constructor(
    private val context: Context? = null
) : Command {

    companion object {
        private val SYMBOLS = arrayOf("🍒", "🍋", "🍇", "💎", "7️⃣")
    }

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith("#slot")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        if (!isGroup) {
            return Respon.slot.slot1
        }

        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val file = File(baseDir, "$cleanName.json")

        return try {
            val content = Const.readFile(file)
            val json = JSONObject(content)

            val currentKoin = json.optInt("koin", 0)
            val currentEp = json.optInt("ep", 0)
            val epCost = 1

            if (currentEp < epCost) {
                return Respon.slot.slot2.format(epCost, currentEp)
            }

            var bet = 10
            val parts = message.trim().split(Regex("\\s+"))
            if (parts.size > 1) {
                bet = parts[1].toIntOrNull() ?: return Respon.slot.slot3
            }

            if (bet <= 0) {
                return Respon.slot.slot4
            }

            if (currentKoin < bet) {
                return Respon.slot.slot5.format(currentKoin)
            }

            val s1 = SYMBOLS.random()
            val s2 = SYMBOLS.random()
            val s3 = SYMBOLS.random()

            val winAmount: Int
            val resultTitle: String

            if (s1 == s2 && s2 == s3) {
                if (s1 == "💎" || s1 == "7️⃣") {
                    winAmount = bet * 10
                    resultTitle = Respon.slot.slot6
                } else {
                    winAmount = bet * 5
                    resultTitle = Respon.slot.slot7
                }
            } else if (s1 == s2 || s2 == s3 || s1 == s3) {
                winAmount = bet * 2
                resultTitle = Respon.slot.slot8
            } else {
                winAmount = -bet
                resultTitle = Respon.slot.slot9
            }

            var newKoin = currentKoin + winAmount
            if (newKoin < 0) {
                newKoin = 0
            }
            val newEp = currentEp - epCost

            json.put("koin", newKoin)
            json.put("ep", newEp)
            Const.writeFile(file, json.toString())

            val winLossMsg = if (winAmount > 0) {
                Respon.slot.slot10.format(winAmount)
            } else {
                Respon.slot.slot11.format(winAmount)
            }

            Respon.slot.slot12.format(
                sender, s1, s2, s3, resultTitle, winLossMsg, epCost, newKoin, newEp
            )
        } catch (e: Exception) {
            Respon.slot.slot13
        }
    }
}
