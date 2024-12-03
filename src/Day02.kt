fun main(){
    fun part1(input: List<String>, pUseDampener: Boolean)=
        Regex("\\s+").let{sep->
            (1..3).let{safeRange->
            input
                .map{it.split(sep).map{it.toInt()}}
                .map{it.windowed(2)}
                .flatMap{listOf(it, it.map{it.reversed()}.reversed())}
                .map{var skipNext=false
                    it.all{
                        if(!skipNext && it.last()-it.first() in safeRange)
                            {skipNext=false;true}
                        else // fail
                            if(pUseDampener && !skipNext)
                                {skipNext=true; true}
                            else false}}
                .map{if(it) 1 else 0}
                .sum()
        }} // 591

    val input = readInput("Day02")
    //part1(input).println()
    part1(input, true).println()
}