package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class PvpCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#pvpm") || lower.startsWith("#pvps") || lower.startsWith("#pvpn")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val lower = message.lowercase().trim()

        return when {
            lower.startsWith("#pvpm") -> handlePvpm(sender, lower)
            lower == "#pvps" -> handlePvps(sender)
            lower.startsWith("#pvpn") -> handlePvpn(sender, lower)
            else -> Respon.pvp.pvp1
        }
    }

    private fun handlePvpm(sender: String, message: String): String {
        val cleanSender = PvpManager.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val senderFile = File(baseDir, "$cleanSender.json")

        if (!senderFile.exists()) return Respon.pvp.pvp2

        return try {
            val senderJson = JSONObject(PvpManager.readFile(senderFile))

            if (senderJson.optBoolean("dead", false) || senderJson.optInt("hp", 0) <= 0) {
                return Respon.pvp.pvp3
            }

            if (senderJson.optInt("level", 1) < 5) {
                return Respon.pvp.pvp4
            }

            if (senderJson.optInt("ep", 0) < 10) {
                return Respon.pvp.pvp5
            }

            val senderState = senderJson.optString("pvp_state", "none")

            if (message == "#pvpm terima") {
                if (senderState != "challenged") {
                    return Respon.pvp.pvp6
                }

                val opponentClean = senderJson.optString("pvp_opponent", "")
                val opponentFile = File(baseDir, "$opponentClean.json")

                if (!opponentFile.exists()) {
                    PvpManager.resetPvpState(context, cleanSender)
                    return Respon.pvp.pvp7
                }

                val opponentJson = JSONObject(PvpManager.readFile(opponentFile))

                val now = System.currentTimeMillis()
                senderJson.put("pvp_state", "in_battle")
                senderJson.put("pvp_time", now)
                senderJson.put("ep", senderJson.optInt("ep", 0) - 10)

                opponentJson.put("pvp_state", "in_battle")
                opponentJson.put("pvp_time", now)
                opponentJson.put("ep", opponentJson.optInt("ep", 0) - 10)

                PvpManager.writeFile(senderFile, senderJson.toString())
                PvpManager.writeFile(opponentFile, opponentJson.toString())

                return Respon.pvp.pvp8
            }

            if (message == "#pvpm tolak") {
                if (senderState != "challenged") {
                    return Respon.pvp.pvp9
                }

                val opponentClean = senderJson.optString("pvp_opponent", "")
                PvpManager.resetPvpState(context, cleanSender)
                if (opponentClean.isNotEmpty()) {
                    PvpManager.resetPvpState(context, opponentClean)
                }

                return Respon.pvp.pvp10
            }

            val targetIdStr = message.replace("#pvpm", "").trim()
            if (targetIdStr.isEmpty()) {
                return Respon.pvp.pvp11
            }

            if (senderState != "none") {
                return Respon.pvp.pvp12
            }

            val indexFile = File(baseDir, "IndexIdNumber.json")
            if (!indexFile.exists()) return Respon.pvp.pvp13

            val indexJson = JSONObject(PvpManager.readFile(indexFile))
            if (!indexJson.has(targetIdStr)) {
                return Respon.pvp.pvp14.format(targetIdStr)
            }

            val targetUsername = indexJson.getString(targetIdStr)
            val cleanTarget = PvpManager.sanitizeUsername(targetUsername)

            if (cleanTarget == cleanSender) {
                return Respon.pvp.pvp15
            }

            val targetFile = File(baseDir, "$cleanTarget.json")
            if (!targetFile.exists()) return Respon.pvp.pvp16

            val targetJson = JSONObject(PvpManager.readFile(targetFile))

            if (targetJson.optBoolean("dead", false) || targetJson.optInt("hp", 0) <= 0) {
                return Respon.pvp.pvp17
            }

            if (targetJson.optInt("level", 1) < 5) {
                return Respon.pvp.pvp18
            }

            if (targetJson.optInt("ep", 0) < 10) {
                return Respon.pvp.pvp19
            }

            val targetState = targetJson.optString("pvp_state", "none")
            if (targetState != "none") {
                return Respon.pvp.pvp20
            }

            val now = System.currentTimeMillis()
            senderJson.put("pvp_state", "challenging")
            senderJson.put("pvp_opponent", cleanTarget)
            senderJson.put("pvp_time", now)

            targetJson.put("pvp_state", "challenged")
            targetJson.put("pvp_opponent", cleanSender)
            targetJson.put("pvp_time", now)

            PvpManager.writeFile(senderFile, senderJson.toString())
            PvpManager.writeFile(targetFile, targetJson.toString())

            Respon.pvp.pvp21.format(targetUsername, sender)
        } catch (e: Exception) {
            Respon.pvp.pvp22
        }
    }

    private fun handlePvps(sender: String): String {
        val cleanSender = PvpManager.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val senderFile = File(baseDir, "$cleanSender.json")

        if (!senderFile.exists()) return Respon.pvp.pvp23

        return try {
            val senderJson = JSONObject(PvpManager.readFile(senderFile))
            val senderState = senderJson.optString("pvp_state", "none")

            if (senderState != "in_battle") {
                return Respon.pvp.pvp24
            }

            val opponentClean = senderJson.optString("pvp_opponent", "")
            val opponentFile = File(baseDir, "$opponentClean.json")

            if (!opponentFile.exists()) {
                PvpManager.resetPvpState(context, cleanSender)
                return Respon.pvp.pvp25
            }

            val opponentJson = JSONObject(PvpManager.readFile(opponentFile))

            val attackerAtk = senderJson.optInt("weapon_atk", senderJson.optInt("weapon_dmg", 1))
            val defenderDef = opponentJson.optInt("armor_def", 0)

            var netDamage = attackerAtk - (attackerAtk * defenderDef / 100)
            if (netDamage < 1) netDamage = 1

            val currentOpponentHp = opponentJson.optInt("hp", 20)
            val newOpponentHp = currentOpponentHp - netDamage

            if (newOpponentHp <= 0) {
                opponentJson.put("hp", 0)
                opponentJson.put("dead", true)

                val opponentKoin = opponentJson.optInt("koin", 0)
                val opponentExp = opponentJson.optInt("exp", 0)

                val rewardKoin: Int
                val rewardExp: Int
                val rewardLog: String

                if (opponentKoin > 0) {
                    val stolenKoin = (opponentKoin * 0.40).toInt()
                    rewardKoin = stolenKoin * 2
                    opponentJson.put("koin", opponentKoin - stolenKoin)
                    senderJson.put("koin", senderJson.optInt("koin", 0) + stolenKoin + rewardKoin)
                    rewardLog = Respon.pvp.pvp26.format(stolenKoin, rewardKoin)
                } else {
                    val stolenExp = (opponentExp * 0.50).toInt()
                    rewardExp = stolenExp * 2
                    opponentJson.put("exp", opponentExp - stolenExp)
                    senderJson.put("exp", senderJson.optInt("exp", 0) + stolenExp + rewardExp)
                    rewardLog = Respon.pvp.pvp27.format(stolenExp, rewardExp)
                }

                senderJson.put("ep", senderJson.optInt("ep", 0) + 15)

                PvpManager.resetPvpState(context, cleanSender)
                PvpManager.resetPvpState(context, opponentClean)

                PvpManager.writeFile(senderFile, senderJson.toString())
                PvpManager.writeFile(opponentFile, opponentJson.toString())

                Respon.pvp.pvp28.format(netDamage, rewardLog)
            } else {
                opponentJson.put("hp", newOpponentHp)
                PvpManager.writeFile(opponentFile, opponentJson.toString())

                Respon.pvp.pvp29.format(
                    netDamage, newOpponentHp, opponentJson.optInt("max_hp", 20)
                )
            }
        } catch (e: Exception) {
            Respon.pvp.pvp30
        }
    }

    private fun handlePvpn(sender: String, message: String): String {
        val cleanSender = PvpManager.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val senderFile = File(baseDir, "$cleanSender.json")

        if (!senderFile.exists()) return Respon.pvp.pvp23

        return try {
            val senderJson = JSONObject(PvpManager.readFile(senderFile))
            if (senderJson.optString("pvp_state", "") != "in_battle") {
                return Respon.pvp.pvp31
            }

            val opponentClean = senderJson.optString("pvp_opponent", "")
            val opponentFile = File(baseDir, "$opponentClean.json")
            if (!opponentFile.exists()) return Respon.pvp.pvp32

            val opponentJson = JSONObject(PvpManager.readFile(opponentFile))

            when (message) {
                "#pvpn setuju" -> {
                    if (!senderJson.optBoolean("pvp_surrender_ask", false)) {
                        return Respon.pvp.pvp33
                    }

                    PvpManager.resetPvpState(context, cleanSender)
                    PvpManager.resetPvpState(context, opponentClean)

                    Respon.pvp.pvp34
                }
                "#pvpn tolak" -> {
                    if (!senderJson.optBoolean("pvp_surrender_ask", false)) {
                        return Respon.pvp.pvp35
                    }

                    senderJson.put("pvp_surrender_ask", false)
                    opponentJson.put("pvp_surrender_ask", false)

                    PvpManager.writeFile(senderFile, senderJson.toString())
                    PvpManager.writeFile(opponentFile, opponentJson.toString())

                    Respon.pvp.pvp36
                }
                else -> {
                    opponentJson.put("pvp_surrender_ask", true)
                    PvpManager.writeFile(opponentFile, opponentJson.toString())

                    Respon.pvp.pvp37
                }
            }
        } catch (e: Exception) {
            Respon.pvp.pvp38
        }
    }
}
