class Day01 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val lines = object {}.javaClass.getResourceAsStream("day01").bufferedReader().readLines()
            val elves = mutableListOf<Int>()
            var curElf = 0
            for (line in lines) {
                if (line == "") {
                    elves.add(curElf)
                    curElf = 0
                } else {
                    curElf += line.toInt()
                }
            }
            val elvesSorted = elves.sorted().reversed()
            println(elvesSorted)
            println(elvesSorted.take(3).sum())

        }
    }
}