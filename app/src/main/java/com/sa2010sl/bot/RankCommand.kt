package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class RankCommand constructor(
    private val context: Context? = null
) : Command {

    private data class UserData(
        val name: String,
        val level: Int,
        val koin: Int,
        val exp: Int
    )

    private val ignoredFiles = setOf(
        "IndexIdNumber.json",
        "RedeemCode.json",
        "IndexBankUser.json",
        "boss_active.json"
    )

    override fun canHandle(message: String): Boolean {
        val msg = message.lowercase().trim()
        return msg == "#rank" || msg == "#top"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val dbFolder = Const.getDatabaseDir(context)

        if (!dbFolder.exists() || !dbFolder.isDirectory) {
            return Respon.rank.rank1
        }

        val files = dbFolder.listFiles { _, name -> name.endsWith(".json") }
        if (files.isNullOrEmpty()) {
            return Respon.rank.rank2
        }

        val cleanSender = Const.sanitizeUsername(sender)
        val userList = mutableListOf<UserData>()

        for (file in files) {
            val fileName = file.name

            if (ignoredFiles.any { it.equals(fileName, ignoreCase = true) }) {
                continue
            }

            runCatching {
                val content = Const.readFile(file)
                val json = JSONObject(content)

                val name = fileName.removeSuffix(".json")
                val level = json.optInt("level", 1)
                val koin = json.optInt("koin", 0)
                val exp = json.optInt("exp", 0)

                userList.add(UserData(name, level, koin, exp))
            }
        }

        if (userList.isEmpty()) {
            return Respon.rank.rank3
        }

        val sortedList = userList.sortedWith(
            compareByDescending<UserData> { it.level }
                .thenByDescending { it.koin }
                .thenByDescending { it.exp }
        )

        val listSb = StringBuilder()
        var userRank = -1
        var currentUserData: UserData? = null

        val limit = minOf(10, sortedList.size)
        for (i in sortedList.indices) {
            val u = sortedList[i]
            val rankNum = i + 1

            if (u.name.equals(cleanSender, ignoreCase = true)) {
                userRank = rankNum
                currentUserData = u
            }

            if (i < limit) {
                val (badge, title) = when (rankNum) {
                    1 -> "🥇" to Respon.rank.rank4
                    2 -> "🥈" to Respon.rank.rank5
                    3 -> "🥉" to Respon.rank.rank6
                    else -> "🎖️" to Respon.rank.rank7
                }

                if (i > 0) listSb.append("\n\n")
                listSb.append(
                    Respon.rank.rank8.format(
                        badge, rankNum, u.name, title, u.level, u.koin, u.exp
                    )
                )
            }
        }

        val userPosStr = if (userRank > 10 && currentUserData != null) {
            Respon.rank.rank9.format(
                userRank, sortedList.size, currentUserData.level, currentUserData.koin, currentUserData.exp
            )
        } else {
            ""
        }

        return Respon.rank.rank10.format(listSb.toString(), userPosStr).trim()
    }
}
