fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }
            .zipWithNext()
            .map { (a, b) -> if (b > a) 1 else 0 }
            .sum()
    }

    fun part2(input: List<String>): Int {
        return input.map { it.toInt() }
            .windowed(size = 3)
            .map { it.sum() }
            .zipWithNext()
            .map { (a, b) -> if (b > a) 1 else 0 }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
