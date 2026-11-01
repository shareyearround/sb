package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class SerangCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim() == "#serang"
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val cleanName = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) {
            return Respon.serang.serang1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            var userHp = userJson.optInt("hp", 20)
            val userMaxHp = userJson.optInt("max_hp", 20)

            if (!userJson.has("dead")) {
                userJson.put("dead", userHp <= 0)
            }

            val isDead = userJson.optBoolean("dead", false)

            if (userHp <= 0 || isDead) {
                userJson.put("hp", 0)
                userJson.put("dead", true)
                Const.writeFile(userFile, userJson.toString())
                return Respon.serang.serang2.format(userMaxHp)
            }

            val inBattle = userJson.optBoolean("in_battle", false)
            if (!inBattle) {
                return Respon.serang.serang3
            }

            val weaponName = userJson.optString("weapon", "Tinju")
            val weaponAtk = userJson.optInt("weapon_atk", 1)

            val armorName = userJson.optString("armor", "Tanpa Armor")
            val armorDef = userJson.optInt("armor_def", 0)

            val monsterName = userJson.optString("monster_name", "Monster")
            var monsterHp = userJson.optInt("monster_hp", 10)
            val monsterMaxHp = userJson.optInt("monster_max_hp", 10)
            val rawMonsterAtk = userJson.optInt("monster_atk", 2)
            val monsterExpReward = userJson.optInt("monster_exp", 15)
            val monsterKoinReward = userJson.optInt("monster_koin", 20)
            val isBossSpecial = userJson.optBoolean("is_boss_special", false)

            monsterHp -= weaponAtk

            if (monsterHp <= 0) {
                monsterHp = 0
                userJson.put("in_battle", false)
                userJson.remove("monster_name")
                userJson.remove("monster_hp")
                userJson.remove("monster_max_hp")
                userJson.remove("monster_atk")
                userJson.remove("monster_exp")
                userJson.remove("monster_koin")
                userJson.remove("is_boss_special")

                var currentExp = userJson.optInt("exp", 0) + monsterExpReward
                var currentKoin = userJson.optInt("koin", 0) + monsterKoinReward

                var bossDropInfo = ""

                if (isBossSpecial) {
                    val mKey = monsterName.lowercase().replace(Regex("[^a-z0-9]"), "_")
                    val flagKey = "boss_drop_$mKey"
                    val alreadyHasDrop = userJson.optBoolean(flagKey, false)

                    if (alreadyHasDrop) {
                        val bonusKoin = 500
                        val bonusExp = 1000
                        currentKoin += bonusKoin
                        currentExp += bonusExp
                        bossDropInfo = Respon.serang.serang4.format(monsterName, bonusKoin, bonusExp)
                    } else {
                        userJson.put(flagKey, true)
                        val isWeapon = Random.nextBoolean()

                        if (isWeapon) {
                            val wAtk = Random.nextInt(100, 151)
                            val wName = when {
                                monsterName.contains("Grava") -> "Tongkat Dewi Grava"
                                monsterName.contains("Tasur") -> "Palu Golem Tasur"
                                else -> "Sabit Reality C-π"
                            }

                            val oldAtk = userJson.optInt("weapon_atk", 1)
                            if (wAtk > oldAtk) {
                                userJson.put("weapon", wName)
                                userJson.put("weapon_atk", wAtk)
                                bossDropInfo = Respon.serang.serang5.format(wName, wAtk)
                            } else {
                                bossDropInfo = Respon.serang.serang6.format(wName, wAtk)
                            }
                        } else {
                            val aDef = Random.nextInt(40, 91)
                            val aName = when {
                                monsterName.contains("Grava") -> "Zirah Dewi Grava"
                                monsterName.contains("Tasur") -> "Perisai Golem Tasur"
                                else -> "Jubah Void C-π"
                            }

                            val oldDef = userJson.optInt("armor_def", 0)
                            if (aDef > oldDef) {
                                userJson.put("armor", aName)
                                userJson.put("armor_def", aDef)
                                bossDropInfo = Respon.serang.serang7.format(aName, aDef)
                            } else {
                                bossDropInfo = Respon.serang.serang8.format(aName, aDef)
                            }
                        }
                    }
                }

                userJson.put("exp", currentExp)
                userJson.put("koin", currentKoin)

                Const.writeFile(userFile, userJson.toString())

                return Respon.serang.serang9.format(
                    monsterName,
                    weaponName,
                    weaponAtk,
                    monsterName,
                    monsterKoinReward,
                    monsterExpReward,
                    bossDropInfo
                )
            }

            var netMonsterAtk = rawMonsterAtk - (rawMonsterAtk * armorDef / 100)
            if (netMonsterAtk < 1) {
                netMonsterAtk = 1
            }

            userHp -= netMonsterAtk

            if (userHp <= 0) {
                userHp = 0
                userJson.put("hp", 0)
                userJson.put("dead", true)
                userJson.put("in_battle", false)
                userJson.remove("monster_name")
                userJson.remove("monster_hp")
                userJson.remove("monster_max_hp")
                userJson.remove("monster_atk")
                userJson.remove("monster_exp")
                userJson.remove("monster_koin")
                userJson.remove("is_boss_special")

                val userKoin = userJson.optInt("koin", userJson.optInt("ep", 0))
                val kineyChance = Random.nextInt(100)

                val kineyDialog: String
                var koinLooted = false

                if (kineyChance < 30) {
                    val kindDialogs = arrayOf(
                        "Kiney: ( × ~ × * ) Berlumuran darah... Mungkin untuk bekal setelah respawn Human saja...",
                        "Kiney: ( 🥺 • ︵ • * ) Kasihan banget... Ney nggak tega ngambil koinnya kali ini...",
                        "Kiney: ( ಠ ╭╮ ಠ * ) Hmph! Ney lagi baik hati, simpan saja koinmu itu untuk berobat!"
                    )
                    kineyDialog = kindDialogs.random()
                } else {
                    if (userKoin > 0) {
                        val lootDialogs = arrayOf(
                            "Kiney: (! • ~ • * ) Uh... Kesempatan kaya...",
                            "Kiney: ( – ∆ – * ) ney ambil ya...",
                            "Kiney: ( 0 ∆ 0 * ) Koin³...",
                            "Kiney: ( > ~ < * ) Tragisnya, izin ney ambil ya :p...",
                            "Kiney: ( ✧ ω ✧ * ) Wah ada yang berkilau di kantongnya! Ney amankan dulu~",
                            "Kiney: ( ⊙ o ⊙ * ) Yey! Koin gratis~ Makasih Human!"
                        )
                        kineyDialog = lootDialogs.random()
                        userJson.put("koin", 0)
                        if (userJson.has("ep")) {
                            userJson.put("ep", 0)
                        }
                        koinLooted = true
                    } else {
                        kineyDialog = "Kiney: ( 😒 • ︵ • * ) Cih... Sudah mati, dompetnya kosong melompong pula..."
                    }
                }

                Const.writeFile(userFile, userJson.toString())

                val armorDamp = if (armorDef > 0) Respon.serang.serang10.format(armorDef, armorName) else ""
                val koinStatus = when {
                    koinLooted -> Respon.serang.serang11.format(userKoin)
                    userKoin > 0 -> Respon.serang.serang12.format(userKoin)
                    else -> ""
                }

                return Respon.serang.serang13.format(
                    monsterName, weaponAtk, monsterName, netMonsterAtk, armorDamp, kineyDialog, koinStatus
                )
            }

            userJson.put("hp", userHp)
            userJson.put("monster_hp", monsterHp)

            Const.writeFile(userFile, userJson.toString())

            val armorReduk = if (armorDef > 0) Respon.serang.serang14.format(armorDef) else ""

            Respon.serang.serang15.format(
                weaponAtk,
                weaponName,
                netMonsterAtk,
                monsterName,
                armorReduk,
                monsterName,
                monsterHp,
                monsterMaxHp,
                userHp,
                userMaxHp
            )
        } catch (e: Exception) {
            Respon.serang.serang16
        }
    }
}
