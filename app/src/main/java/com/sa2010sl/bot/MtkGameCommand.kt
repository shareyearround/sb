package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.math.sqrt
import kotlin.random.Random

class MtkGameCommand constructor(
    private val context: Context? = null
) : Command {

    private class Problem(
        var difficulty: String = "easy",
        var question: String = "",
        var answer: Long = 0L,
        var rewardKoin: Int = 0,
        var rewardExp: Int = 0
    )

    companion object {
        private const val TIMEOUT_MS = 5 * 60 * 1000L
    }

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#mtk") || lower.startsWith("mtk ")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val targetSession = if (isGroup && !groupName.isNullOrBlank()) groupName else sender
        val sessionKey = Const.sanitizeUsername(targetSession)
        val parts = message.trim().split(Regex("\\s+"))

        if (parts.size < 2) {
            return getHelpMenu()
        }

        return when (parts[1].lowercase()) {
            "mulai" -> {
                val diff = if (parts.size >= 3) parts[2].lowercase() else "easy"
                handleStartGame(sessionKey, sender, diff)
            }
            "nyerah" -> handleSurrender(sessionKey, sender)
            "jawab" -> {
                if (parts.size < 3) {
                    Respon.mtkgame.mtkgame15
                } else {
                    handleAnswer(sessionKey, sender, parts[2])
                }
            }
            else -> getHelpMenu()
        }
    }

    private fun handleStartGame(sessionKey: String, sender: String, difficulty: String): String {
        val baseDir = Const.getDatabaseDir(context)
        val gameFile = File(baseDir, "MtkGame.json")

        return try {
            val db = loadJson(gameFile)

            if (db.has(sessionKey)) {
                val activeSession = db.getJSONObject(sessionKey)

                if (isExpired(activeSession)) {
                    db.remove(sessionKey)
                    Const.writeFile(gameFile, db.toString())
                } else {
                    val q = activeSession.optString("question", "")
                    val diff = activeSession.optString("difficulty", "easy")
                    val startTime = activeSession.optLong("start_time", System.currentTimeMillis())
                    val remainingSec = (TIMEOUT_MS - (System.currentTimeMillis() - startTime)) / 1000

                    return Respon.mtkgame.mtkgame1.format(diff.uppercase(), q, remainingSec)
                }
            }

            val problem = generateProblem(difficulty)
            val newSession = JSONObject().apply {
                put("initiator", sender)
                put("initiator_clean", Const.sanitizeUsername(sender))
                put("difficulty", problem.difficulty)
                put("question", problem.question)
                put("answer", problem.answer)
                put("reward_koin", problem.rewardKoin)
                put("reward_exp", problem.rewardExp)
                put("start_time", System.currentTimeMillis())
            }

            db.put(sessionKey, newSession)
            Const.writeFile(gameFile, db.toString())

            Respon.mtkgame.mtkgame2.format(
                sender,
                problem.difficulty.uppercase(),
                problem.rewardKoin,
                problem.rewardExp,
                problem.question
            )
        } catch (e: Exception) {
            Respon.mtkgame.mtkgame3
        }
    }

    private fun handleSurrender(sessionKey: String, sender: String): String {
        val baseDir = Const.getDatabaseDir(context)
        val gameFile = File(baseDir, "MtkGame.json")

        return try {
            val db = loadJson(gameFile)
            if (!db.has(sessionKey)) {
                return Respon.mtkgame.mtkgame4
            }

            val session = db.getJSONObject(sessionKey)

            if (isExpired(session)) {
                val answer = session.optLong("answer", 0)
                db.remove(sessionKey)
                Const.writeFile(gameFile, db.toString())
                return Respon.mtkgame.mtkgame5.format(answer)
            }

            val initiatorClean = session.optString("initiator_clean", "")
            val currentClean = Const.sanitizeUsername(sender)

            if (initiatorClean != currentClean) {
                val initiatorName = session.optString("initiator", "pembuat game")
                return Respon.mtkgame.mtkgame6.format(initiatorName)
            }

            val answer = session.optLong("answer", 0)
            db.remove(sessionKey)
            Const.writeFile(gameFile, db.toString())

            Respon.mtkgame.mtkgame7.format(sender, answer)
        } catch (e: Exception) {
            Respon.mtkgame.mtkgame8
        }
    }

    private fun handleAnswer(sessionKey: String, sender: String, answerStr: String): String {
        val baseDir = Const.getDatabaseDir(context)
        val gameFile = File(baseDir, "MtkGame.json")

        return try {
            val db = loadJson(gameFile)
            if (!db.has(sessionKey)) {
                return Respon.mtkgame.mtkgame9
            }

            val session = db.getJSONObject(sessionKey)

            if (isExpired(session)) {
                val answer = session.optLong("answer", 0)
                db.remove(sessionKey)
                Const.writeFile(gameFile, db.toString())
                return Respon.mtkgame.mtkgame10.format(answer)
            }

            val userAnswer = answerStr.trim().toLongOrNull() ?: return Respon.mtkgame.mtkgame11
            val correctAnswer = session.optLong("answer", 0)

            if (userAnswer != correctAnswer) {
                return Respon.mtkgame.mtkgame12.format(sender)
            }

            val rewardKoin = session.optInt("reward_koin", 20)
            val rewardExp = session.optInt("reward_exp", 10)
            val difficulty = session.optString("difficulty", "easy")

            db.remove(sessionKey)
            Const.writeFile(gameFile, db.toString())

            val answererClean = Const.sanitizeUsername(sender)
            val userFile = File(baseDir, "$answererClean.json")

            if (userFile.exists()) {
                val userJson = JSONObject(Const.readFile(userFile))
                val currentKoin = userJson.optInt("koin", 0) + rewardKoin
                val currentExp = userJson.optInt("exp", 0) + rewardExp
                userJson.put("koin", currentKoin)
                userJson.put("exp", currentExp)
                Const.writeFile(userFile, userJson.toString())
            }

            Respon.mtkgame.mtkgame13.format(
                sender, difficulty.uppercase(), correctAnswer, rewardKoin, rewardExp
            )
        } catch (e: Exception) {
            Respon.mtkgame.mtkgame14
        }
    }

    private fun isExpired(session: JSONObject): Boolean {
        val startTime = session.optLong("start_time", 0)
        return (System.currentTimeMillis() - startTime) > TIMEOUT_MS
    }

    private fun generateProblem(diff: String): Problem {
        val p = Problem()

        when (diff) {
            "medium" -> {
                p.difficulty = "medium"
                p.rewardKoin = 50
                p.rewardExp = 25
                generateMedium(p)
            }
            "hard" -> {
                p.difficulty = "hard"
                p.rewardKoin = 100
                p.rewardExp = 50
                generateHard(p)
            }
            "nightmare" -> {
                p.difficulty = "nightmare"
                p.rewardKoin = 250
                p.rewardExp = 120
                generateNightmare(p)
            }
            else -> {
                p.difficulty = "easy"
                p.rewardKoin = 20
                p.rewardExp = 10
                generateEasy(p)
            }
        }

        return p
    }

    private fun generateEasy(p: Problem) {
        when (Random.nextInt(4)) {
            0 -> {
                val a = Random.nextInt(1, 51)
                val b = Random.nextInt(1, 51)
                p.question = "$a + $b"
                p.answer = (a + b).toLong()
            }
            1 -> {
                val a = Random.nextInt(10, 100)
                val b = Random.nextInt(1, a + 1)
                p.question = "$a - $b"
                p.answer = (a - b).toLong()
            }
            2 -> {
                val a = Random.nextInt(2, 13)
                val b = Random.nextInt(2, 12)
                p.question = "$a × $b"
                p.answer = a.toLong() * b
            }
            else -> {
                val b = Random.nextInt(2, 11)
                val ans = Random.nextInt(2, 12)
                val a = b * ans
                p.question = "$a ÷ $b"
                p.answer = ans.toLong()
            }
        }
    }

    private fun generateMedium(p: Problem) {
        when (Random.nextInt(4)) {
            0 -> {
                val a = Random.nextInt(2, 11)
                val b = Random.nextInt(5, 30)
                p.question = "$a² + $b"
                p.answer = (a * a).toLong() + b
            }
            1 -> {
                val squares = intArrayOf(4, 9, 16, 25, 36, 49, 64, 81, 100)
                val sq = squares.random()
                val b = Random.nextInt(2, 11)
                p.question = "√$sq × $b"
                p.answer = sqrt(sq.toDouble()).toLong() * b
            }
            2 -> {
                val a = Random.nextInt(5, 30)
                val b = Random.nextInt(2, 11)
                val c = Random.nextInt(2, 11)
                p.question = "$a + $b × $c"
                p.answer = a + (b.toLong() * c)
            }
            else -> {
                val a = Random.nextInt(5, 20)
                val b = Random.nextInt(2, 11)
                val c = Random.nextInt(1, 21)
                p.question = "$a × $b - $c"
                p.answer = (a.toLong() * b) - c
            }
        }
    }

    private fun generateHard(p: Problem) {
        when (Random.nextInt(3)) {
            0 -> {
                val a = Random.nextInt(5, 20)
                val b = Random.nextInt(5, 20)
                val c = Random.nextInt(2, 7)
                val d = Random.nextInt(1, 26)
                p.question = "($a + $b) × $c - $d"
                p.answer = ((a + b).toLong() * c) - d
            }
            1 -> {
                val a = Random.nextInt(3, 13)
                val squares = intArrayOf(4, 9, 16, 25, 36, 49, 64, 81, 100)
                val sq = squares.random()
                val c = Random.nextInt(10, 50)
                p.question = "$a² - √$sq + $c"
                p.answer = (a * a).toLong() - sqrt(sq.toDouble()).toLong() + c
            }
            else -> {
                val c = Random.nextInt(2, 9)
                val k = Random.nextInt(2, 11)
                val a = c * k
                val b = Random.nextInt(2, 11)
                val d = Random.nextInt(5, 30)
                p.question = "($a × $b) ÷ $c + $d"
                p.answer = ((a.toLong() * b) / c) + d
            }
        }
    }

    private fun generateNightmare(p: Problem) {
        when (Random.nextInt(3)) {
            0 -> {
                val b = Random.nextInt(2, 11)
                val k1 = Random.nextInt(2, 11)
                val a = b * k1
                val c = Random.nextInt(2, 11)

                val e = Random.nextInt(2, 11)
                val k2 = Random.nextInt(2, 11)
                val d = e * k2

                p.question = "$a ÷ $b × $c + $d ÷ $e"
                p.answer = ((a / b).toLong() * c) + (d / e)
            }
            1 -> {
                val a = Random.nextInt(2, 6)
                val squares = intArrayOf(4, 9, 16, 25, 36, 64)
                val sq = squares.random()
                val c = Random.nextInt(2, 6)
                val d = Random.nextInt(10, 50)

                val cube = a.toLong() * a * a
                val sqrtVal = sqrt(sq.toDouble()).toLong()
                p.question = "($a³ + √$sq) × $c - $d"
                p.answer = ((cube + sqrtVal) * c) - d
            }
            else -> {
                val a = Random.nextInt(2, 7)
                val squares = intArrayOf(4, 9, 16, 25, 36)
                val sq = squares.random()
                val d = Random.nextInt(2, 7)
                val k = Random.nextInt(2, 11)
                val c = d * k

                val sqVal = a.toLong() * a
                val sqrtVal = sqrt(sq.toDouble()).toLong()
                p.question = "$a² × √$sq - $c ÷ $d"
                p.answer = (sqVal * sqrtVal) - (c / d)
            }
        }
    }

    private fun getHelpMenu(): String = Respon.mtkgame.mtkgame16

    private fun loadJson(file: File): JSONObject {
        if (!file.exists()) return JSONObject()
        return runCatching { JSONObject(Const.readFile(file)) }.getOrDefault(JSONObject())
    }
}
