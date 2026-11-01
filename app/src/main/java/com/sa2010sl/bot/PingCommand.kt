package com.sa2010sl.bot

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PingCommand : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith("#ping")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val startTime = System.currentTimeMillis()

        val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        val deviceTime = sdf.format(Date(startTime))

        val latency = System.currentTimeMillis() - startTime

        val typeInfo = if (isGroup) {
            Respon.ping.ping1.format(groupName)
        } else {
            Respon.ping.ping2
        }

        return Respon.ping.ping3.format(sender, typeInfo, latency, deviceTime)
    }
}
