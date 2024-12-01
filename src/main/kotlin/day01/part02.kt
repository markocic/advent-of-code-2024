package day01

import utils.parseInput
import utils.toPair
import kotlin.math.abs

fun main() {
    val pairs = parseInput("day01/input.txt") {
        it.split("   ").toPair()
    }

    val map = mutableMapOf<Int, Int>()
    pairs.map { it.second.toInt() }.forEach { num ->
        map.computeIfPresent(num) { _, value ->
            value + 1
        }
        map.putIfAbsent(num, 1)
    }

    val result = pairs.map { it.first.toInt() }.sumOf {
        it * (map[it] ?: 0)
    }

    println(result)
}
