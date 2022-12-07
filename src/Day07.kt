fun main() {
    fun part1(input: List<String>): Int {
        var monArbre = directory(arbre = input, parentname = "", "/", -1)
        directory.index = 1
        monArbre.ComputeMyPart(input)
        var somme = 0
        monArbre.getLespetits().forEach { it ->
            somme += it.totalSize()
        }

        return somme

    }

    fun part2(input: List<String>): Int {
        var monArbre = directory(arbre = input, parentname = "", "/", -1)
        directory.index = 1
        monArbre.ComputeMyPart(input)
        val totaloccupe = monArbre.totalSize()
        val totallibre = 70000000 - totaloccupe
        val objectif = 30000000 - totallibre
        var reponse = monArbre.getlespretendants(objectif)
        reponse.sortedWith(compareBy { it.total_size })
        return reponse[reponse.size - 1].total_size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 95437)
    check(part2(testInput) == 24933642)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))

}

class directory(
    val arbre: List<String>, val parentname: String, val name: String, val parentId: Int

) {
    companion object {
        var compteur_id = 0
        var index = 1
    }

    var id = 0
    var directSize: Int = 0
    val mesEnfants = mutableListOf<directory>()
    var lestoutepetits = mutableSetOf<directory>()
    var pretendant = mutableListOf<directory>()
    var total_size = 0

    init {
        this.id = compteur_id++
    }


    fun getLespetits(): MutableSet<directory> {

        var reponse = mutableSetOf<directory>()
        mesEnfants.forEach { it ->
            reponse.addAll(it.getLespetits())
            if (it.totalSize() < 100000) {
                reponse.add(it)

            }
        }
        lestoutepetits.addAll(reponse)
        return lestoutepetits

    }

    fun getlespretendants(barriere: Int): MutableList<directory> {

        mesEnfants.forEach { it ->
            if (it.totalSize() >= barriere) {
                this.pretendant.add(it)
            }
            this.pretendant.addAll(it.getlespretendants(barriere))
        }
        return this.pretendant
    }

    fun ComputeMyPart(arbo: List<String>) {

        while (arbo[index].substring(0, 4) == "$ ls") {

            index++
        }
        while (index < arbo.size && arbo[index].substring(0, 4) != "$ cd") {
            if (arbo[index].substring(0, 3) == "dir") {
                this.mesEnfants.add(directory(arbo, this.name, arbo[index].substring(4), this.id))
            } else {
                this.directSize += arbo[index].split(" ")[0].toInt()
            }
            index++
        }

        while (index < arbo.size && arbo[index] != "$ cd ..") {
            val nom_enfant = arbo[index].substring(5)
            val enfant = mesEnfants.find { it.name == nom_enfant }
            if (enfant != null) {
                index++
                enfant.ComputeMyPart(arbo)
            }
        }

        index++

    }

    fun totalSize(): Int {

        var reponse = this.directSize
        mesEnfants.forEach { it ->
            reponse += it.totalSize()
        }
        this.total_size = reponse
        return reponse
    }

}