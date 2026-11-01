package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class MancingCommand constructor(
    private val context: Context? = null
) : Command {

    private data class Fish(
        val name: String,
        val price: Int,
        val isRare: Boolean
    )

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#fishm") ||
                lower.startsWith("#fishs") ||
                lower.startsWith("#fisho") ||
                lower.startsWith("#fishinv") ||
                lower.startsWith("#fish toko") ||
                lower.startsWith("#fish beli")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val lower = message.lowercase().trim()

        return when {
            lower.startsWith("#fish toko") -> handleFishToko()
            lower.startsWith("#fish beli") -> handleFishBeli(sender, message)
            lower.startsWith("#fishinv") -> handleFishInv(sender)
            lower.startsWith("#fishm") -> handleFishm(sender)
            lower.startsWith("#fishs") -> handleFishs(sender, message)
            lower.startsWith("#fisho") -> handleFisho(sender)
            else -> Respon.mancing.mancing1
        }
    }

    private fun handleFishToko(): String = Respon.mancing.mancing2

    private fun handleFishBeli(sender: String, message: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.mancing.mancing3

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            val itemKey = message.replace("#fish beli", "").trim().lowercase()
            var userKoin = userJson.optInt("koin", 0)

            val mancingData = userJson.optJSONObject("mancing_data") ?: getInitialMancingData()

            when (itemKey) {
                "joran_carbon" -> {
                    if (userKoin < 1000) return Respon.mancing.mancing4
                    userKoin -= 1000
                    mancingData.put("joran", "Joran Carbon")
                    mancingData.put("joran_bonus_rate", 15)
                }
                "joran_titanium" -> {
                    if (userKoin < 5000) return Respon.mancing.mancing5
                    userKoin -= 5000
                    mancingData.put("joran", "Joran Titanium Mitos")
                    mancingData.put("joran_bonus_rate", 35)
                }
                "umpan_cacing" -> {
                    if (userKoin < 150) return Respon.mancing.mancing6
                    userKoin -= 150
                    val currentUmpan = mancingData.optInt("umpan_cacing", 0)
                    mancingData.put("umpan_cacing", currentUmpan + 10)
                }
                "umpan_udang" -> {
                    if (userKoin < 400) return Respon.mancing.mancing7
                    userKoin -= 400
                    val currentUmpan = mancingData.optInt("umpan_udang", 0)
                    mancingData.put("umpan_udang", currentUmpan + 10)
                }
                else -> return Respon.mancing.mancing8
            }

            userJson.put("koin", userKoin)
            userJson.put("mancing_data", mancingData)

            Const.writeFile(userFile, userJson.toString())

            Respon.mancing.mancing9
        } catch (e: Exception) {
            Respon.mancing.mancing10
        }
    }

    private fun handleFishInv(sender: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.mancing.mancing3

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            val mancingData = userJson.optJSONObject("mancing_data") ?: getInitialMancingData()

            val joran = mancingData.optString("joran", "Joran Bambu")
            val umpanCacing = mancingData.optInt("umpan_cacing", 0)
            val umpanUdang = mancingData.optInt("umpan_udang", 0)

            val fishInventory = mancingData.optJSONObject("ikan") ?: JSONObject()

            val fishList = StringBuilder()
            if (fishInventory.length() == 0) {
                fishList.append(Respon.mancing.mancing11)
            } else {
                val keys = fishInventory.keys()
                var first = true
                while (keys.hasNext()) {
                    val key = keys.next()
                    val count = fishInventory.optInt(key, 0)
                    if (!first) fishList.append("\n")
                    first = false
                    fishList.append(Respon.mancing.mancing12.format(capitalizeWord(key), count))
                }
            }

            Respon.mancing.mancing13.format(joran, umpanCacing, umpanUdang, fishList.toString())
        } catch (e: Exception) {
            Respon.mancing.mancing14
        }
    }

    private fun handleFishm(sender: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) {
            return Respon.mancing.mancing15
        }

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return Respon.mancing.mancing16
            }

            if (userJson.optBoolean("in_guardian_battle", false)) {
                return Respon.mancing.mancing17
            }

            val mancingData = userJson.optJSONObject("mancing_data") ?: getInitialMancingData()

            val umpanCacing = mancingData.optInt("umpan_cacing", 0)
            val umpanUdang = mancingData.optInt("umpan_udang", 0)

            if (umpanCacing <= 0 && umpanUdang <= 0) {
                return Respon.mancing.mancing18
            }

            val usingUdang: Boolean
            if (umpanUdang > 0) {
                mancingData.put("umpan_udang", umpanUdang - 1)
                usingUdang = true
            } else {
                mancingData.put("umpan_cacing", umpanCacing - 1)
                usingUdang = false
            }

            val eventChance = Random.nextInt(100)

            if (eventChance < 5) {
                userJson.put("in_guardian_battle", true)
                userJson.put("guardian_hp", 1500)
                userJson.put("guardian_max_hp", 1500)
                userJson.put("guardian_atk", 150)
                userJson.put("mancing_data", mancingData)

                Const.writeFile(userFile, userJson.toString())

                return Respon.mancing.mancing19
            }

            val bonusRate = mancingData.optInt("joran_bonus_rate", 0)
            val catchSuccess = Random.nextInt(100)

            if (catchSuccess >= (30 + bonusRate)) {
                userJson.put("mancing_data", mancingData)
                Const.writeFile(userFile, userJson.toString())

                return Respon.mancing.mancing20
            }

            val caughtFish = getRandomFish(usingUdang)

            val fishInventory = mancingData.optJSONObject("ikan") ?: JSONObject()

            val fishKey = caughtFish.name.lowercase()
            val currentCount = fishInventory.optInt(fishKey, 0)
            fishInventory.put(fishKey, currentCount + 1)
            mancingData.put("ikan", fishInventory)
            userJson.put("mancing_data", mancingData)

            Const.writeFile(userFile, userJson.toString())

            val rarityBadge = if (caughtFish.isRare) "🌟 [LANGKA]" else "🐟 [UMUM]"
            Respon.mancing.mancing21.format(
                caughtFish.name, rarityBadge, caughtFish.price, currentCount + 1
            )
        } catch (e: Exception) {
            Respon.mancing.mancing22
        }
    }

    private fun handleFishs(sender: String, message: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.mancing.mancing3

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            if (userJson.optBoolean("in_guardian_battle", false)) {
                return Respon.mancing.mancing23
            }

            val paramsStr = message.replace("#fishs", "").trim()
            if (paramsStr.isEmpty()) {
                return Respon.mancing.mancing24
            }

            val parts = paramsStr.split(Regex("\\s+"))
            val fishNameInput: String
            var amountToSell = 1

            if (parts.size >= 2 && parts.last().matches(Regex("\\d+"))) {
                amountToSell = parts.last().toInt()
                fishNameInput = parts.dropLast(1).joinToString(" ").lowercase()
            } else {
                fishNameInput = paramsStr.lowercase()
            }

            if (amountToSell <= 0) {
                return Respon.mancing.mancing25
            }

            val mancingData = userJson.optJSONObject("mancing_data")
                ?: return Respon.mancing.mancing26

            val fishInventory = mancingData.optJSONObject("ikan")
            if (fishInventory == null || !fishInventory.has(fishNameInput)) {
                return Respon.mancing.mancing27.format(fishNameInput)
            }

            val currentOwned = fishInventory.optInt(fishNameInput, 0)
            if (currentOwned < amountToSell) {
                return Respon.mancing.mancing28.format(fishNameInput, currentOwned, amountToSell)
            }

            val pricePerFish = getFishPriceByName(fishNameInput)
            val totalEarnings = pricePerFish * amountToSell

            val newOwned = currentOwned - amountToSell
            if (newOwned > 0) {
                fishInventory.put(fishNameInput, newOwned)
            } else {
                fishInventory.remove(fishNameInput)
            }
            mancingData.put("ikan", fishInventory)
            userJson.put("mancing_data", mancingData)
            userJson.put("koin", userJson.optInt("koin", 0) + totalEarnings)

            Const.writeFile(userFile, userJson.toString())

            Respon.mancing.mancing29.format(
                amountToSell, capitalizeWord(fishNameInput), totalEarnings, newOwned
            )
        } catch (e: Exception) {
            Respon.mancing.mancing30
        }
    }

    private fun handleFisho(sender: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.mancing.mancing3

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            if (!userJson.optBoolean("in_guardian_battle", false)) {
                return Respon.mancing.mancing31
            }

            var guardianHp = userJson.optInt("guardian_hp", 1500)
            val guardianMaxHp = userJson.optInt("guardian_max_hp", 1500)
            val guardianAtk = userJson.optInt("guardian_atk", 150)

            var userHp = userJson.optInt("hp", 20)
            val userMaxHp = userJson.optInt("max_hp", 20)
            val userAtk = userJson.optInt("weapon_atk", userJson.optInt("weapon_dmg", 1))
            val userDef = userJson.optInt("armor_def", 0)

            guardianHp -= userAtk

            if (guardianHp <= 0) {
                userJson.put("in_guardian_battle", false)
                userJson.remove("guardian_hp")
                userJson.remove("guardian_max_hp")
                userJson.remove("guardian_atk")

                val mancingData = userJson.optJSONObject("mancing_data") ?: getInitialMancingData()
                val fishInventory = mancingData.optJSONObject("ikan") ?: JSONObject()

                fishInventory.put("ikan naga laut", fishInventory.optInt("ikan naga laut", 0) + 1)
                fishInventory.put("ikan hiu purba", fishInventory.optInt("ikan hiu purba", 0) + 1)
                fishInventory.put("ikan leviathan muda", fishInventory.optInt("ikan leviathan muda", 0) + 1)

                mancingData.put("ikan", fishInventory)
                userJson.put("mancing_data", mancingData)

                val items = userJson.optJSONObject("inventory") ?: JSONObject()
                items.put("Mutiara Laut", items.optInt("Mutiara Laut", 0) + 1)
                userJson.put("inventory", items)

                Const.writeFile(userFile, userJson.toString())

                return Respon.mancing.mancing32
            }

            userJson.put("guardian_hp", guardianHp)

            var netDamage = guardianAtk - (guardianAtk * userDef / 100)
            if (netDamage < 1) netDamage = 1

            userHp -= netDamage

            if (userHp <= 0) {
                userJson.put("hp", 0)
                userJson.put("dead", true)
                userJson.put("in_guardian_battle", false)
                userJson.remove("guardian_hp")
                userJson.remove("guardian_max_hp")
                userJson.remove("guardian_atk")

                Const.writeFile(userFile, userJson.toString())

                return Respon.mancing.mancing33.format(netDamage)
            }

            userJson.put("hp", userHp)
            Const.writeFile(userFile, userJson.toString())

            Respon.mancing.mancing34.format(
                userAtk, netDamage, guardianHp, guardianMaxHp, userHp, userMaxHp
            )
        } catch (e: Exception) {
            Respon.mancing.mancing35
        }
    }

    private fun getInitialMancingData(): JSONObject {
        return JSONObject().apply {
            put("joran", "Joran Bambu")
            put("joran_bonus_rate", 0)
            put("umpan_cacing", 5)
            put("umpan_udang", 0)
            put("ikan", JSONObject())
        }
    }

    private fun getRandomFish(isUdang: Boolean): Fish {
        val rareThreshold = if (isUdang) 40 else 20
        val rareChance = Random.nextInt(100)

        return if (rareChance < rareThreshold) {
            val rareFishList = arrayOf(
                Fish("Ikan Mas Mahkota", 800, true),
                Fish("Ikan Pari Listrik Mitos", 1200, true),
                Fish("Ikan Arwana Emas", 1500, true)
            )
            rareFishList.random()
        } else {
            val commonFishList = arrayOf(
                Fish("Ikan Lele", 30, false),
                Fish("Ikan Nila", 45, false),
                Fish("Ikan Gurame", 60, false),
                Fish("Ikan Bawal", 50, false),
                Fish("Ikan Mas", 40, false),
                Fish("Ikan Mujair", 35, false),
                Fish("Ikan Kakap", 90, false),
                Fish("Ikan Tongkol", 75, false),
                Fish("Ikan Kembung", 25, false),
                Fish("Ikan Tenggiri", 110, false),
                Fish("Ikan Patin", 55, false),
                Fish("Ikan Bandeng", 40, false),
                Fish("Ikan Tuna", 130, false),
                Fish("Ikan Salmon", 160, false),
                Fish("Ikan Gabus", 65, false)
            )
            commonFishList.random()
        }
    }

    private fun getFishPriceByName(fishName: String): Int {
        return when (fishName.lowercase()) {
            "ikan lele" -> 30
            "ikan nila" -> 45
            "ikan gurame" -> 60
            "ikan bawal" -> 50
            "ikan mas" -> 40
            "ikan mujair" -> 35
            "ikan kakap", "kakap" -> 90
            "ikan tongkol" -> 75
            "ikan kembung" -> 25
            "ikan tenggiri" -> 110
            "ikan patin" -> 55
            "ikan bandeng" -> 40
            "ikan tuna" -> 130
            "ikan salmon" -> 160
            "ikan gabus" -> 65
            "ikan mas mahkota" -> 800
            "ikan pari listrik mitos" -> 1200
            "ikan arwana emas" -> 1500
            "ikan naga laut" -> 5000
            "ikan hiu purba" -> 4500
            "ikan leviathan muda" -> 6000
            else -> 20
        }
    }

    private fun capitalizeWord(str: String): String {
        return str.split(Regex("\\s+")).joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
    }
}
