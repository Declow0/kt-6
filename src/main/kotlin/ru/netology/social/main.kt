package ru.netology.social

import ru.netology.social.model.Post
import java.util.UUID

fun main() {
    val post = Post(
        UUID.randomUUID(),
        UUID.randomUUID(),
        "Мой первый пост"
    )

    printPost(post)
    println()

    post.incViews()
    post.incViews()
    post.incViews()
    post.incLikes()
    post.incCommentCount()
    post.incReposts()

    val repost = Post(
        UUID.randomUUID(),
        UUID.randomUUID(),
        "Мой первый РЕпост",
        post.id
    )

    printPost(post)
    println()

    printPost(repost)
    println()
}

fun printPost(post: Post) {
    println("""
        id: ${post.id},
        usedId: ${post.usedId}
        content: ${post.content}
        originalPost: ${post.originalPost}
        createTime: ${post.createTime}
        views: ${post.views}
        likes: ${post.likes}
        reposts: ${post.reposts}
        commentCount: ${post.commentCount}
    """.trimIndent())
}