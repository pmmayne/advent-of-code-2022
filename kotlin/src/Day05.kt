import Utils.Companion.readLinesFromResourceFile

class Day05 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = readLinesFromResourceFile("day05")
            var isCrate = true
            val stacks = mutableListOf<ArrayDeque<Crate>>()
            for(i in 0..8) {
                stacks.add(ArrayDeque())
            }
            val moves = mutableListOf<Move>()
            for (line in input) {
                if (line == "") {
                    isCrate = false
                } else if (isCrate && line.contains("[")) {
                    var crateName = ""
                    for (i in line.indices) {
                        crateName += line[i]
                        if ((i + 2) % 4 == 0 && crateName != "   ") {
                            val crate = Crate(crateName)
                            stacks[i / 4].addLast(crate)
                        } else if ((i + 2) % 4 == 1) {
                            crateName = ""
                        }
                    }
                } else if (!isCrate) {
                    moves.add(line.toMoves())
                }
            }
            for(deck in stacks) {
                println(deck)
            }

            for(move in moves) {
                val crates = ArrayDeque<Crate>()
                for(i in 0 until move.quantity){
                    crates.addFirst(stacks[move.from].removeFirst())
                }
                for(crate in crates) {
                    stacks[move.to].addFirst(crate)
                }

            }
            var result = ""
            for(deck in stacks) {
                if(deck.isNotEmpty()){
                    result += deck.first().nameParsed
                }

            }
            println("result:")
            println(result)
        }
    }

    data class Crate(val name: String, val nameParsed: Char = name[1])
}

private fun String.toMoves(): Move {
    //move 1 from 2 to 1
    val moves = mutableListOf<Move>()
    println("move: $this")
    val split = this.split(" ")
    return Move(split[3].toInt() - 1, split[5].toInt() - 1, split[1].toInt())

}

data class Move(val from: Int, val to: Int, val quantity: Int)
