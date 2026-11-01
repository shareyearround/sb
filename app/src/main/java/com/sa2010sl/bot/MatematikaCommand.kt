package com.sa2010sl.bot

class MatematikaCommand : Command {

    override fun canHandle(message: String): Boolean {
        val lower = message.lowercase().trim()
        return lower.startsWith("#tambah") ||
                lower.startsWith("#kurang") ||
                lower.startsWith("#kali") ||
                lower.startsWith("#bagi")
    }

    override fun execute(
        sender: String,
        message: String,
        isGroup: Boolean,
        groupName: String?
    ): String? {
        val parts = message.trim().split(Regex("\\s+"))

        if (parts.size < 3) {
            return Respon.matematika.matematika1
        }

        val command = parts[0].lowercase()

        val num1 = parts[1].toDoubleOrNull()
        val num2 = parts[2].toDoubleOrNull()

        if (num1 == null || num2 == null) {
            return Respon.matematika.matematika5
        }

        val (result, label) = when (command) {
            "#tambah" -> (num1 + num2) to "Tambah"
            "#kurang" -> (num1 - num2) to "Kurang"
            "#kali" -> (num1 * num2) to "Kali"
            "#bagi" -> {
                if (num2 == 0.0) {
                    return Respon.matematika.matematika2
                }
                (num1 / num2) to "Bagi"
            }
            else -> return Respon.matematika.matematika3
        }

        return Respon.matematika.matematika4.format(
            formatNumber(num1),
            getOperatorSymbol(command),
            formatNumber(num2),
            label,
            formatNumber(result)
        )
    }

    private fun getOperatorSymbol(cmd: String): String {
        return when (cmd) {
            "#tambah" -> "+"
            "#kurang" -> "-"
            "#kali" -> "×"
            "#bagi" -> "÷"
            else -> ""
        }
    }

    private fun formatNumber(number: Double): String {
        return if (number == number.toLong().toDouble()) {
            number.toLong().toString()
        } else {
            String.format("%.2f", number)
        }
    }
}
