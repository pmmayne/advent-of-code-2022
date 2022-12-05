import Utils.Companion.readLinesFromResourceFile

class Day04 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = readLinesFromResourceFile("day04")
                    .map { it.split(",")[0] to it.split(",")[1] }
                    .map { it.first.toEnsemble() to it.second.toEnsemble() }.count { it.doOverLap() }
            println(input)
        }
    }
}

private fun Pair<Pair<Int, Int>, Pair<Int, Int>>.doOverLap(): Boolean {
    val pairOne = this.first
    val pairTwo = this.second
    return !(pairOne.first > pairTwo.second || pairOne.second < pairTwo.first)
}

private fun Pair<Pair<Int, Int>, Pair<Int, Int>>.oneComprisesTheOther(): Boolean {
    val pairOne = this.first
    val pairTwo = this.second
    return (pairOne.first <= pairTwo.first && pairOne.second >= pairTwo.second) ||
            (pairTwo.first <= pairOne.first && pairTwo.second >= pairOne.second)
}

private fun String.toEnsemble(): Pair<Int, Int> {
    return this.split("-")[0].toInt() to this.split("-")[1].toInt()
}
