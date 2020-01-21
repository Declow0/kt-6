package ru.netology.profile.model

import java.util.UUID

class Profile(
    val id: UUID,
    val login: String,
    val name: String,
    val surname: String,
    val status: Status,
    val avatar: Array<Byte>
) {
    val fullname: String
        get() = "$name $surname"

    enum class Status {
        ONLINE,
        OFFLINE
    }
}