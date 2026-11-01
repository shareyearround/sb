package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File
import kotlin.random.Random

class JelajahCommand constructor(
    private val context: Context? = null
) : Command {

    private data class MonsterData(
        val name: String,
        val hp: Int,
        val atk: Int,
        val exp: Int,
        val koin: Int,
        val isBossSpecial: Boolean = false
    )

    private data class FoodDrop(
        val key: String,
        val name: String,
        val emoji: String,
        val healValue: Int,
        val qty: Int
    )

    private data class MaterialDrop(
        val key: String,
        val name: String,
        val emoji: String,
        val value: Int,
        val qty: Int
    )

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim() == "#jelajah"
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
            return Respon.jelajah.jelajah1
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val currentEp = userJson.optInt("ep", 0)
            val epCost = 3

            if (currentEp < epCost) {
                return Respon.jelajah.jelajah2.format(epCost, currentEp)
            }

            val userHp = userJson.optInt("hp", 20)
            val userMaxHp = userJson.optInt("max_hp", 20)

            if (!userJson.has("dead")) {
                userJson.put("dead", userHp <= 0)
            }

            val isDead = userJson.optBoolean("dead", false)

            if (userHp <= 0 || isDead) {
                userJson.put("hp", 0)
                userJson.put("dead", true)
                Const.writeFile(userFile, userJson.toString())

                return Respon.jelajah.jelajah3.format(userMaxHp)
            }

            val inBattle = userJson.optBoolean("in_battle", false)
            if (inBattle) {
                val activeMonster = userJson.optString("monster_name", "Monster")
                return Respon.jelajah.jelajah4.format(activeMonster)
            }

            val remainingEp = currentEp - epCost
            userJson.put("ep", remainingEp)

            val roll = Random.nextInt(100)

            when {
                roll < 10 -> {
                    val specialRoll = Random.nextInt(100)
                    val monster = if (specialRoll < 70) {
                        val specialMonsters = arrayOf("Arkolgo", "Inea", "AvoidZ", "Krasion", "Dragon Vanatix")
                        MonsterData(
                            name = specialMonsters.random(),
                            hp = Random.nextInt(500, 701),
                            atk = Random.nextInt(200, 401),
                            exp = Random.nextInt(400, 701),
                            koin = Random.nextInt(100, 301),
                            isBossSpecial = false
                        )
                    } else {
                        when (Random.nextInt(3)) {
                            0 -> MonsterData("Dewi Grava", 5000, 50, 1500, 500, true)
                            1 -> MonsterData("Golem Tasur", 100, 100, 1000, 300, true)
                            else -> MonsterData("Entity C-*π~", 15000, 1000, 5000, 2000, true)
                        }
                    }

                    userJson.apply {
                        put("in_battle", true)
                        put("monster_name", monster.name)
                        put("monster_hp", monster.hp)
                        put("monster_max_hp", monster.hp)
                        put("monster_atk", monster.atk)
                        put("monster_exp", monster.exp)
                        put("monster_koin", monster.koin)
                        put("is_boss_special", monster.isBossSpecial)
                    }

                    Const.writeFile(userFile, userJson.toString())

                    Respon.jelajah.jelajah5.format(
                        sender, remainingEp, monster.name, monster.hp, monster.hp, monster.atk
                    )
                }

                roll < 40 -> {
                    val monsterNames = arrayOf("Slime Hijau", "Serigala Hutan", "Goblin", "Orc Muda")
                    val index = Random.nextInt(monsterNames.size)
                    val monsterName = monsterNames[index]

                    val monster = when (index) {
                        0 -> MonsterData(monsterName, 10, 2, 12, 15)
                        1 -> MonsterData(monsterName, 16, 3, 20, 25)
                        2 -> MonsterData(monsterName, 22, 4, 30, 35)
                        else -> MonsterData(monsterName, 30, 6, 45, 50)
                    }

                    userJson.apply {
                        put("in_battle", true)
                        put("monster_name", monster.name)
                        put("monster_hp", monster.hp)
                        put("monster_max_hp", monster.hp)
                        put("monster_atk", monster.atk)
                        put("monster_exp", monster.exp)
                        put("monster_koin", monster.koin)
                        put("is_boss_special", false)
                    }

                    Const.writeFile(userFile, userJson.toString())

                    Respon.jelajah.jelajah6.format(
                        sender, remainingEp, monster.name, monster.hp, monster.hp, monster.atk
                    )
                }

                roll < 70 -> {
                    val foodRoll = Random.nextInt(100)
                    val food = when {
                        foodRoll < 50 -> FoodDrop("buah", "Buah Segar", "🍎", 5, Random.nextInt(1, 3))
                        foodRoll < 85 -> FoodDrop("sayur", "Sayuran Hutan", "🥗", 10, Random.nextInt(1, 3))
                        else -> FoodDrop("madu", "Madu Hutan", "🍯", 25, 1)
                    }

                    val oldQty = userJson.optInt(food.key, 0)
                    val newQty = oldQty + food.qty
                    userJson.put(food.key, newQty)

                    Const.writeFile(userFile, userJson.toString())

                    Respon.jelajah.jelajah7.format(
                        sender, remainingEp, food.emoji, food.name, food.qty, food.healValue, newQty
                    )
                }

                roll < 88 -> {
                    val matRoll = Random.nextInt(100)
                    val material = when {
                        matRoll < 40 -> MaterialDrop("kayu", "Kayu Unik", "🪵", 5, Random.nextInt(2, 5))
                        matRoll < 70 -> MaterialDrop("tembaga", "Bijih Tembaga", "🪨", 15, Random.nextInt(1, 3))
                        matRoll < 90 -> MaterialDrop("zamrud", "Batu Zamrud", "❇️", 50, 1)
                        else -> MaterialDrop("berlian", "Berlian Murni", "💎", 100, 1)
                    }

                    val oldQty = userJson.optInt(material.key, 0)
                    val newQty = oldQty + material.qty
                    userJson.put(material.key, newQty)

                    Const.writeFile(userFile, userJson.toString())

                    Respon.jelajah.jelajah8.format(
                        sender, remainingEp, material.emoji, material.name, material.qty, material.value, newQty
                    )
                }

                else -> {
                    val wpnRoll = Random.nextInt(100)
                    val (newWpnName, newWpnAtk) = when {
                        wpnRoll < 50 -> "Pisau Berburu" to 3
                        wpnRoll < 85 -> "Pedang Besi" to 5
                        else -> "Tombak Emas" to 8
                    }

                    val currentWpnAtk = userJson.optInt("weapon_atk", 1)
                    val currentWpnName = userJson.optString("weapon", "Tinju")

                    val statusMsg = if (newWpnAtk > currentWpnAtk) {
                        userJson.put("weapon", newWpnName)
                        userJson.put("weapon_atk", newWpnAtk)
                        Respon.jelajah.jelajah9.format(currentWpnName, currentWpnAtk)
                    } else {
                        Respon.jelajah.jelajah10.format(currentWpnName, currentWpnAtk)
                    }

                    Const.writeFile(userFile, userJson.toString())

                    Respon.jelajah.jelajah11.format(
                        sender, remainingEp, newWpnName, newWpnAtk, statusMsg
                    )
                }
            }
        } catch (e: Exception) {
            Respon.jelajah.jelajah12
        }
    }
}
