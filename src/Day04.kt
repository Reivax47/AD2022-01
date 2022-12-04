fun main() {
    fun part1(input: List<String>): Int {

        fun secontiennent(FirstElveDebut: Int, FirstElveFin: Int, SecondElveDebut: Int, SecondElveFin: Int): Boolean {

            return ((FirstElveDebut >= SecondElveDebut && FirstElveFin <= SecondElveFin) || (FirstElveDebut <= SecondElveDebut && FirstElveFin >= SecondElveFin))
        }

        var somme = 0
        input.forEach { pair ->
            val elves = pair.split(",")
            val firstelve = elves[0].split("-")
            val secondelve = elves[1].split("-")

            somme += if (secontiennent(
                    firstelve[0].toInt(),
                    firstelve[1].toInt(),
                    secondelve[0].toInt(),
                    secondelve[1].toInt()
                )
            ) 1 else 0
        }

        return somme

    }

    fun part2(input: List<String>): Int {
        fun overlap(FirstElveDebut: Int, FirstElveFin: Int, SecondElveDebut: Int, SecondElveFin: Int): Boolean {

            if (SecondElveDebut > FirstElveFin || SecondElveFin < FirstElveDebut) {
                return false
            }
            return true

        }

        var somme = 0
        input.forEach { pair ->
            val elves = pair.split(",")
            val firstelve = elves[0].split("-")
            val secondelve = elves[1].split("-")

            somme += if (overlap(
                    firstelve[0].toInt(),
                    firstelve[1].toInt(),
                    secondelve[0].toInt(),
                    secondelve[1].toInt()
                )
            ) 1 else 0
        }

        return somme
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))

}
