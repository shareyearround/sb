package com.sa2010sl.bot

import android.content.Context
import java.io.File

object Const {
    const val PKG: String = "com.whatsapp"
    const val PKG2: String = "com.whatsapp.w4b"
    const val BOT: String = "sa2010sl_bot_prefs"
    const val STATUS: String = "status"
    const val STATUS2: String = "status2"
    const val LOG: String = "WAR-Log"
    const val FOOTER: String = "\n\n_2018 | Gim Teks_"

    
    fun getDatabaseDir(context: Context?): File {
        val baseDir = context?.getExternalFilesDir(null)?.let {
            File(it, "database_sl")
        } ?: File("/storage/emulated/0/Android/data/com.sa2010sl.bot/files/database_sl")

        if (!baseDir.exists()) {
            baseDir.mkdirs()
        }
        return baseDir
    }

    
    fun sanitizeUsername(username: String): String {
        return username.replace(Regex("[^a-zA-Z0-9\\s]"), "").replace(Regex("\\s+"), "_")
    }

    
    fun readFile(file: File): String {
        return file.readText(Charsets.UTF_8)
    }

    
    fun writeFile(file: File, data: String) {
        file.writeText(data, Charsets.UTF_8)
    }
}
