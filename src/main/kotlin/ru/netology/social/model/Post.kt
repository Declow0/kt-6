package ru.netology.social.model

import java.time.Instant
import java.util.UUID

class Post(
    val id : UUID,
    val usedId: UUID,
    var content: String,

    val originalPost: UUID? = null //for repost
) {
    val createTime: Long = Instant.now().toEpochMilli()
    var views: Long = 0L
        private set
    var likes: Long = 0L
        private set
    var reposts: Long = 0L
        private set
    var commentCount: Long = 0L
        private set

    fun incViews() = views++

    fun incLikes() = likes++

    fun incReposts() = reposts++

    fun incCommentCount() = commentCount++
}