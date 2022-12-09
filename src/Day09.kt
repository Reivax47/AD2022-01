import kotlin.math.abs
import kotlin.math.sqrt
import kotlin.math.pow

fun main() {
    fun euclidienne(x_H: Int, y_H: Int, x_T: Int, y_T: Int): Float {
        return sqrt((x_H - x_T).toFloat().pow(2) + (y_H - y_T).toFloat().pow(2))
    }

    fun solution(input: List<String>, nombreDeNoeuds: Int): Int {

        val liste = mutableSetOf<position_occupee>()

        var posx = Array(nombreDeNoeuds) { 0 }
        var posy = Array(nombreDeNoeuds) { 0 }

        liste.add(position_occupee(posx[nombreDeNoeuds - 1], posy[nombreDeNoeuds - 1]))

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
                for (j in 1 until nombreDeNoeuds) {
                    val distance = euclidienne(posx[j - 1], posy[j - 1], posx[j], posy[j])

                    if (distance > 2) {
                        // diagonale
                        posy[j] += if (posy[j - 1] > posy[j]) 1 else -1
                        posx[j] += if (posx[j - 1] > posx[j]) 1 else -1

                    } else if (abs(posx[j - 1] - posx[j]) > 1) {
                        // Sur le meme Y.
                        posx[j] += if (posx[j - 1] > posx[j]) 1 else -1

                    } else if (abs((posy[j] - posy[j - 1])) > 1) {
                        // Sur le meme X
                        posy[j] += if (posy[j - 1] > posy[j]) 1 else -1

                    }
                }

                liste.add(position_occupee(posx[nombreDeNoeuds - 1], posy[nombreDeNoeuds - 1]))
            }
        }
        return liste.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(solution(testInput, 2) == 13)
    check(solution(testInput, 10) == 1)
    val testInputBis = readInput("Day09_testbis")
    check(solution(testInputBis, 10) == 36)

    val input = readInput("Day09")
    println(solution(input, 2))
    println(solution(input, 10))

}


data class position_occupee(val x: Int, val y: Int)