package day01

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }
            .zipWithNext()
            .count { it.second > it.first }
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }
            .windowed(size = 3) { it.sum() }
            .zipWithNext()
            .count { it.second > it.first }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day01/Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("day01/Day01")
    val answer1 = part1(input)
    val answer2 = part2(input)
    println(answer1)
    println(answer2)
    check(answer1 == 1502)
    check(answer2 == 1538)
}
