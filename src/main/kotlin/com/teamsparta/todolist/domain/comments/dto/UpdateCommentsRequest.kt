package com.teamsparta.todolist.domain.comments.dto

data class UpdateCommentsRequest(
    val comments: String,
    val commentUserName: String,
    val commentUserPW: String,
)
