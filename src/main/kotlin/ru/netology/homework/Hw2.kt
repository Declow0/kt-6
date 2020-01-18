package ru.netology.homework

const val INCORRECT = "Incorrect input params"

fun main() {
    try {
        println(verdict(bodyMassIndex(0.0, 56.0)))
    } catch (e: RuntimeException) {
        println(e)
    }
    println()

    try {
        println(verdict(bodyMassIndex(2.0, 0.0)))
    } catch (e: RuntimeException) {
        println(e)
    }
    println()

    println(verdict(bodyMassIndex(1.5, 20.0)))
    println()

    println(verdict(bodyMassIndex(1.5, 40.0)))
    println()

    println(verdict(bodyMassIndex(1.5, 50.0)))
    println()

    println(verdict(bodyMassIndex(1.5, 70.0)))
    println()

    println(verdict(bodyMassIndex(1.5, 80.0)))
    println()
}

fun bodyMassIndex(
    height: Double,
    mass: Double
): Double {
    println("height: $height, mass: $mass")
    return if (height > 0 && mass > 0)
        mass / height / height
    else throw RuntimeException(INCORRECT)
}

fun verdict(bodyMassIndex: Double): String {
    println("bodyMassIndex: $bodyMassIndex")
    return when (bodyMassIndex) {
        in 0.0..16.0 -> "Выраженный дефицит массы тела"
        in 16.0..18.5 -> "Недостаточная (дефицит) масса тела"
        in 18.5..25.0 -> "Норма"
        in 25.0..30.0 -> "Избыточная масса тела (предожирение)"
        in 30.0..35.0 -> "Ожирение"
        in 35.0..40.0 -> "Ожирение резкое"
        in 40.0..Double.MAX_VALUE -> "Очень резкое ожирение"
        else -> throw RuntimeException(INCORRECT)
    }
}