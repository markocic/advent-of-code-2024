package day03

import utils.parseInput
import utils.toPair


fun main() {
    val input = parseInput("day03/input.txt") { line ->
        val regex = Regex("(mul\\(\\d+,\\d+\\))")
        regex.findAll(line).map { it.value }
    }

    val result = input.sumOf { line ->
        line.sumOf { mul ->
            mul.sumMul()
        }
    }

    println(result)
}

fun String.sumMul(): Int {
    val pair = this.replaceFirst("mul(", "")
        .replaceFirst(")", "")
        .split(",")
        .map { it.trim().toInt() }
        .toPair()
    return pair.first * pair.second
}

