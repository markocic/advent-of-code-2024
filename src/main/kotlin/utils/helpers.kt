package utils

import java.io.File
import kotlin.Pair

fun <T> parseInput(path: String, block: (String) -> T): List<T> {
    val out: MutableList<T> = mutableListOf()
    File("src/main/kotlin/$path").bufferedReader().forEachLine {
        out.add(block(it))
    }

    return out
}

fun <T> List<T>.toPair() = Pair(this[0], this[1])