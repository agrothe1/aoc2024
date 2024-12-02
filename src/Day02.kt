fun main(){
    fun part1(input: List<String>)=
        Regex("\\s+").let{sep->
            (1..3).let{safeRange->
            input
                .map{it.split(sep).map{it.toInt()}}
                .map{it.windowed(2)}
                .flatMap{listOf(it, it.map{it.reversed()}.reversed())}
                .map{
                    it.all{it.last()-it.first() in safeRange}}
                .map{if(it) 1 else 0}
                .sum()
        }}

    val input = readInput("Day02")
    part1(input).println()
}