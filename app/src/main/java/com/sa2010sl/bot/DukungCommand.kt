package com.sa2010sl.bot

class DukungCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#dukung" || lower == "#donasi" || lower == "#support" || lower == "#donate"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.dukung.dukung1.format(sender)
    }
}
