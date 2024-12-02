package day02

import utils.parseInput
import utils.toInt

fun main() {
    val reports = parseInput("day02/input.txt") { line ->
        line.split(" ").map { it.toInt() }
    }

    val result = reports.map { report ->
        val asc = report.firstAscendingUnsafeLevel()

        val first = if (asc == -1) 1
        else {
            (report.popAndCheck(asc) { this.isAscendingAndSafe() }
                    || report.popAndCheck(asc + 1) { this.isAscendingAndSafe() })
                .toInt()
        }

        val desc = report.firstDescendingUnsafeLevel()

        val second = if (desc == -1) 1
        else {
            (report.popAndCheck(desc) { this.isDescendingAndSafe() }
                    || report.popAndCheck(desc + 1) { this.isDescendingAndSafe() })
                .toInt()
        }

        Pair(first, second)
    }.map { it.first + it.second }

    println(result.sum())
}

private fun List<Int>.popAndCheck(
    index: Int,
    block: MutableList<Int>.() -> Boolean
): Boolean =
    this.toMutableList().apply { this.removeAt(index) }.block()

private fun List<Int>.firstAscendingUnsafeLevel() =
    this.zipWithNext { a, b -> a - b in -3..-1 }.indexOfFirst { !it }

private fun List<Int>.firstDescendingUnsafeLevel() =
    this.zipWithNext { a, b -> a - b in 1..3 }.indexOfFirst { !it }

private fun MutableList<Int>.isAscendingAndSafe() = this.zipWithNext { a, b ->
    a - b
}.all { it in -3..-1 }

private fun MutableList<Int>.isDescendingAndSafe() = this.zipWithNext { a, b ->
    a - b
}.all { it in 1..3 }
