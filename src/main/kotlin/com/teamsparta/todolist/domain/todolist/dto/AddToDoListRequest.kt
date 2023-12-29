package com.teamsparta.todolist.domain.todolist.dto

import java.time.LocalDate

data class AddToDoListRequest(
    val title: String,
    val description: String?,
    val creationDate: LocalDate,
    val userName: String,
)
