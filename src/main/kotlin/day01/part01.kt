package day01

import utils.parseInput
import utils.toPair
import kotlin.math.abs

fun main() {
    val pairs = parseInput("day01/input.txt") {
        it.split("   ").toPair()
    }

    val first = pairs.map { it.first.toInt() }.sorted()
    val second = pairs.map { it.second.toInt() }.sorted()

    val result = first
        .zip(second)
        .sumOf { abs(it.first - it.second) }

    println(result)
}
