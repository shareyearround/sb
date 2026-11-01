package com.sa2010sl.bot

class CreditCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#credit" || lower == "#credits" || lower == "#kredit" || lower == "#tim"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.credit.credit1
    }
}
