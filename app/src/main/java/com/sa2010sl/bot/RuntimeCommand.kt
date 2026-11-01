package com.sa2010sl.bot

import android.os.SystemClock
import java.util.concurrent.TimeUnit

class RuntimeCommand : Command {

    companion object {
        private val BOT_START_TIME = System.currentTimeMillis()
    }

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower == "#runtime" || lower == "#uptime" || lower == "#aktif"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val currentMillis = System.currentTimeMillis()
        val uptimeMillis = currentMillis - BOT_START_TIME

        val formattedBotRuntime = formatDuration(uptimeMillis)
        val formattedDeviceUptime = formatDuration(SystemClock.elapsedRealtime())

        return Respon.runtime.runtime1.format(formattedBotRuntime, formattedDeviceUptime)
    }

    private fun formatDuration(millis: Long): String {
        var tempMillis = millis
        val days = TimeUnit.MILLISECONDS.toDays(tempMillis)
        tempMillis -= TimeUnit.DAYS.toMillis(days)

        val hours = TimeUnit.MILLISECONDS.toHours(tempMillis)
        tempMillis -= TimeUnit.HOURS.toMillis(hours)

        val minutes = TimeUnit.MILLISECONDS.toMinutes(tempMillis)
        tempMillis -= TimeUnit.MINUTES.toMillis(minutes)

        val seconds = TimeUnit.MILLISECONDS.toSeconds(tempMillis)

        return buildString {
            if (days > 0) append(days).append(" hari ")
            if (hours > 0 || days > 0) append(hours).append(" jam ")
            if (minutes > 0 || hours > 0 || days > 0) append(minutes).append(" menit ")
            append(seconds).append(" detik")
        }.trim()
    }
}
