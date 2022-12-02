fun main() {

    val winner = mapOf("A" to "Y", "B" to "Z", "C" to "X")
    val loser = mapOf("A" to "Z", "B" to "X", "C" to "Y")
    val equal = mapOf("A" to "X", "B" to "Y", "C" to "Z")

    fun point_lie_a_mon_jeu(input: List<String>): Int {
        val somme = input.sumOf {
            it.substring(2, 3)
                .first().code - 87
        }

        return somme;
    }

    fun point_lie_a_mes_victoires(input: List<String>): Int {
        var somme = 0
        input.forEach { ligne ->
            val tirage = ligne.split(" ")
            somme += if (tirage[0] == "A" && tirage[1] == "X"
                || tirage[0] == "B" && tirage[1] == "Y"
                || tirage[0] == "C" && tirage[1] == "Z"
            ) 3 else
                if (tirage[0] == "A" && tirage[1] == "Y"
                    || tirage[0] == "B" && tirage[1] == "Z"
                    || tirage[0] == "C" && tirage[1] == "X"
                ) 6 else 0
        }
        return somme
    }

    fun part1(input: List<String>): Int {

        val somme_my_game = point_lie_a_mon_jeu(input)
        val somme_my_win = point_lie_a_mes_victoires(input)

        return somme_my_game + somme_my_win


    }

    fun part2(input: List<String>): Int {

        val transform = input.map { ligne ->
            if (ligne.substring(2, 3) == "X") ligne.substring(0, 1) + " " + loser[ligne.substring(0, 1)]
            else if (ligne.substring(2, 3) == "Y") ligne.substring(0, 1) + " " + equal[ligne.substring(0, 1)]
            else   ligne.substring(0, 1) + " " + winner[ligne.substring(
                0,
                1
            )]
        }
        return part1(transform)

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))

}
