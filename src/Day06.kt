fun main() {
    fun detection(packet: String) : Boolean {

        val reponse = packet.toCharArray().distinct()
        return reponse.size == packet.length

    }
    fun part(input: List<String>, Taille: Int): Int {

        var index = 0
        while (!detection(input[0].substring(index, index + Taille))) {
            index++
        }
        return index + Taille

    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part(testInput,4) == 7)
    check(part(testInput,14) == 19)

    val input = readInput("Day06")
    println(part(input,4))
    println(part(input,14))

}
