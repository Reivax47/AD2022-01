import kotlin.math.ceil

fun main() {
    fun part1(input: List<String>): Int {

        val hauteur = input.size
        val largeur = input[0].length
        val mesArbres = mutableListOf<trees>()

        for (indexY in 0 until hauteur) {

            for (indexX in 0 until largeur) {
                val visible = (indexX == 0 || indexY == 0 || indexX == largeur - 1 || indexY == hauteur - 1)
                val value = input[indexY].substring(indexX, indexX + 1).toInt()
                mesArbres.add(trees(indexX, indexY, value, visible))
            }

        }

        for (indexY in 0 until hauteur) {

            var max = mesArbres.find { it.y == indexY && it.x == 0 }?.hauteur
            for (indexX in 1..largeur - 1) {

                val arbre = mesArbres.find { it.y == indexY && it.x == indexX }
                if (arbre?.hauteur!! > max!!) {
                    arbre?.visible = true
                    max = arbre?.hauteur
                }
            }

            max = mesArbres.find { it.y == indexY && it.x == (largeur - 1) }?.hauteur
            for (indexX in (largeur - 2) downTo 0) {
                val arbre = mesArbres.find { it.y == indexY && it.x == indexX }
                if (arbre?.hauteur!! > max!!) {
                    arbre?.visible = true
                    max = arbre?.hauteur
                }
            }
        }
        for (indexX in 0 until largeur) {
            var max = mesArbres.find { it.y == 0 && it.x == indexX }?.hauteur
            for (indexY in 1..hauteur -1) {

                val arbre = mesArbres.find { it.y == indexY && it.x == indexX }
                if (arbre?.hauteur!! > max!!) {
                    arbre?.visible = true
                    max = arbre?.hauteur
                }
            }

            max = mesArbres.find { it.y == hauteur-1 && it.x == indexX }?.hauteur
            for (indexY in (hauteur - 2) downTo 0) {
                val arbre = mesArbres.find { it.y == indexY && it.x == indexX }
                if (arbre?.hauteur!! > max!!) {
                    arbre?.visible = true
                    max = arbre?.hauteur
                }
            }
        }
        val listeVisible = mesArbres.filter { it.visible }
        val reponse = listeVisible.size
        return reponse

    }

    fun docalcul(unArbre : trees, sensX : Int, sensY: Int, largeur: Int, laforet: List<trees>): Int {
        var rep = 1
        var x = unArbre.x
        var y= unArbre.y
        var onsrt = false
        while (x + sensX >= 1 && x + sensX < largeur -1
            && y + sensY >= 1
            && y + sensY < largeur -1 && !onsrt

        ) {
            val candidat = laforet.find { it.x == x + sensX && it.y == y + sensY }
            if (candidat != null && candidat.hauteur >= unArbre.hauteur) {
                onsrt = true
            } else {
                rep ++
                x += sensX
                y += sensY
            }

        }
        return rep
    }
    fun part2(input: List<String>): Int {
        val hauteur = input.size
        val largeur = input[0].length
        val mesArbres = mutableListOf<trees>()

        for (indexY in 0 until hauteur) {

            for (indexX in 0 until largeur) {
                val visible = (indexX == 0 || indexY == 0 || indexX == largeur - 1 || indexY == hauteur - 1)
                val value = input[indexY].substring(indexX, indexX + 1).toInt()
                mesArbres.add(trees(indexX, indexY, value, visible))
            }

        }
        val liste =mesArbres.filter { it.hauteur >= 5 && it.x > 0 && it.y > 0 && it.x < (largeur -1) && it.y < (hauteur -1) }
        var reponse = 0
        liste.forEach { unArbre ->
            var up = docalcul(unArbre,0,-1, largeur, liste)
            var right = docalcul(unArbre,1,0, largeur, liste)
            var down = docalcul(unArbre,0,1, largeur, liste)
            var left = docalcul(unArbre,-1,0, largeur, liste)
            val total = left*right*up*down
            reponse = if(total > reponse) total else reponse
        }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))

}

data class trees(val x: Int, val y: Int, val hauteur: Int, var visible: Boolean)