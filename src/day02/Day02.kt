package day02

import readInput

data class Position(
    val horizontal: Int,
    val depth: Int,
)

fun main() {
    fun part1(input: List<String>): Int {
        return input
            .map { it.toPosition() }
            .fold(Position(horizontal = 0, depth = 0)) { acc, position ->
                acc + position
            }
            .let { finalPosition ->
                finalPosition.horizontal * finalPosition.depth
            }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day02/Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("day02/Day02")
    val answer1 = part1(input)
    val answer2 = part2(input)
    println(answer1)
    println(answer2)
    check(answer1 == 1692075)
//    check(answer2 == 1538)
}

private fun String.toPosition(): Position {
    val parts = split(" ")
    val direction = parts[0]
    val amount = parts[1].toInt()
    return when (direction) {
        "up" -> Position(horizontal = 0, depth = -amount)
        "down" -> Position(horizontal = 0, depth = amount)
        "forward" -> Position(horizontal = amount, depth = 0)
        else -> throw IllegalArgumentException("Direction not expected: '$direction'")
    }
}

private operator fun Position.plus(other: Position) =
    Position(
        horizontal = horizontal + other.horizontal,
        depth = depth + other.depth,
    )
