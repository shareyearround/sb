package com.sa2010sl.bot

class WarningCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#warning" ||
                lower == "#aturan" ||
                lower == "#rules" ||
                lower == "#peringatan"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.warning.warning1
    }
}
