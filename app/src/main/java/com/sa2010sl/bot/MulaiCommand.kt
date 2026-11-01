package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class MulaiCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim() == "#mulai"
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
            return Respon.mulai.mulai1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val isStarted = userJson.optBoolean("startuser", false)
            if (isStarted) {
                return Respon.mulai.mulai2
            }

            userJson.put("startuser", true)
            Const.writeFile(userFile, userJson.toString())

            Respon.mulai.mulai3
        } catch (e: Exception) {
            Respon.mulai.mulai4
        }
    }
}
