package ru.netology.homework

const val EXCLUSIVE_DISC = 5
val NEGATIVE_MESSAGE = fun(param: String): String = "\"$param\" can't be negative!"

fun main() {
    try {
        println(calculateFee(100, -1))
    } catch (e: RuntimeException) {
        println(e)
    }

    try {
        println(calculateFee(-100, 0))
    } catch (e: RuntimeException) {
        println(e)
    }

    println(calculateFee(100, 0))

    println(calculateFee(100, 1_001))

    println(calculateFee(100, 10_001))

    println(calculateFee(100, 50_001))

    println(calculateFee(100, 0, true))

    println(calculateFee(100, 1_001, true))

    println(calculateFee(100, 10_001, true))

    println(calculateFee(100, 50_001, true))
}

fun calculateFee(
    amount: Int,
    total: Int,
    exclusive: Boolean = false
): Double {
    println("amount: $amount, total: $total, exclusive: $exclusive")

    var disc = when (total) {
        in 0..1_000 -> 30
        in 1_001..10_000 -> 25
        in 10_001..50_000 -> 20
        in 50_001..Int.MAX_VALUE -> 15
        else -> throw RuntimeException(NEGATIVE_MESSAGE("total"))
    }
    disc = if (exclusive) disc - EXCLUSIVE_DISC else disc

    return if (amount < 0)
        throw RuntimeException(NEGATIVE_MESSAGE("amount"))
    else
        amount.toDouble() / 100.0 * disc
}