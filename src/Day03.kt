fun main() {

    fun trouvelecommun(gauche: List<Char>, droite: List<Char>): MutableList<Char> {

        val reponse = mutableListOf<Char>()
        var index_gauche = 0
        var index_droite = 0

        while (index_gauche < gauche.size && index_droite < droite.size) {

            if (gauche[index_gauche] == droite[index_droite]) {

                reponse.add(gauche[index_gauche++])
                index_droite++


            } else {
                val inc_droite = if (gauche[index_gauche] > droite[index_droite]) 1 else 0
                val inc_gauche = if (gauche[index_gauche] < droite[index_droite]) 1 else 0
                index_gauche += inc_gauche
                index_droite += inc_droite
            }

        }

        return reponse
    }

    fun part1(input: List<String>): Int {
        var result = 0

        input.forEach { line ->
            val gauche = line.substring(0, line.length/2 ).toCharArray().distinct().sorted()
            val droite = line.substring(line.length /2).toCharArray().distinct().sorted()
            val winner = trouvelecommun(gauche, droite)[0]
            result += if (winner <='Z') (winner - 38).code else (winner - 96).code

        }

        return result
    }

    fun part2(input: List<String>): Int {
        var index_input = 0
        var result = 0

        while (index_input < input.size) {

            val premier = input[index_input++].toCharArray().distinct().sorted()
            val second = input[index_input++].toCharArray().distinct().sorted()
            val troisieme = input[index_input++].toCharArray().distinct().sorted()

            val premiers_commun = trouvelecommun(premier, second)
            val winner = trouvelecommun(premiers_commun, troisieme)[0]
            result += if (winner <='Z') (winner - 38).code else (winner - 96).code
        }
        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))

}
