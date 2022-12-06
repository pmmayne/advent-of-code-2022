import Utils.Companion.readLinesFromResourceFile

class Day06 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val input = readLinesFromResourceFile("day06")[0]
            var marker = getMarker(input)
            print(marker)
        }

        private fun getMarker(input: String): Int {
            for (i in 14 until input.length step 1) {
                val marker = input.substring(i - 14, i)
                if (marker.toSet().size == marker.length) {
                    return i
                }
            }
            return -1
        }
    }
}