fun main() {
    fun part1(input: List<String>): Int {
        var max_somme = 0
        var somme = 0
        input.forEach {

            if (it != "") {
                somme += it.toInt()
            } else {
                max_somme = if (somme > max_somme) somme else max_somme
                somme = 0
            }
        }

        return max_somme

    }

    fun part2(input: List<String>): Int {
        val tableau = mutableListOf<Int>()
        var somme = 0
        input.forEach {

            if (it != "") {
                somme += it.toInt()
            } else {
                tableau.add(somme)
                somme = 0
            }
        }
        tableau.add(somme)
        tableau.sortDescending()
        return tableau.subList(0, 3).fold(0) { somme, element -> somme + element }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))

}
