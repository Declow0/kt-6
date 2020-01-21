package ru.netology.profile

import ru.netology.profile.model.Profile
import java.util.UUID

fun main() {
    println(
        Profile(
            UUID.randomUUID(),
            "login",
            "name",
            "surname",
            Profile.Status.ONLINE,
            arrayOf(104, 101, 108, 108, 111)
        )
    )

    kotlin.io.println()

    println(
        Profile(
            UUID.randomUUID(),
            "n.ivanov",
            "Nikolay",
            "Ivanov",
            Profile.Status.OFFLINE,
            arrayOf(98, 121, 101)
        )
    )
}

fun println(profile: Profile) {
    kotlin.io.println(
        """
        |id: ${profile.id}
        |login: ${profile.login},
        |name: ${profile.name}
        |surname: ${profile.surname}
        |status: ${profile.status}
        |avatar: ${profile.avatar}
        |fullname: ${profile.fullname}
    """.trimMargin()
    )
}