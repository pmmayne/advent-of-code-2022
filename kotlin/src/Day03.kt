import Utils.Companion.readLinesFromResourceFile

class Day03 {

    companion object {
        @JvmStatic
        fun main1(args: Array<String>) {
            val input = readLinesFromResourceFile("day03")

            val sumOf = input.map { it.substring(0, it.length / 2) to it.substring(it.length / 2, it.length) }
                    // .map{println(it)
                    // it}
                    .map { (a, b) ->
                        a.filter { c -> b.contains(c) }
                    }
                    .map {
                        println(it)
                        it
                    }
                    .sumOf { it.toPriority() }

            println(sumOf)
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val input = readLinesFromResourceFile("day03")
            val commons = mutableListOf<Int>()
            for (i in input.indices step 3) {
                val elf1Items = input[i]
                val elf2Items = input[i + 1]
                val elf3Items = input[i + 2]
                val commonItem = elf1Items.filter{
                    elf2Items.contains(it) && elf3Items.contains(it)
                }
                commons.add(commonItem.toPriority())
            }
            println(commons.sum())
        }
    }
}

private fun String.toPriority(): Int {
    return if (this[0].isLowerCase()) {
        this[0].code - 'a'.code + 1
    } else {
        this[0].code - 'A'.code + 27
    }

}
