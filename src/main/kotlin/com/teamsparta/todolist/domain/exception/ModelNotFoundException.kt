package com.teamsparta.todolist.domain.exception

data class ModelNotFoundException(
    val cardOrToDoList: String, val id: Long?
): RuntimeException("User $cardOrToDoList not found with given id: $id")
