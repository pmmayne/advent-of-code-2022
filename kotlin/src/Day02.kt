import Utils.Companion.readLinesFromResourceFile

class Day02 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val rounds =
                    readLinesFromResourceFile("day02")
                            .sumOf { it.toGame().toScore() }
            println(rounds)
        }
    }

}

private fun String.toGame(): String {
    return when (this) {
        "A X" -> {
            "A Z"
        }
        "A Y" -> {
            "A X"
        }
        "A Z" -> {
            "A Y"
        }
        "B X" -> {
            "B X"
        }
        "B Y" -> {
            "B Y"
        }
        "B Z" -> {
            "B Z"
        }
        "C X" -> {
            "C Y"
        }
        "C Y" -> {
            "C Z"
        }
        "C Z" -> {
            "C X"
        }
        else -> ""
    }
}

private fun String.toScore(): Int {
    return when (this) {
        "A X" -> { // Rock vs Rock
            3 + 1
        }
        "A Y" -> { // Rock vs Paper
            6 + 2
        }
        "A Z" -> { // Rock vs Scissors
            0 + 3
        }
        "B X" -> { // Paper vs Rock
            0 + 1
        }
        "B Y" -> { // Paper vs Paper
            3 + 2
        }
        "B Z" -> { // Paper vs Scissors
            6 + 3
        }
        "C X" -> { // Scissors vs Rock
            6 + 1
        }
        "C Y" -> { // Scissors vs Paper
            0 + 2
        }
        "C Z" -> { // Scissors vs Scissors
            3 + 3
        }
        else -> 0
    }

}

