package com.sa2010sl.bot

class UpdateCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#update list" ||
                lower == "#update" ||
                lower == "#changelog" ||
                lower == "#version"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.updatets.updatets1
    }
}
