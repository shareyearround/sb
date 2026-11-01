package com.sa2010sl.bot

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.concurrent.ConcurrentHashMap
import kotlin.random.Random

class TriviaGameCommand(
    private val context: Context,
    commandPrefix: String,
    private val gameTitle: String,
    private val jsonFileName: String
) : Command {

    private val commandPrefix: String = commandPrefix.lowercase()
    private val activeSessions = ConcurrentHashMap<String, GameSession>()

    private data class GameSession(
        val soal: String,
        val jawaban: String,
        val tipe: String,
        val deskripsi: String
    )

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith(commandPrefix)
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        if (!isGroup || groupName.isNullOrEmpty()) {
            return Respon.triviagame.triviagame1.format(commandPrefix)
        }

        val parts = message.trim().split(Regex("\\s+"), 3)
        val subCmd = if (parts.size > 1) parts[1].lowercase() else ""

        return when (subCmd) {
            "mulai" -> handleMulai(sender, groupName)
            "jawab" -> {
                val guess = if (parts.size > 2) parts[2].trim() else ""
                handleJawab(sender, groupName, guess)
            }
            "nyerah" -> handleNyerah(groupName)
            else -> Respon.triviagame.triviagame2.format(
                gameTitle.uppercase(),
                commandPrefix,
                commandPrefix,
                commandPrefix
            )
        }
    }

    private fun handleMulai(sender: String, groupName: String): String {
        activeSessions[groupName]?.let { session ->
            return Respon.triviagame.triviagame3.format(gameTitle, session.soal, session.tipe)
        }

        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) {
            return Respon.triviagame.triviagame4
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)
            val currentEp = userJson.optInt("ep", 0)

            if (currentEp < 1) {
                return Respon.triviagame.triviagame5
            }

            val soalContent = loadSoalFromAssets(jsonFileName)
            if (soalContent.isNullOrEmpty()) {
                return Respon.triviagame.triviagame6.format(jsonFileName)
            }

            val soalArray = JSONArray(soalContent)
            if (soalArray.length() == 0) {
                return Respon.triviagame.triviagame7.format(gameTitle)
            }

            userJson.put("ep", currentEp - 1)
            Const.writeFile(userFile, userJson.toString())

            val selectedSoal = soalArray.getJSONObject(Random.nextInt(soalArray.length()))

            val newSession = GameSession(
                soal = selectedSoal.getString("soal"),
                jawaban = selectedSoal.getString("jawaban"),
                tipe = selectedSoal.optString("tipe", "Tidak ada petunjuk khusus."),
                deskripsi = selectedSoal.optString("deskripsi", "Tidak ada penjelasan khusus.")
            )

            activeSessions[groupName] = newSession

            Respon.triviagame.triviagame8.format(
                gameTitle.uppercase(),
                sender,
                newSession.soal,
                newSession.tipe,
                commandPrefix
            )
        } catch (e: Exception) {
            Respon.triviagame.triviagame9.format(gameTitle)
        }
    }

    private fun loadSoalFromAssets(fileName: String): String? {
        return runCatching {
            context.assets.open("games/$fileName").bufferedReader().use { it.readText() }
        }.getOrNull()
    }

    private fun handleJawab(sender: String, groupName: String, guess: String): String {
        val session = activeSessions[groupName]
            ?: return Respon.triviagame.triviagame10.format(gameTitle, commandPrefix)

        if (guess.isEmpty()) {
            return Respon.triviagame.triviagame11.format(commandPrefix)
        }

        val cleanGuess = guess.lowercase().replace(Regex("\\s+"), "")
        val cleanTarget = session.jawaban.lowercase().replace(Regex("\\s+"), "")

        return if (cleanGuess == cleanTarget) {
            activeSessions.remove(groupName)

            val rewardKoin = 50
            val rewardExp = 20

            val cleanName = Const.sanitizeUsername(sender)
            val baseDir = Const.getDatabaseDir(context)
            val userFile = File(baseDir, "$cleanName.json")

            if (userFile.exists()) {
                runCatching {
                    val userContent = Const.readFile(userFile)
                    val userJson = JSONObject(userContent)
                    userJson.put("koin", userJson.optInt("koin", 0) + rewardKoin)
                    userJson.put("exp", userJson.optInt("exp", 0) + rewardExp)
                    Const.writeFile(userFile, userJson.toString())
                }
            }

            Respon.triviagame.triviagame12.format(
                sender, session.jawaban, session.deskripsi, rewardKoin, rewardExp
            )
        } else {
            Respon.triviagame.triviagame13.format(session.tipe)
        }
    }

    private fun handleNyerah(groupName: String): String {
        val session = activeSessions.remove(groupName)
            ?: return Respon.triviagame.triviagame14.format(gameTitle)

        return Respon.triviagame.triviagame15.format(
            session.soal, session.jawaban, session.deskripsi
        )
    }
}
