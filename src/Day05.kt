fun main() {
    fun nombreDeColonnes(input: List<String>): Int {
        var index = 0
        while (input[index].indexOf('[') != -1) {
            index++
        }
        val laListe = input[index].trim().replace(" ", "")
        return laListe.substring(laListe.length - 1).toInt()
    }
    fun part1(input: List<String>): String {

        val nb_colonnes = nombreDeColonnes(input)
        val mesColonnes = MutableList(nb_colonnes) { mutableListOf<String>() }
        var index_input = 0
        while (input[index_input].indexOf('[') != -1) {

            val ligne = input[index_input++]
            var index_ligne = ligne.indexOf('[', 0)
            while (index_ligne != -1) {
                var chargement = ligne.substring(index_ligne + 1, index_ligne + 2)
                mesColonnes[index_ligne / 4].add(chargement)
                index_ligne = ligne.indexOf('[', index_ligne + 1)
            }
        }

        index_input += 2

        while (index_input < input.size) {

            val uneLigne = input[index_input++]
            val nbPackets = uneLigne.substring(5, uneLigne.indexOf(" ", 5)).toInt()
            val colonneDepart =
                uneLigne.substring(uneLigne.indexOf("from") + 5, uneLigne.indexOf(" ", uneLigne.indexOf("from") + 5))
                    .toInt()
            val colonneFin = uneLigne.substring(uneLigne.indexOf("to") + 3).toInt()

            for (i in 1..nbPackets) {

                val packet = mesColonnes[colonneDepart - 1][0]
                mesColonnes[colonneDepart - 1] = mesColonnes[colonneDepart - 1].drop(1).toMutableList()
                mesColonnes[colonneFin - 1].add(0, packet)
            }
        }

        var reponse = ""
        mesColonnes.forEach { uneColonne ->
            reponse += uneColonne[0]

        }
        return reponse

    }

    fun part2(input: List<String>): String {

        val nb_colonnes = nombreDeColonnes(input)
        val mesColonnes = MutableList(nb_colonnes) { mutableListOf<String>() }
        var index_input = 0
        while (input[index_input].indexOf('[') != -1) {

            val ligne = input[index_input++]
            var index_ligne = ligne.indexOf('[', 0)
            while (index_ligne != -1) {
                var chargement = ligne.substring(index_ligne + 1, index_ligne + 2)
                mesColonnes[index_ligne / 4].add(chargement)
                index_ligne = ligne.indexOf('[', index_ligne + 1)
            }
        }

        index_input += 2

        while (index_input < input.size) {

            val uneLigne = input[index_input++]
            val nbPackets = uneLigne.substring(5, uneLigne.indexOf(" ", 5)).toInt()
            val colonneDepart =
                uneLigne.substring(uneLigne.indexOf("from") + 5, uneLigne.indexOf(" ", uneLigne.indexOf("from") + 5))
                    .toInt()
            val colonneFin = uneLigne.substring(uneLigne.indexOf("to") + 3).toInt()
            var position = 0
            for (i in 1..nbPackets) {

                val packet = mesColonnes[colonneDepart - 1][0]
                mesColonnes[colonneDepart - 1] = mesColonnes[colonneDepart - 1].drop(1).toMutableList()
                mesColonnes[colonneFin - 1].add(position++, packet)
            }
        }

        var reponse = ""
        mesColonnes.forEach { uneColonne ->
            reponse += uneColonne[0]
        }
        return reponse
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))

}
