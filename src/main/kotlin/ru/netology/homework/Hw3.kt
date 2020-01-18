package ru.netology.homework

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

fun main() {
    val now = Instant.now().toEpochMilli()
    try {
        println(constructText(now, -1))
    } catch (e: RuntimeException) {
        println(e)
    }

    println()

    println(constructText(now, Period.SECONDS.chronoUnit.duration.seconds))

    println()

    println(constructText(now, Period.MINUTES.chronoUnit.duration.seconds))
    println(constructText(now, Period.MINUTES.chronoUnit.duration.seconds * 2))
    println(constructText(now, Period.MINUTES.chronoUnit.duration.seconds * 5))
    println(constructText(now, Period.MINUTES.chronoUnit.duration.seconds * 11))
    println(constructText(now, Period.MINUTES.chronoUnit.duration.seconds * 13))
    println(constructText(now, Period.MINUTES.chronoUnit.duration.seconds * 31))

    println()

    println(constructText(now, Period.HOURS.chronoUnit.duration.seconds))
    println(constructText(now, Period.HOURS.chronoUnit.duration.seconds * 3))
    println(constructText(now, Period.HOURS.chronoUnit.duration.seconds * 7))
    println(constructText(now, Period.HOURS.chronoUnit.duration.seconds * 11))
    println(constructText(now, Period.HOURS.chronoUnit.duration.seconds * 12))
    println(constructText(now, Period.HOURS.chronoUnit.duration.seconds * 21))

    println()

    println(constructText(now, Period.DAYS.chronoUnit.duration.seconds))
    println(constructText(now, Period.DAYS.chronoUnit.duration.seconds * 4))
    println(constructText(now, Period.DAYS.chronoUnit.duration.seconds * 6))

    println()

    println(constructText(now, Period.WEEKS.chronoUnit.duration.seconds))
    println(constructText(now, Period.WEEKS.chronoUnit.duration.seconds * 2))

    println()

    println(constructText(now, Period.MONTHS.chronoUnit.duration.seconds))
    println(constructText(now, Period.MONTHS.chronoUnit.duration.seconds * 2))
    println(constructText(now, Period.MONTHS.chronoUnit.duration.seconds * 8))
    println(constructText(now, Period.MONTHS.chronoUnit.duration.seconds * 11))

    println()

    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 3))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 9))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 11))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 14))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 21))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 101))
    println(constructText(now, Period.YEARS.chronoUnit.duration.seconds * 111))
}

fun constructText(time: Long, minusSeconds: Long): String {
    if (minusSeconds < 0) throw RuntimeException("Incorrect Input Parameter")

    val timeTo = Instant.ofEpochMilli(time).atZone(ZoneId.systemDefault()).toLocalDateTime()
    val timeFrom = timeTo.minusSeconds(minusSeconds)
    println("$timeFrom - $timeTo")

    val between = calcBetween(timeFrom, timeTo)
    val wordForm = calcWordForm(between.second)

    return if (between.first == Period.SECONDS) {
        "менее "
    } else {
        if (between.second == 1L) {
            ""
        } else {
            "${between.second} "
        }
    } + "${between.first.wordForm(wordForm)} назад"
}

fun calcWordForm(value: Long): WordForm {
    return when (value % 10L) {
        1L -> if (value % 100L == 11L) WordForm.THIRD else WordForm.FIRST
        2L, 3L, 4L -> if (value % 100L in 12L..14L) WordForm.THIRD else WordForm.SECOND
        else -> WordForm.THIRD
    }
}

fun calcBetween(
    from: LocalDateTime,
    to: LocalDateTime
): Pair<Period, Long> {
    Period.values()
        .sortedArrayDescending()
        .forEach {
            val between = it.chronoUnit.between(from, to)
            if (between > 0)
                return Pair(it, between)
        }
    return Pair(Period.SECONDS, 0L)
}

enum class Period(val chronoUnit: ChronoUnit) {
    SECONDS(ChronoUnit.SECONDS) {
        override fun wordForm(wordForm: WordForm) = "минуты"
    },
    MINUTES(ChronoUnit.MINUTES) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "минуту"
            WordForm.SECOND -> "минуты"
            WordForm.THIRD -> "минут"
        }
    },
    HOURS(ChronoUnit.HOURS) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "час"
            WordForm.SECOND -> "часа"
            WordForm.THIRD -> "часов"
        }
    },
    DAYS(ChronoUnit.DAYS) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "день"
            WordForm.SECOND -> "дня"
            WordForm.THIRD -> "дней"
        }
    },
    WEEKS(ChronoUnit.WEEKS) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "неделю"
            WordForm.SECOND -> "недели"
            WordForm.THIRD -> "недель"
        }
    },
    MONTHS(ChronoUnit.MONTHS) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "месяц"
            WordForm.SECOND -> "месяца"
            WordForm.THIRD -> "месяцев"
        }
    },
    YEARS(ChronoUnit.YEARS) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "год"
            WordForm.SECOND -> "года"
            WordForm.THIRD -> "лет"
        }
    };

    abstract fun wordForm(wordForm: WordForm): String
}

enum class WordForm {
    FIRST,
    SECOND,
    THIRD
}