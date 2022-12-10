import kotlin.math.floor

fun main() {
    fun rempliCycle(input: List<String>): Array<Int> {
        val nombreAdd = input.filter { it.contains("addx") }.size
        val nombreNoop = input.filter { it.contains("noop") }.size
        val nombreCycles = nombreAdd * 2 + nombreNoop
        var cycles = Array(nombreCycles + 1) { 1 }

        var indexCycle = 0
        input.forEach { ligne ->
            val instruction = ligne.split(" ")
            when (instruction[0]) {
                "noop" -> {
                    indexCycle++
                    cycles[indexCycle] = cycles[indexCycle - 1]
                }

                "addx" -> {
                    indexCycle += 2
                    cycles[indexCycle - 1] = cycles[indexCycle - 2]
                    cycles[indexCycle] = cycles[indexCycle - 1] + instruction[1].toInt()
                }
            }
        }

        return cycles
    }

    fun part1(input: List<String>): Int {

        val cycles = rempliCycle(input)
        return cycles[19] * 20 + cycles[59] * 60 + cycles[99] * 100 + cycles[139] * 140 + cycles[179] * 180 + cycles[219] * 220

    }

    fun part2(input: List<String>) {
        val cycles = rempliCycle(input)
        var crt = Array(6) { "" }

        for (cycle in 1..240) {
            var ligne = floor(cycle / 40.0).toInt()
            var position = cycle % 40
            val X = cycles[cycle - 1]
            if (position == 0) {
                position = 40
                ligne--
            }

            if (position - 1 >= X - 1 && position - 1 <= X + 1) {
                crt[ligne] += "#"
            } else {
                crt[ligne] += "."
            }

        }

        for (j in 0..5) {
            println(crt[j])
        }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 13140)
    part2(testInput)

    val input = readInput("Day10")
    println(part1(input))
    part2(input)

}
