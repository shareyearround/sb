package com.sa2010sl.bot

import android.content.Context
import org.json.JSONObject
import java.io.File

class PakaiCommand constructor(
    private val context: Context? = null
) : Command {

    override fun canHandle(message: String): Boolean {
        return message.lowercase().trim().startsWith("#pakai")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val parts = message.trim().split(Regex("\\s+"), 2)
        if (parts.size < 2) {
            return Respon.pakai.pakai1
        }

        val targetItemName = parts[1].trim()
        val cleanName = Const.sanitizeUsername(sender)

        val baseDir = Const.getDatabaseDir(context)
        val userFile = File(baseDir, "$cleanName.json")

        if (!userFile.exists()) {
            return Respon.pakai.pakai2
        }

        return try {
            val userContent = Const.readFile(userFile)
            val userJson = JSONObject(userContent)

            val inventory = userJson.optJSONArray("inventory")
            if (inventory == null || inventory.length() == 0) {
                return Respon.pakai.pakai3
            }

            var foundIndex = -1
            var targetItem: JSONObject? = null

            for (i in 0 until inventory.length()) {
                val item = inventory.getJSONObject(i)
                val itemName = item.optString("name", "")
                if (itemName.equals(targetItemName, ignoreCase = true)) {
                    foundIndex = i
                    targetItem = item
                    break
                }
            }

            if (foundIndex == -1 || targetItem == null) {
                return Respon.pakai.pakai4.format(targetItemName)
            }

            val itemName = targetItem.optString("name", "Unknown Item")
            val itemType = targetItem.optString("type", "")
            val itemValue = targetItem.optInt("value", 0)

            if (!itemType.equals("Weapon", ignoreCase = true) && !itemType.equals("Armor", ignoreCase = true)) {
                return Respon.pakai.pakai5.format(itemName, itemType)
            }

            inventory.remove(foundIndex)

            if (itemType.equals("Weapon", ignoreCase = true)) {
                val oldWeapon = userJson.optString("weapon", "Tinju")
                val oldWeaponAtk = userJson.optInt("weapon_atk", 1)

                var returnedMsg = ""
                if (!oldWeapon.equals("Tinju", ignoreCase = true)) {
                    val oldWeaponObj = JSONObject().apply {
                        put("name", oldWeapon)
                        put("type", "Weapon")
                        put("value", oldWeaponAtk)
                    }
                    inventory.put(oldWeaponObj)
                    returnedMsg = Respon.pakai.pakai6.format(oldWeapon)
                }

                userJson.put("weapon", itemName)
                userJson.put("weapon_atk", itemValue)
                userJson.put("inventory", inventory)

                Const.writeFile(userFile, userJson.toString())

                Respon.pakai.pakai7.format(sender, itemName, itemValue, returnedMsg)
            } else {
                val oldArmor = userJson.optString("armor", "Tanpa Armor")
                val oldArmorDef = userJson.optInt("armor_def", 0)

                var returnedMsg = ""
                if (!oldArmor.equals("Tanpa Armor", ignoreCase = true)) {
                    val oldArmorObj = JSONObject().apply {
                        put("name", oldArmor)
                        put("type", "Armor")
                        put("value", oldArmorDef)
                    }
                    inventory.put(oldArmorObj)
                    returnedMsg = Respon.pakai.pakai6.format(oldArmor)
                }

                userJson.put("armor", itemName)
                userJson.put("armor_def", itemValue)
                userJson.put("inventory", inventory)

                Const.writeFile(userFile, userJson.toString())

                Respon.pakai.pakai8.format(sender, itemName, itemValue, returnedMsg)
            }
        } catch (e: Exception) {
            Respon.pakai.pakai9
        }
    }
}
