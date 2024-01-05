package com.teamsparta.todolist.domain.todolist.dto

import java.time.LocalDate

data class ToDoListResponse(
    val id: Long,
    val title: String,
    val description: String?,
    val creationDate: LocalDate,
    val userName: String,
    val complete: Boolean,
)
