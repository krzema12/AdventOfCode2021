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

    fun calculateOxygenGeneratorRating(readings: List<String>, position: Int = 0): Int {
        if (readings.size == 1) {
            return readings[0].toInt(2)
        }

        val numberOfOnes = readings
            .map { it[position] }
            .count { it == '1' }
        val numberOfZeros = readings.size - numberOfOnes
        val requiredValueOfFirstBit = if (numberOfOnes > numberOfZeros) {
            '1'
        } else if (numberOfOnes < numberOfZeros) {
            '0'
        } else {
            '1'
        }
        println("Oxygen: For position $position, leave numbers with $requiredValueOfFirstBit (0: $numberOfZeros, 1: $numberOfOnes)")

        return calculateOxygenGeneratorRating(readings
            .filter { it[position] == requiredValueOfFirstBit },
            position = position + 1
        )
    }

    fun calculateCo2ScrubberRating(readings: List<String>, position: Int = 0): Int {
        if (readings.size == 1) {
            return readings[0].toInt(2)
        }

        val numberOfOnes = readings
            .map { it[position] }
            .count { it == '1' }
        val numberOfZeros = readings.size - numberOfOnes
        val requiredValueOfFirstBit = if (numberOfOnes > numberOfZeros) {
            '0'
        } else if (numberOfOnes < numberOfZeros) {
            '1'
        } else {
            '0'
        }
        println("CO2: For position $position, leave numbers with $requiredValueOfFirstBit (0: $numberOfZeros, 1: $numberOfOnes)")

        return calculateCo2ScrubberRating(readings
            .filter { it[position] == requiredValueOfFirstBit },
            position = position + 1
        )
    }

    fun part2(input: List<String>): Int {
        val oxygenGeneratorRating = calculateOxygenGeneratorRating(input)
        val co2ScrubberRating = calculateCo2ScrubberRating(input)
        return oxygenGeneratorRating * co2ScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("day03/Day03_test")
    val answer00 = part1(testInput)
    val answer01 = part2(testInput)
    println("Test input, part 1: $answer00")
    println("Test input, part 2: $answer01")
    println()
    check(part1(testInput) == 198)

    val input = readInput("day03/Day03")
    val answer1 = part1(input)
    val answer2 = part2(input)
    println("Part 1: $answer1")
    println("Part 2: $answer2")
    check(answer1 == 693486)
    check(answer2 == 3379326)
}
