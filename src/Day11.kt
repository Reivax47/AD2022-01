import java.math.BigInteger

import kotlin.math.floor

fun main() {

    fun createMonkeys(input: List<String>): List<monkeys> {
        val reponse = mutableListOf<monkeys>()
        var index = 0
        while (index < input.size) {
            val listsDescription = mutableListOf<String>()
            for (i in 0..5) {
                listsDescription.add(input[index + i])

            }
            index += 7
            reponse.add(monkeys(listsDescription.toList()))
        }
        return reponse.toList()
    }

    fun part1(input: List<String>): Int {
        val lesmonkeys = createMonkeys(input)
        for (round in 1..20) {
            lesmonkeys.forEach { unMonkey ->

                while (unMonkey.startingItems.size > 0) {
                    var items = unMonkey.startingItems[0]
                    unMonkey.startingItems.removeAt(0)
                    val operande = if (unMonkey.selfOperande) items else unMonkey.operande
                    when (unMonkey.operateur) {
                        "+" -> {
                            items += operande.toLong()
                        }

                        "*" -> {
                            items *= operande.toLong()
                        }
                    }
                    items = floor(items / 3.0).toLong()
                    if ((items % unMonkey.modulo).toInt() == 0) {
                        val leMonkey = lesmonkeys.find { it.numero == unMonkey.monkeyIfTrue }
                        leMonkey?.startingItems?.add(items)
                    } else {
                        val leMonkey = lesmonkeys.find { it.numero == unMonkey.monkeyIfFalse }
                        leMonkey?.startingItems?.add(items)
                    }
                    unMonkey.inspectedItems++
                }

            }
        }
        val lesSingesTries = lesmonkeys.sortedByDescending { it.inspectedItems }
        return (lesSingesTries[0].inspectedItems * lesSingesTries[1].inspectedItems)
    }

    fun part2(input: List<String>): BigInteger {
        val lesmonkeys = createMonkeys(input)
        val plusPetitCommunMultiple = lesmonkeys.map { it.modulo }.reduce { acc, i -> acc * i }
        for (round in 1..10000) {
            lesmonkeys.forEach { unMonkey ->

                while (unMonkey.startingItems.size > 0) {
                    var items = unMonkey.startingItems[0]
                    unMonkey.startingItems.removeAt(0)
                    val operande = if (unMonkey.selfOperande) items else unMonkey.operande
                    when (unMonkey.operateur) {
                        "+" -> {
                            items += operande.toLong()
                        }

                        "*" -> {
                            items *= operande.toLong()
                        }
                    }
                    items %= plusPetitCommunMultiple
                    if ((items % unMonkey.modulo).toInt() == 0) {
                        val leMonkey = lesmonkeys.find { it.numero == unMonkey.monkeyIfTrue }
                        leMonkey?.startingItems?.add(items)
                    } else {
                        val leMonkey = lesmonkeys.find { it.numero == unMonkey.monkeyIfFalse }
                        leMonkey?.startingItems?.add(items)
                    }
                    unMonkey.inspectedItems++
                }

            }
        }
        val lesSingesTries = lesmonkeys.sortedByDescending { it.inspectedItems }
        val prem = lesSingesTries[0].inspectedItems.toBigInteger()
        val second = lesSingesTries[1].inspectedItems.toBigInteger()
        return prem.multiply(second)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 10605)
    check(part2(testInput) == "2713310158".toBigInteger())

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))

}

class monkeys(description: List<String>) {

    var startingItems = mutableListOf<Long>()
    var operateur: String = ""
    var modulo: Int = 0
    var selfOperande: Boolean = false
    var operande: Int = 0
    var numero = 0
    var monkeyIfTrue = 0
    var monkeyIfFalse = 0
    var inspectedItems: Int = 0

    init {
        this.numero = description[0].split(" ")[1].split(":")[0].toInt()
        val lesItems = description[1].split(":")[1].split(",")
        lesItems.forEach { unItem ->
            this.startingItems.add(unItem.trim().toLong())
        }
        val operation = description[2].split(" = ")[1]
        this.operateur = operation.substring(4, 5)
        val lafin = operation.substring(6)
        if (lafin == "old") {
            this.selfOperande = true
        } else {
            this.operande = lafin.toInt()
        }
        this.modulo = description[3].split("by ")[1].toInt()
        this.monkeyIfTrue = description[4].split("to monkey ")[1].toInt()
        this.monkeyIfFalse = description[5].split("to monkey ")[1].toInt()
    }
}



