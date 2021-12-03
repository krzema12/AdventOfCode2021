package day03

import readInput


fun main() {
    fun gammaAsString(readings: List<String>) =
        readings[0].indices.joinToString(separator = "") { index ->
            val numberOfOnes = readings
                .map { it[index] }
                .count { it == '1' }
            if (numberOfOnes > readings.size / 2) {
                "1"
            } else {
                "0"
            }
        }

    fun calculateGamma(readings: List<String>): Int {
        val gammaAsString = gammaAsString(readings)
        return gammaAsString.toInt(2)
    }

    fun String.toggleBits(): String =
        this.map {
            if (it == '1') '0' else '1'
        }.joinToString(separator = "")

    fun calculateEpsilon(readings: List<String>): Int {
        val gammaAsString = gammaAsString(readings)
        val epsilonAsString = gammaAsString.toggleBits()
        return epsilonAsString.toInt(2)
    }

    fun part1(input: List<String>): Int {
        val gamma = calculateGamma(input)
        val epsilon = calculateEpsilon(input)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    val answer0 = part1(testInput)
    println(answer0)
    check(part1(testInput) == 198)

    val input = readInput("day03/Day03")
    val answer1 = part1(input)
    val answer2 = part2(input)
    println(answer1)
    println(answer2)
    check(answer1 == 693486)
//    check(answer2 == 1749524700)
}
