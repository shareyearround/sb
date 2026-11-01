package com.sa2010sl.bot

import android.content.Context

class MenuCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#menu" || lower == "#m"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.menu.menu1
    }
}
