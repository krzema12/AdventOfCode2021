package day02

import readInput

data class Position(
    val horizontal: Int,
    val depth: Int,
)

data class PositionWithAim(
    val horizontal: Int,
    val depth: Int,
    val aim: Int,
)

data class Command(
    val command: String,
    val amount: Int,
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
        return input
            .map { it.toCommand() }
            .fold(PositionWithAim(horizontal = 0, depth = 0, aim = 0)) { acc, command ->
                when (command.command) {
                    "down" -> acc.copy(aim = acc.aim + command.amount)
                    "up" -> acc.copy(aim = acc.aim - command.amount)
                    "forward" -> acc.copy(
                        horizontal = acc.horizontal + command.amount,
                        depth = acc.depth + acc.aim * command.amount,
                    )
                    else -> throw IllegalArgumentException("Not supported")
                }
            }
            .let { finalPosition ->
                finalPosition.horizontal * finalPosition.depth
            }
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
    check(answer2 == 1749524700)
}

private fun String.toCommand(): Command {
    val parts = split(" ")
    val command = parts[0]
    val amount = parts[1].toInt()
    return Command(command = command, amount = amount)
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
