package com.sa2010sl.bot

class VipCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#vip" || lower == "#premium" || lower == "#langganan"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        return Respon.vip.vip1
    }
}
