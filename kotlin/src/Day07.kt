import Utils.Companion.readLinesFromResourceFile

class Day07 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val root = Dir(name = "/", parent = null)
            val input = readLinesFromResourceFile("day07")
            var currentDir = root
            for (line in input) {
                if (isCD(line)) {
                    println(line)
                    val dirName = line.split(" ")[2]
                    currentDir = when (dirName) {
                        "/" -> {
                            root
                        }
                        ".." -> {
                            currentDir.parent!!
                        }
                        else -> {
                            if (!currentDir.hasSubDir(dirName)) {
                                val newDir = Dir(name = dirName, parent = currentDir)
                                currentDir.addSubDir(newDir)
                            }
                            currentDir.getSubDir(dirName)
                        }
                    }

                } else if (isLS(line)) {
                    println("$line // ls current dir $currentDir")
                } else {
                    currentDir.subNodes.add(line.toNode(currentDir))
                }
            }

            val total = getTotalSizeOfDirsLessThan(100000, root)
            println("total less than 100000: $total")

            val allEligible = getAllSizesAbove(30000000 - (70000000 - root.getSize()), root)
            print(allEligible)
            val smallestEnough = allEligible.minOrNull()!!
            println("smallest above ${30000000 - (70000000 - root.getSize())}: $smallestEnough")
        }

        private fun getAllSizesAbove(min: Int, currentDir: Dir): List<Int> {
            return if (currentDir.getSize() < min) {
                emptyList()
            } else {
                currentDir.getSubDirs().map { getAllSizesAbove(min, it) }.flatten() + currentDir.getSize()
            }
        }

        private fun getTotalSizeOfDirsLessThan(max: Int, currentDir: Dir): Long {
            val totalSize : Long = currentDir.getSizeIfLessThan(max).toLong()
            return if (currentDir.isLeaf()) {
                totalSize
            } else {
                totalSize + currentDir.getSubDirs().sumOf { getTotalSizeOfDirsLessThan(max, it) }
            }

        }

        fun isCD(input: String): Boolean {
            return input.startsWith("$ cd")
        }

        fun isLS(input: String): Boolean {
            return input.startsWith("$ ls")
        }

        fun String.toNode(parent: Dir): Node {
            return if (this.startsWith("dir")) {
                Dir(name = this.split(" ")[1], parent = parent)
            } else {
                File(size = this.split(" ")[0].toInt(), name = this.split(" ")[1], parent = parent)
            }
        }
    }

    abstract class Node(open val parent: Dir?) {
        abstract fun getSize(): Int
        fun getSizeIfLessThan(max: Int): Int {
            return if (getSize() < max) {
                getSize()
            } else {
                0
            }
        }
    }

    data class File(private val size: Int, val name: String, override val parent: Dir?) : Node(parent) {
        override fun getSize(): Int {
            return size
        }

    }

    data class Dir(val subNodes: MutableList<Node> = mutableListOf(), val name: String, override val parent: Dir?) : Node(parent) {


        override fun getSize(): Int {
            return subNodes.sumOf { it.getSize() }
        }

        fun hasSubDir(dirName: String): Boolean {
            return subNodes.filterIsInstance<Dir>().any { it.name == dirName }
        }

        fun isLeaf(): Boolean {
            return subNodes.none { it is Dir }
        }

        fun getSubDir(dirName: String): Dir {
            return subNodes.filterIsInstance<Dir>().first { it.name == dirName }
        }

        fun getSubDirs(): List<Dir> {
            return subNodes.filterIsInstance<Dir>()
        }

        fun addSubDir(dir: Dir) {
            subNodes.add(dir)
        }

        override fun toString(): String {
            return "Dir(name='$name')"
        }
    }
}