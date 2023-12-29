package com.teamsparta.todolist.domain.todolist.dto

data class UpdateToDoListRequest(
    val title: String,
    val description: String?,
    val userName: String,
)
