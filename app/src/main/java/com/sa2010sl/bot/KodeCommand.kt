package com.sa2010sl.bot

import android.content.Context
import org.json.JSONArray
import org.json.JSONObject
import java.io.File

class KodeCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#kode") || lower.startsWith("#redeem")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val parts = message.trim().split(Regex("\\s+"))
        if (parts.size < 2) {
            return Respon.kode.kode1
        }

        val inputCode = parts[1].trim()
        val cleanName = Const.sanitizeUsername(sender)

        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")
        val redeemFile = File(baseDir, "RedeemCode.json")

        if (!userFile.exists()) {
            return Respon.kode.kode2
        }

        if (!redeemFile.exists()) {
            return Respon.kode.kode3
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val redeemContent = Const.readFile(redeemFile)
            val redeemJson = JSONObject(redeemContent)

            if (!redeemJson.has(inputCode)) {
                return Respon.kode.kode4.format(inputCode)
            }

            val codeDetails = redeemJson.getJSONObject(inputCode)
            val expiredTime = codeDetails.optLong("expired", 0L)

            if (System.currentTimeMillis() > expiredTime) {
                return Respon.kode.kode5.format(inputCode)
            }

            val userId = if (userJson.has("id")) userJson.get("id").toString() else cleanName

            val claimedBy = codeDetails.optJSONArray("claimed_by") ?: JSONArray()
            for (i in 0 until claimedBy.length()) {
                if (claimedBy.getString(i).equals(userId, ignoreCase = true)) {
                    return Respon.kode.kode6.format(inputCode)
                }
            }

            val redeemedCodes = userJson.optJSONArray("redeemed_codes") ?: JSONArray()
            for (i in 0 until redeemedCodes.length()) {
                if (redeemedCodes.getString(i).equals(inputCode, ignoreCase = true)) {
                    return Respon.kode.kode6.format(inputCode)
                }
            }

            val bonusKoin = codeDetails.optInt("bonus_koin", 0)
            val bonusExp = codeDetails.optInt("bonus_exp", 0)
            val itemName = codeDetails.optString("item_name", "").trim()
            val itemType = codeDetails.optString("item_type", "").trim()
            val itemValue = codeDetails.optInt("item_value", 0)

            val currentKoin = userJson.optInt("koin", 0)
            val currentExp = userJson.optInt("exp", 0)

            userJson.put("koin", currentKoin + bonusKoin)
            userJson.put("exp", currentExp + bonusExp)

            if (itemName.isNotEmpty()) {
                val inventory = userJson.optJSONArray("inventory") ?: JSONArray()
                val newItem = JSONObject().apply {
                    put("name", itemName)
                    put("type", itemType)
                    put("value", itemValue)
                }
                inventory.put(newItem)
                userJson.put("inventory", inventory)
            }

            redeemedCodes.put(inputCode)
            userJson.put("redeemed_codes", redeemedCodes)

            claimedBy.put(userId)
            codeDetails.put("claimed_by", claimedBy)
            redeemJson.put(inputCode, codeDetails)

            Const.writeFile(userFile, userJson.toString())
            Const.writeFile(redeemFile, redeemJson.toString())

            val rewardsSb = buildString {
                if (bonusKoin > 0) {
                    append(Respon.kode.kode7.format(bonusKoin)).append("\n")
                }
                if (bonusExp > 0) {
                    append(Respon.kode.kode8.format(bonusExp)).append("\n")
                }
                if (itemName.isNotEmpty()) {
                    if (!itemType.equals("Material", ignoreCase = true) && itemValue > 0) {
                        append(Respon.kode.kode9.format(itemName, itemType, itemValue)).append("\n")
                    } else {
                        append(Respon.kode.kode10.format(itemName, itemType)).append("\n")
                    }
                }
            }

            Respon.kode.kode11.format(sender, inputCode, rewardsSb)
        } catch (e: Exception) {
            Respon.kode.kode12
        }
    }
}
