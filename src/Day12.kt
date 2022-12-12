fun main() {
    fun trouveLesVoisins(monArbre: MutableList<Noeud>, leNoeud: Noeud, largeur: Int, hauteur: Int) {
        val x = leNoeud.x
        val y = leNoeud.y
        val laLettre = leNoeud.letter

        val decalX = arrayOf(0, 0, -1, 1)
        val decalY = arrayOf(-1, 1, 0, 0)
        val decalSens = arrayOf("U", "D", "L", "R")
        for (i in 0..3) {
            val newX = x + decalX[i]
            val newY = y + decalY[i]
            if (newX in 0..largeur && newY in 0..hauteur) {
                val cible = monArbre.find { it.x == newX && it.y == newY }
                if (cible != null && cible.letter < laLettre + 2) {
                    leNoeud.voisins.add(cible)
                }
            }
        }

    }

    fun createArbre(input: List<String>): MutableList<Noeud> {
        val largeur = input[0].length
        val hauteur = input.size
        val monArbre = mutableListOf<Noeud>()
        for (h in 0 until hauteur) {
            for (l in 0 until largeur) {
                val lettre = input[h].substring(l, l + 1)[0].toChar()
                val leNoeud = Noeud(lettre, l, h)
                if (lettre == 'S') {
                    leNoeud.entree = true
                    leNoeud.letter = 'a'
                } else if (lettre == 'E') {
                    leNoeud.sortie = true
                    leNoeud.letter = 'z'
                }
                monArbre.add(leNoeud)
            }
        }
        monArbre.forEach { it ->
            trouveLesVoisins(monArbre, it, largeur - 1, hauteur - 1)
        }
        return monArbre
    }

    fun Dijkstra(monArbre: MutableList<Noeud>, depart: Noeud?): Int {

        val unVisited = mutableSetOf<Noeud>()
        monArbre.forEach { feuille ->
            feuille.visited = false
            feuille.shortestDistance = 10000000
        }
        depart!!.shortestDistance = 0
        unVisited.add(depart)

        do {
            val actuel = unVisited.sortedBy { it.shortestDistance }[0]
            unVisited.remove(actuel)
            actuel.visited = true
            val distanceFromDebut = actuel.shortestDistance + 1
            actuel.voisins.filter { !it.visited }.forEach { unVoisin ->
                if (distanceFromDebut < unVoisin.shortestDistance) {
                    unVoisin.shortestDistance = distanceFromDebut
                }
                unVisited.add(unVoisin)
            }

        } while (unVisited.size > 0)

        return monArbre.find { it.sortie }!!.shortestDistance
    }

    fun part1(input: List<String>): Int {
        val monArbre = createArbre(input)
        val cible = monArbre.find { it.entree }
        return Dijkstra(monArbre, cible)

    }

    fun part2(input: List<String>): Int {
        val monArbre = createArbre(input)
        val lesA = monArbre.filter { it.letter == 'a' }
        val lesResults = mutableListOf<Int>()
        lesA.forEach { it ->
            lesResults.add(Dijkstra(monArbre, it))
        }
        return lesResults.sorted()[0]
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    check(part1(testInput) == 31)
    check(part2(testInput) == 29)

    val input = readInput("Day12")
    println(part1(input))
    println(part2(input))

}

class Noeud(var letter: Char, val x: Int, val y: Int) {

    val voisins = mutableListOf<Noeud>()
    var shortestDistance: Int = 10000000
    var entree = false
    var sortie = false
    var visited = false
}
