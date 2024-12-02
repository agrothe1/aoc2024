fun main() {
    fun part1(input: List<String>) =
        Regex("\\s+").let{sep->
            input
                .map{it.split(sep).map{it.toInt()}}
                .map{Pair(it.first(), it.last())}
                .unzip()
                .let{Pair(it.first.sorted(), it.second.sorted())}
                .let{it.first.zip(it.second)}
                .sumOf{Math.abs(it.first-it.second)}
        }

    fun part2(input: List<String>) =
        Regex("\\s+").let{sep->
            input
                .map{it.split(sep).map{it.toInt()}}
                .map{Pair(it.first(), it.last())}
                .fold(Pair(mutableMapOf<Int, Int>(), mutableMapOf<Int, Int>())){
                    lftRightCnts, (lft, rght)->
                        lftRightCnts.let{(lftCnts, rghtCntsSum)->
                            lftCnts.put(lft, lftCnts.getOrElse(lft, {0})+1)
                            rghtCntsSum.put(rght,
                                rghtCntsSum.getOrElse(rght, {0})+rght)
                            Pair(lftCnts, rghtCntsSum)
                        }
                    ;lftRightCnts}
                .let{(cnts, sums)->cnts.map{(num, cnt)->
                    sums.getOrElse(num, {0})*cnt}.sum()}
        }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test Day01.txt from the `src/Day01_test.txt` file:
    //val testInput = readInput("Day01_test")
    //check(part1(testInput) == 1)

    // Read the Day01.txt from the `src/Day01.txt.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
