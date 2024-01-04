package com.teamsparta.todolist.domain.comments.dto

data class CreateCommentsRequest(
    val comments: String,
    val commentUserName: String,
    val commentUserPW: String,
)
