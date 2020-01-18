package ru.netology.homework

fun main() {
    try {
        println(constructText(-1))
    } catch (e: RuntimeException) {
        println(e)
    }

    println()

    println(constructText(Period.SECOND.seconds))

    println()

    println(constructText(Period.MINUTE.seconds))
    println(constructText(Period.MINUTE.seconds * 2))
    println(constructText(Period.MINUTE.seconds * 5))
    println(constructText(Period.MINUTE.seconds * 11))
    println(constructText(Period.MINUTE.seconds * 13))
    println(constructText(Period.MINUTE.seconds * 31))

    println()

    println(constructText(Period.HOUR.seconds))
    println(constructText(Period.HOUR.seconds * 3))
    println(constructText(Period.HOUR.seconds * 7))
    println(constructText(Period.HOUR.seconds * 11))
    println(constructText(Period.HOUR.seconds * 12))
    println(constructText(Period.HOUR.seconds * 21))

    println()

    println(constructText(Period.DAY.seconds))
    println(constructText(Period.DAY.seconds * 4))
    println(constructText(Period.DAY.seconds * 6))

    println()

    println(constructText(Period.WEEK.seconds))
    println(constructText(Period.WEEK.seconds * 2))

    println()

    println(constructText(Period.MONTH.seconds))
    println(constructText(Period.MONTH.seconds * 2))
    println(constructText(Period.MONTH.seconds * 8))
    println(constructText(Period.MONTH.seconds * 11))

    println()

    println(constructText(Period.YEAR.seconds))
    println(constructText(Period.YEAR.seconds * 3))
    println(constructText(Period.YEAR.seconds * 9))
    println(constructText(Period.YEAR.seconds * 11))
    println(constructText(Period.YEAR.seconds * 14))
    println(constructText(Period.YEAR.seconds * 21))
    println(constructText(Period.YEAR.seconds * 101))
    println(constructText(Period.YEAR.seconds * 111))
}

fun constructText(seconds: Long): String {
    if (seconds < 0) throw RuntimeException("Incorrect Input Parameter")

    val period = calcPeriod(seconds)
    val value = calcPeriodValue(seconds, period)
    val form = calcWordForm(value)

    return if (period == Period.SECOND) {
        "менее "
    } else {
        if (value == 1L) {
            ""
        } else {
            "$value "
        }
    } + "${period.wordForm(form)} назад"
}

fun calcWordForm(value: Long): WordForm {
    return when (value % 10L) {
        1L -> if (value % 100L == 11L) WordForm.THIRD else WordForm.FIRST
        2L, 3L, 4L -> if (value % 100L in 12L..14L) WordForm.THIRD else WordForm.SECOND
        else -> WordForm.THIRD
    }
}

fun calcPeriodValue(
    seconds: Long,
    period: Period
): Long {
    return seconds / period.seconds
}

fun calcPeriod(seconds: Long): Period {
    return when {
        // in works so slow
        seconds >= Period.YEAR.seconds -> Period.YEAR
        seconds >= Period.MONTH.seconds -> Period.MONTH
        seconds >= Period.WEEK.seconds -> Period.WEEK
        seconds >= Period.DAY.seconds -> Period.DAY
        seconds >= Period.HOUR.seconds -> Period.HOUR
        seconds >= Period.MINUTE.seconds -> Period.MINUTE
        else -> Period.SECOND
    }
}

enum class Period(val seconds: Long = 1L) {
    SECOND {
        override fun wordForm(wordForm: WordForm) = "минуты"
    },
    MINUTE(60L) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "минуту"
            WordForm.SECOND -> "минуты"
            WordForm.THIRD -> "минут"
        }
    },
    HOUR(MINUTE.seconds * 60L) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "час"
            WordForm.SECOND -> "часа"
            WordForm.THIRD -> "часов"
        }
    },
    DAY(HOUR.seconds * 24L) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "день"
            WordForm.SECOND -> "дня"
            WordForm.THIRD -> "дней"
        }
    },
    WEEK(DAY.seconds * 7L) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "неделю"
            WordForm.SECOND -> "недели"
            WordForm.THIRD -> "недель"
        }
    },
    MONTH(DAY.seconds * 30L) {
        override fun wordForm(wordForm: WordForm) = when (wordForm) {
            WordForm.FIRST -> "месяц"
            WordForm.SECOND -> "месяца"
            WordForm.THIRD -> "месяцев"
        }
    },
    YEAR(DAY.seconds * 365L) {
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