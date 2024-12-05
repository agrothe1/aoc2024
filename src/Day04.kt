fun main(){
    fun part1(pInput: List<String>, pPatt: String): Int {
        fun String.toWidePatt() =
            Regex(toCharArray().map{".*$it"}.joinToString(""))
        return pPatt.toWidePatt().let{patt->
            pInput
                .map{line->patt.findAll(line).count()+
                    patt.findAll(line.reversed()).count()
                }
                .sum()
        }
    }

    val input = listOf(
                "MMMSXXMASM",
                "MSAMXMSMSA",
                "AMXSXMAAMM",
                "MSAMASMSMX",
                "XMASAMXAMM",
                "XXAMMXXAMA",
                "SMSMSASXSS",
                "SAXAMASAAA",
                "MAMMMXMMMM",
                "MXMXAXMASX"
    )
    val xinput=readInput("Day04")
    val patt="XMAS"
    println(part1(input, patt) +
        part1(input.rotateStrings(), patt)
    )
    val diagonals = findAllDiagonals(input)
    diagonals.forEach { println(it) }
    //part2(input).println()
}
// copied from IntelliJ AI Assistant ;-)
fun List<String>.rotateStrings(): List<String> {
    if (isEmpty()) return emptyList()
    val rowCount = size
    val colCount = this[0].length
    val rotatedList = MutableList(colCount) { StringBuilder() }
    for (col in 0 until colCount) {
        for (row in rowCount - 1 downTo 0) {
            rotatedList[col].append(this[row][col])
        }
    }
    return rotatedList.map { it.toString() }
}

fun findAllDiagonals(strings: List<String>): List<String> {
    val diagonals = mutableListOf<String>()
    val rowCount = strings.size
    if (rowCount == 0) return diagonals
    val colCount = strings[0].length

    // Top-left to bottom-right diagonals
    for (startCol in 0 until colCount) {
        val sb = StringBuilder()
        var row = 0
        var col = startCol
        while (row < rowCount && col < colCount) {
            sb.append(strings[row][col])
            row++
            col++
        }
        diagonals.add(sb.toString())
    }

    for (startRow in 1 until rowCount) {
        val sb = StringBuilder()
        var row = startRow
        var col = 0
        while (row < rowCount && col < colCount) {
            sb.append(strings[row][col])
            row++
            col++
        }
        diagonals.add(sb.toString())
    }

    // Top-right to bottom-left diagonals
    for (startCol in 0 until colCount) {
        val sb = StringBuilder()
        var row = 0
        var col = startCol
        while (row < rowCount && col >= 0) {
            sb.append(strings[row][col])
            row++
            col--
        }
        diagonals.add(sb.toString())
    }

    for (startRow in 1 until rowCount) {
        val sb = StringBuilder()
        var row = startRow
        var col = colCount - 1
        while (row < rowCount && col >= 0) {
            sb.append(strings[row][col])
            row++
            col--
        }
        diagonals.add(sb.toString())
    }

    return diagonals
}
