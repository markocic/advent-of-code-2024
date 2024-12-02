package day02

import utils.parseInput


fun main() {
    val reports = parseInput("day02/input.txt") { line ->
        line.split(" ").map { it.toInt() }
    }

    val result = reports.sumOf { report ->
        report.isAscendingAndSafe().or(report.isDescendingAndSafe()).toInt()
    }

    println(result)
}

private fun List<Int>.isAscendingAndSafe() = this.zipWithNext { a, b ->
    a - b
}.all { it in -3..-1 }

private fun List<Int>.isDescendingAndSafe() = this.zipWithNext { a, b ->
    a - b
}.all { it in 1..3 }

private fun Boolean.toInt() = if (this) 1 else 0