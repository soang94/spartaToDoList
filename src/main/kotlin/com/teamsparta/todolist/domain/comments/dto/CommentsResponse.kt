package com.teamsparta.todolist.domain.comments.dto

data class CommentsResponse(
    val id: Long,
    val comments: String,
    val commentsUserName: String,
    val commentsUserPW: String,
)
