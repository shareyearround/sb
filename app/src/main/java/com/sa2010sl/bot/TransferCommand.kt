package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class TransferCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#tf") || lower.startsWith("#transfer")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val parts = message.trim().split(Regex("\\s+"))

        if (parts.size < 3) {
            return Respon.transfer.transfer1
        }

        val targetId = parts[1].toIntOrNull()
        val amount = parts[2].toIntOrNull()

        if (targetId == null || amount == null) {
            return Respon.transfer.transfer4
        }

        if (amount <= 0) {
            return Respon.transfer.transfer2
        }

        if (amount > 1_000_000) {
            return Respon.transfer.transfer3
        }

        return processTransfer(sender, targetId, amount)
    }

    @Synchronized
    private fun processTransfer(sender: String, targetId: Int, amount: Int): String {
        val senderClean = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val senderFile = File(baseDir, "$senderClean.json")

        if (!senderFile.exists()) {
            return Respon.transfer.transfer5
        }

        return try {
            val senderJson = JSONObject(Const.readFile(senderFile))
            val senderId = senderJson.optInt("id", 0)

            if (senderId == targetId) {
                return Respon.transfer.transfer6
            }

            val senderKoin = senderJson.optInt("koin", senderJson.optInt("ep", 0))
            if (senderKoin < amount) {
                return Respon.transfer.transfer7.format(senderKoin, amount)
            }

            val targetFile = findFileByUserIdIndex(baseDir, targetId)
                ?: return Respon.transfer.transfer8.format(targetId)

            val targetJson = JSONObject(Const.readFile(targetFile))
            val targetKoin = targetJson.optInt("koin", targetJson.optInt("ep", 0))

            if (targetKoin.toLong() + amount > Int.MAX_VALUE) {
                return Respon.transfer.transfer9
            }

            val newSenderKoin = senderKoin - amount
            val newTargetKoin = targetKoin + amount

            senderJson.put("koin", newSenderKoin)
            targetJson.put("koin", newTargetKoin)

            Const.writeFile(senderFile, senderJson.toString())
            Const.writeFile(targetFile, targetJson.toString())

            Respon.transfer.transfer10.format(targetId, amount, newSenderKoin)
        } catch (e: Exception) {
            Respon.transfer.transfer11
        }
    }

    private fun findFileByUserIdIndex(baseDir: File, targetId: Int): File? {
        val indexFile = File(baseDir, "IndexIdNumber.json")

        if (indexFile.exists()) {
            runCatching {
                val indexJson = JSONObject(Const.readFile(indexFile))
                val targetKey = targetId.toString()

                if (indexJson.has(targetKey)) {
                    var targetName = indexJson.optString(targetKey)
                    if (!targetName.endsWith(".json", ignoreCase = true)) {
                        targetName = "$targetName.json"
                    }
                    val file = File(baseDir, targetName)
                    if (file.exists()) {
                        return file
                    }
                }
            }
        }

        val files = baseDir.listFiles { _, name -> name.endsWith(".json", ignoreCase = true) }
        if (files != null) {
            for (f in files) {
                if (f.name.equals("IndexIdNumber.json", ignoreCase = true) ||
                    f.name.equals("RedeemCode.json", ignoreCase = true)
                ) {
                    continue
                }
                runCatching {
                    val json = JSONObject(Const.readFile(f))
                    if (json.optInt("id", -1) == targetId) {
                        return f
                    }
                }
            }
        }
        return null
    }
}
