package day03

import utils.parseInput


fun main() {
    val input = parseInput("day03/input.txt") { line ->
        val regex = Regex("(mul\\(\\d+,\\d+\\))|(do\\(\\))|(don't\\(\\))")
        regex.findAll(line.replace("\n", "")).map { it.value }
    }.map { it.toList() }.flatten()

    var enabled = true
    val result = input.sumOf { match ->
        when {
            match == "do()" -> {
                enabled = true
                0
            }
            match == "don't()" -> {
                enabled = false
                0
            }
            enabled -> {
                match.sumMul()
            }
            else -> 0
        }
    }

    println(result)
}

/*
 * requires single line input...
 */
fun secondApproach() {
    val input = parseInput("day03/input.txt") { line ->
        val stripBad = Regex("(don't\\(\\)).*?(do\\(\\))")
        val trailingDonts = Regex("(don't\\(\\).*$)")

        val stripped = trailingDonts
            .replace(stripBad.replace(line, ""), "")

        val regex = Regex("(mul\\(\\d+,\\d+\\))")
        regex.findAll(stripped).map { it.value }
    }.map { it.toList() }.flatten()

    val result = input.sumOf { mul ->
        mul.sumMul()
    }

    println(result)
}