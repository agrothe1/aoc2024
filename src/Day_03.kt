fun main(){
    fun part1(pInput: List<String>)=
        Regex("""mul\((\d{1,3}),(\d{1,3})\)""").let{mulPatt->
            pInput
                .flatMap{mulPatt.findAll(it)
                    .map{it.groupValues.let{g->g[1].toInt()*g[2].toInt()}}
                }
                .sum()
        }

    fun part2(pInput: List<String>)=
        pInput.joinToString("\n")
            .run{
                val stop=Regex("don't\\(\\)")
                val start=Regex("do\\(\\)")
                split(start)
                .map{it.split(stop).first()}
            }
            .run{part1(this)}

    val input=readInput("Day03")
    //part1(input).println() // 192767529
    part2(input).println() //   104083373
}