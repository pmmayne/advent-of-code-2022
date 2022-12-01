class Utils {
    companion object {
        @JvmStatic
        fun readLinesFromResourceFile(filename: String) : List<String>{
            return object {}.javaClass.getResourceAsStream(filename).bufferedReader().readLines()
        }
    }
}