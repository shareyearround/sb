package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class RaidCommand constructor(
    private val context: Context? = null
) : Command {

    private data class Monster(
        val id: Int,
        val name: String,
        val maxHp: Int,
        val baseDmg: Int,
        val itemName: String,
        val isEquipment: Boolean,
        val itemType: String,
        val itemVal: Int,
        val abilityDesc: String
    )

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#raidm") || lower.startsWith("#raids")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val lower = message.lowercase().trim()

        return when {
            lower.startsWith("#raidm") -> handleRaidMatch(sender)
            lower.startsWith("#raids") -> handleRaidAttack(sender)
            else -> Respon.raid.raid1
        }
    }

    private fun handleRaidMatch(sender: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) {
            return Respon.raid.raid2
        }

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                return Respon.raid.raid3
            }

            val inRaid = userJson.optBoolean("in_raid_battle", false)
            if (inRaid) {
                val currentBoss = userJson.optString("raid_boss_name", "Makhluk Mitos")
                val currentHp = userJson.optInt("raid_boss_hp", 0)
                return Respon.raid.raid4.format(currentBoss, currentHp)
            }

            val selectedBoss = getRandomMonster()

            userJson.apply {
                put("in_raid_battle", true)
                put("raid_boss_id", selectedBoss.id)
                put("raid_boss_name", selectedBoss.name)
                put("raid_boss_hp", selectedBoss.maxHp)
                put("raid_boss_max_hp", selectedBoss.maxHp)
                put("raid_boss_base_dmg", selectedBoss.baseDmg)
            }

            Const.writeFile(userFile, userJson.toString())

            Respon.raid.raid5.format(
                selectedBoss.name,
                selectedBoss.maxHp,
                selectedBoss.baseDmg,
                selectedBoss.itemName,
                selectedBoss.abilityDesc
            )
        } catch (e: Exception) {
            Respon.raid.raid6
        }
    }

    private fun handleRaidAttack(sender: String): String {
        val cleanSender = Const.sanitizeUsername(sender)
        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanSender.json")

        if (!userFile.exists()) return Respon.raid.raid7

        return try {
            val content = Const.readFile(userFile)
            val userJson = JSONObject(content)

            if (!userJson.optBoolean("in_raid_battle", false)) {
                return Respon.raid.raid8
            }

            if (userJson.optBoolean("dead", false) || userJson.optInt("hp", 0) <= 0) {
                clearRaidSession(userJson)
                Const.writeFile(userFile, userJson.toString())
                return Respon.raid.raid9
            }

            val bossId = userJson.optInt("raid_boss_id", 1)
            val bossName = userJson.optString("raid_boss_name", "Makhluk Mitos")
            var bossHp = userJson.optInt("raid_boss_hp", 100)
            val bossMaxHp = userJson.optInt("raid_boss_max_hp", 100)
            val bossBaseDmg = userJson.optInt("raid_boss_base_dmg", 10)

            var userHp = userJson.optInt("hp", 20)
            val userMaxHp = userJson.optInt("max_hp", 20)
            var userAtk = userJson.optInt("weapon_atk", userJson.optInt("weapon_dmg", 1))
            val userDef = userJson.optInt("armor_def", 0)

            val resultMsg = StringBuilder()

            if (bossId == 10) {
                val anosAction = when (Random.nextInt(3)) {
                    0 -> Respon.raid.raid10
                    1 -> Respon.raid.raid11
                    else -> Respon.raid.raid12
                }

                userJson.put("hp", 0)
                userJson.put("dead", true)

                clearRaidSession(userJson)
                Const.writeFile(userFile, userJson.toString())

                return Respon.raid.raid13.format(anosAction)
            }

            var isMiss = false
            var playerDamage = userAtk

            if (bossId == 3 && Random.nextInt(100) < 30) {
                isMiss = true
                playerDamage = 0
            }

            if (bossId == 5) {
                playerDamage -= (playerDamage * 0.40).toInt()
                if (playerDamage < 1) playerDamage = 1
            }

            if (isMiss) {
                resultMsg.append(Respon.raid.raid14.format(bossName))
            } else {
                bossHp -= playerDamage
                resultMsg.append(Respon.raid.raid15.format(bossName, playerDamage))
            }

            if (bossHp <= 0) {
                val defeatedBoss = getMonsterById(bossId)
                clearRaidSession(userJson)

                if (defeatedBoss.isEquipment) {
                    if (defeatedBoss.itemType.equals("Weapon", ignoreCase = true)) {
                        userJson.put("weapon", defeatedBoss.itemName)
                        userJson.put("weapon_atk", defeatedBoss.itemVal)
                    } else if (defeatedBoss.itemType.equals("Armor", ignoreCase = true)) {
                        userJson.put("armor", defeatedBoss.itemName)
                        userJson.put("armor_def", defeatedBoss.itemVal)
                    }
                }

                val bonusKoin = Random.nextInt(100, 301)
                val bonusExp = Random.nextInt(150, 401)

                userJson.put("koin", userJson.optInt("koin", 0) + bonusKoin)
                userJson.put("exp", userJson.optInt("exp", 0) + bonusExp)

                Const.writeFile(userFile, userJson.toString())

                return Respon.raid.raid16.format(
                    bossName, defeatedBoss.itemName, bonusKoin, bonusExp
                )
            }

            userJson.put("raid_boss_hp", bossHp)

            var monsterDamage = bossBaseDmg
            var specialEffect = ""

            when (bossId) {
                1 -> {
                    if (Random.nextInt(100) < 50) {
                        monsterDamage += (monsterDamage * 0.20).toInt()
                        specialEffect = Respon.raid.raid17
                    }
                    monsterDamage = applyStandardArmor(monsterDamage, userDef)
                }
                2 -> {
                    if (Random.nextInt(100) < 40) {
                        userAtk -= (userAtk * 0.30).toInt()
                        if (userAtk < 1) userAtk = 1
                        userJson.put("weapon_atk", userAtk)
                        specialEffect = Respon.raid.raid18
                    }
                    monsterDamage = applyStandardArmor(monsterDamage, userDef)
                }
                3 -> {
                    monsterDamage = (userMaxHp * 0.20).toInt()
                    specialEffect = Respon.raid.raid19
                }
                4 -> {
                    monsterDamage = (userMaxHp * 0.10).toInt()
                    specialEffect = Respon.raid.raid20
                }
                5 -> {
                    monsterDamage = (userMaxHp * 0.10).toInt()
                    specialEffect = Respon.raid.raid21
                }
                6 -> {
                    if (Random.nextInt(100) < 50) {
                        monsterDamage += 15
                        specialEffect = Respon.raid.raid22
                    }
                    monsterDamage = applyStandardArmor(monsterDamage, userDef)
                }
                7 -> {
                    specialEffect = Respon.raid.raid23
                    monsterDamage = applyStandardArmor(monsterDamage, userDef)
                }
                8 -> {
                    when (Random.nextInt(5)) {
                        0 -> {
                            monsterDamage = 90
                            specialEffect = Respon.raid.raid24
                        }
                        1 -> {
                            monsterDamage = 10
                            specialEffect = Respon.raid.raid25
                        }
                        2 -> {
                            monsterDamage = 200
                            specialEffect = Respon.raid.raid26
                        }
                        3 -> {
                            monsterDamage = 1
                            specialEffect = Respon.raid.raid27
                        }
                        else -> {
                            monsterDamage = 150
                            specialEffect = Respon.raid.raid28
                        }
                    }
                    monsterDamage = applyStandardArmor(monsterDamage, userDef)
                }
                9 -> {
                    specialEffect = Respon.raid.raid29
                    monsterDamage = applyStandardArmor(monsterDamage, userDef)
                }
            }

            userHp -= monsterDamage

            if (userHp <= 0) {
                userJson.put("hp", 0)
                userJson.put("dead", true)
                clearRaidSession(userJson)
                Const.writeFile(userFile, userJson.toString())

                return Respon.raid.raid30.format(
                    resultMsg.toString(), bossName, monsterDamage, specialEffect
                )
            }

            userJson.put("hp", userHp)
            Const.writeFile(userFile, userJson.toString())

            Respon.raid.raid31.format(
                resultMsg.toString(),
                bossName,
                monsterDamage,
                specialEffect,
                bossHp,
                bossMaxHp,
                userHp,
                userMaxHp
            )
        } catch (e: Exception) {
            Respon.raid.raid32
        }
    }

    private fun applyStandardArmor(damage: Int, armorDef: Int): Int {
        val reduced = damage - (damage * armorDef / 100)
        return if (reduced < 1) 1 else reduced
    }

    private fun clearRaidSession(userJson: JSONObject) {
        userJson.apply {
            put("in_raid_battle", false)
            remove("raid_boss_id")
            remove("raid_boss_name")
            remove("raid_boss_hp")
            remove("raid_boss_max_hp")
            remove("raid_boss_base_dmg")
        }
    }

    private fun getRandomMonster(): Monster {
        val monsters = getAllMonsters()
        return monsters.random()
    }

    private fun getMonsterById(id: Int): Monster {
        val monsters = getAllMonsters()
        return monsters.firstOrNull { it.id == id } ?: monsters[0]
    }

    private fun getAllMonsters(): Array<Monster> {
        return arrayOf(
            Monster(
                1,
                "NEXE",
                80,
                40,
                "Tombak Nexe (35 DMG)",
                false,
                "Weapon",
                35,
                "Memiliki efek lemparan tombak dengan bonus +20% ekstra damage."
            ),
            Monster(
                2,
                "KALIFOX",
                100,
                60,
                "Bulu Kalifox",
                false,
                "Material",
                0,
                "Dapat memberikan Gigitan Terkutuk yang mengurangi ATK senjata user sebesar 30%."
            ),
            Monster(
                3,
                "GREYARD",
                7000,
                500,
                "Batu Magma",
                false,
                "Material",
                0,
                "Dapat menahan/membuat serangan MISS, serta Semburan Magma menembus Armor (Efek terbakar 20% Max HP)."
            ),
            Monster(
                4,
                "ZETER DRAGON",
                100,
                200,
                "Sisik Zeter",
                false,
                "Material",
                0,
                "Semburan nafas naga yang menembus armor sekuat apapun (10% Max HP Damage)."
            ),
            Monster(
                5,
                "BAYI ZETER DRAGON",
                20,
                5,
                "Sisik Zeter",
                false,
                "Material",
                0,
                "Memiliki pelindung yang memblokir 40% serangan, serta Bola Api yang menembus armor 10%."
            ),
            Monster(
                6,
                "KUARK",
                10,
                1,
                "Zamrud",
                false,
                "Material",
                0,
                "Mematung lalu melempar batu beruntun untuk menghasilkan damage ekstra."
            ),
            Monster(
                7,
                "👑 KING DEMONIAC",
                1000,
                400,
                "Zirah Demoniac (+50 DEF)",
                true,
                "Armor",
                50,
                "Memiliki HP raksasa dan defense tinggi, menyerang dengan pukulan bayangan."
            ),
            Monster(
                8,
                "🔮 ELAINA",
                100,
                0,
                "Tongkat Sihir (+50 DMG)",
                true,
                "Weapon",
                50,
                "Menyerang secara acak dengan Sihir Api (90), Air (10), Bola Sihir (200), Sayur (1), atau TNT (150)."
            ),
            Monster(
                9,
                "SOIN",
                20,
                10,
                "Ranting Pohon",
                false,
                "Material",
                0,
                "Menyerang dengan melemparkan kayu runcing."
            ),
            Monster(
                10,
                "🩸 Anos Voldigoad",
                999999,
                999999,
                "Bola Kematian (+9999 DMG)",
                true,
                "Weapon",
                9999,
                "Mustahil dilawan! Menghancurkan lawan secara instan tanpa ampun."
            )
        )
    }
}
