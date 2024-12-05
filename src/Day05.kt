fun main(){
    fun part1(pInput: List<String>)=""

    val input=readInput("Day05_real")
    val (orderingRules, produce)=input.splitInput()
    produce
        // part 1
        .filterNot{prod->
            prod.windowed(2, 1).all{orderingRules.contains(it)}
            //prod[prod.size/2] else null
        }
        // part 2
        .mapNotNull{prod->
            println(prod.toString())
            permutations(prod).find{entry->
                entry.windowed(2, 1).all{
                    orderingRules.contains(it)}
            }
        }
        .map{it[it.size/2]}
        .sum()
        .println()
    //part2(input).println()
}

fun List<String>.splitInput() =
    fold(Triple(mutableListOf<List<Int>>(), mutableListOf<List<Int>>(),
            booleanArrayOf(true)))
        {acc, line->
            if(line.isBlank())acc.third[0]=false // part delimiter
            else if(acc.third[0]) // first part
                acc.first+=line.split("|").map{it.toInt()}
            else // second part
                acc.second+=line.split(",").map{it.toInt()}
            acc
        }.let{Pair(it.first.toList(), it.second.toList())} // make immutable

fun <T> permutations(list: List<T>): Sequence<List<T>> =
    sequence{
        if(list.isEmpty()){
            yield(emptyList())
            return@sequence
        }
        list.indices.forEach{idx->
            val current=list[idx]
            val remaining=list.take(idx)+list.drop(idx+1)
            for(permutation in permutations(remaining)){
                yield(listOf(current)+permutation)
            }
        }
    }