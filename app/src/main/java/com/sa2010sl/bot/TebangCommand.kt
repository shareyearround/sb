package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class TebangCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim() == "#tebang"
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
            return Respon.tebang.tebang1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val currentEp = userJson.optInt("ep", 0)
            val epCost = 3

            if (currentEp < epCost) {
                return Respon.tebang.tebang2.format(epCost, currentEp)
            }

            val newEp = currentEp - epCost
            userJson.put("ep", newEp)

            val woodGained = Random.nextInt(3, 9)
            val oldKayu = userJson.optInt("kayu", 0)
            val newKayu = oldKayu + woodGained
            userJson.put("kayu", newKayu)

            Const.writeFile(userFile, userJson.toString())

            Respon.tebang.tebang3.format(
                sender,
                newEp,
                woodGained,
                newKayu
            )
        } catch (e: Exception) {
            Respon.tebang.tebang4
        }
    }
}
