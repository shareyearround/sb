package com.sa2010sl.bot

interface Command {
    fun canHandle(message: String): Boolean

    fun execute(
        sender: String,
        message: String,
        isGroup: Boolean = false,
        groupName: String? = null
    ): String?
}
