package com.sa2010sl.bot

import android.content.Context

class BantuanCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#bantuan") || lower.startsWith("#help") || lower.startsWith("#cara")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val parts = message.trim().split(Regex("\\s+"), 2)

        if (parts.size < 2) {
            return getGeneralHelp()
        }

        val category = parts[1].lowercase().trim().replace("#", "")

        return when (category) {
            "akun", "utama" -> getHelpAkun()
            "aksi", "petualangan" -> getHelpPetualangan()
            "item", "ekonomi" -> getHelpEkonomi()
            "kuis", "game" -> getHelpKuis()
            else -> getGeneralHelp()
        }
    }

    private fun getGeneralHelp(): String = Respon.bantuan.bantuan1

    private fun getHelpAkun(): String = Respon.bantuan.bantuan2

    private fun getHelpPetualangan(): String = Respon.bantuan.bantuan3

    private fun getHelpEkonomi(): String = Respon.bantuan.bantuan4

    private fun getHelpKuis(): String = Respon.bantuan.bantuan5
}
