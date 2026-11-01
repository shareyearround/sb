package com.sa2010sl.bot

class IntroCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#intro" ||
                lower == "#pengenal"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.intro.intro1
    }
}