package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import java.util.Calendar

class BankCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith("#bank")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        if (hour < 10 || hour >= 16) {
            return Respon.bank.bank1
        }

        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) {
            return Respon.bank.bank2
        }

        val parts = message.trim().split(Regex("\\s+"))

        if (parts.size < 2 || parts[1].equals("cek", ignoreCase = true) || parts[1].equals("status", ignoreCase = true)) {
            return getBankStatus(userFile)
        }

        val action = parts[1].lowercase()

        if (parts.size < 3) {
            return Respon.bank.bank3
        }

        val amountStr = parts[2].lowercase()

        return when (action) {
            "tabung", "simpan" -> processDeposit(userFile, amountStr)
            "tarik", "ambil" -> processWithdraw(userFile, amountStr)
            else -> Respon.bank.bank4
        }
    }

    @Synchronized
    private fun getBankStatus(userFile: File): String {
        return runCatching {
            val userJson = JSONObject(Const.readFile(userFile))
            val userId = userJson.optInt("id", 0)
            val walletCoin = userJson.optInt("koin", userJson.optInt("ep", 0))

            val bankFile = File(Const.getDatabaseDir(context), "IndexBankUser.json")
            val bankCoin = getBankBalance(bankFile, userId)

            Respon.bank.bank5.format(userId, walletCoin, bankCoin)
        }.getOrElse {
            Respon.bank.bank6
        }
    }

    @Synchronized
    private fun processDeposit(userFile: File, amountStr: String): String {
        return try {
            val userJson = JSONObject(Const.readFile(userFile))
            val userId = userJson.optInt("id", 0)
            val walletCoin = userJson.optInt("koin", userJson.optInt("ep", 0))

            val depositAmount = if (amountStr == "semua" || amountStr == "all") {
                walletCoin
            } else {
                amountStr.toInt()
            }

            if (depositAmount <= 0) {
                return Respon.bank.bank7
            }

            if (walletCoin < depositAmount) {
                return Respon.bank.bank8
            }

            val bankFile = File(Const.getDatabaseDir(context), "IndexBankUser.json")
            val currentBankCoin = getBankBalance(bankFile, userId)

            val newWalletCoin = walletCoin - depositAmount
            val newBankCoin = currentBankCoin + depositAmount

            userJson.put("koin", newWalletCoin)
            Const.writeFile(userFile, userJson.toString())

            saveBankBalance(bankFile, userId, newBankCoin)

            Respon.bank.bank9.format(depositAmount, newWalletCoin, newBankCoin, depositAmount)
        } catch (e: NumberFormatException) {
            Respon.bank.bank10
        } catch (e: Exception) {
            Respon.bank.bank11
        }
    }

    @Synchronized
    private fun processWithdraw(userFile: File, amountStr: String): String {
        return try {
            val userJson = JSONObject(Const.readFile(userFile))
            val userId = userJson.optInt("id", 0)
            val walletCoin = userJson.optInt("koin", userJson.optInt("ep", 0))

            val bankFile = File(Const.getDatabaseDir(context), "IndexBankUser.json")
            val currentBankCoin = getBankBalance(bankFile, userId)

            val withdrawAmount = if (amountStr == "semua" || amountStr == "all") {
                currentBankCoin
            } else {
                amountStr.toInt()
            }

            if (withdrawAmount < 15) {
                return Respon.bank.bank12
            }

            if (currentBankCoin < withdrawAmount) {
                return Respon.bank.bank13
            }

            val adminFee = 10
            val netReceived = withdrawAmount - adminFee

            val newBankCoin = currentBankCoin - withdrawAmount
            val newWalletCoin = walletCoin + netReceived

            userJson.put("koin", newWalletCoin)
            Const.writeFile(userFile, userJson.toString())

            saveBankBalance(bankFile, userId, newBankCoin)

            Respon.bank.bank14.format(withdrawAmount, adminFee, netReceived, newBankCoin, withdrawAmount, netReceived)
        } catch (e: NumberFormatException) {
            Respon.bank.bank10
        } catch (e: Exception) {
            Respon.bank.bank15
        }
    }

    private fun getBankBalance(bankFile: File, userId: Int): Int {
        if (!bankFile.exists()) return 0
        return runCatching {
            val json = JSONObject(Const.readFile(bankFile))
            json.optInt(userId.toString(), 0)
        }.getOrDefault(0)
    }

    private fun saveBankBalance(bankFile: File, userId: Int, newBalance: Int) {
        val json = if (bankFile.exists()) {
            JSONObject(Const.readFile(bankFile))
        } else {
            JSONObject()
        }
        json.put(userId.toString(), newBalance)
        Const.writeFile(bankFile, json.toString())
    }
}
