import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.math.pow

fun main() {
    fun euclidienne(x_H: Int, y_H: Int, x_T: Int, y_T: Int): Float {
        return sqrt((x_H - x_T).toFloat().pow(2) + (y_H - y_T).toFloat().pow(2))
    }

    fun part1(input: List<String>): Int {
        val liste = mutableSetOf<position_occupee>()

        var posx_H = 0
        var posy_H = 0
        var posx_T = 0
        var posy_T = 0
        liste.add(position_occupee(posx_T, posy_T))

        input.forEach { ligne ->
            val direction = ligne.substring(0, 1)
            val combien = ligne.substring(2).toInt()

            for (i in 1..combien) {
                when (direction) {
                    "R" -> posx_H++
                    "L" -> posx_H--
                    "U" -> posy_H++
                    "D" -> posy_H--
                }
                val distance = euclidienne(posx_H, posy_H, posx_T, posy_T)

                if (distance > 2) {
                    // diagonale
                    posy_T += if (posy_H > posy_T) 1 else -1
                    posx_T += if (posx_H > posx_T) 1 else -1

                } else if (abs(posx_H - posx_T) > 1) {
                    // Sur le meme Y.
                    posx_T += if (posx_H > posx_T) 1 else -1

                } else if (abs((posy_T - posy_H)) > 1) {
                    // Sur le meme X
                    posy_T += if (posy_H > posy_T) 1 else -1
                }
                liste.add(position_occupee(posx_T, posy_T))
            }
        }
        return liste.size

    }

    fun part2(input: List<String>): Int {

        val liste = mutableSetOf<position_occupee>()

        var posx = arrayOf(0,0,0,0,0,0,0,0,0,0)
        var posy = arrayOf(0,0,0,0,0,0,0,0,0,0)

        liste.add(position_occupee(posx[9], posy[9]))

        input.forEach { ligne ->
            val direction = ligne.substring(0, 1)
            val combien = ligne.substring(2).toInt()

            for (i in 1..combien) {
                when (direction) {
                    "R" -> posx[0]++
                    "L" -> posx[0]--
                    "U" -> posy[0]++
                    "D" -> posy[0]--
                }
                for (j in 1..9) {
                    val distance = euclidienne(posx[j -1], posy[j -1], posx[j ], posy[j ])

                    if (distance > 2) {
                        // diagonale
                        posy[j ] += if (posy[j -1] > posy[j ]) 1 else -1
                        posx[j ] += if (posx[j -1] > posx[j ]) 1 else -1

                    } else if (abs(posx[j -1] - posx[j ]) > 1) {
                        // Sur le meme Y.
                        posx[j ] += if (posx[j -1] > posx[j ]) 1 else -1

                    } else if (abs((posy[j ] - posy[j -1])) > 1) {
                        // Sur le meme X
                        posy[j ] += if (posy[j -1] > posy[j ]) 1 else -1

                    }
                }

                liste.add(position_occupee(posx[9], posy[9]))
            }
        }
        return liste.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 13)
    check(part2(testInput) == 1)
    val testInputBis = readInput("Day09_testbis")
    check(part2(testInputBis) == 36)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))

}


data class position_occupee(val x: Int, val y: Int)