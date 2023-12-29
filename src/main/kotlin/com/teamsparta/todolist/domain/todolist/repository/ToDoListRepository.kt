package com.teamsparta.todolist.domain.todolist.repository

import com.teamsparta.todolist.domain.todolist.model.ToDoList
import org.springframework.data.jpa.repository.JpaRepository

interface ToDoListRepository: JpaRepository<ToDoList, Long> {
    fun findByCardIdAndId(cardId: Long, toDoListId: Long): ToDoList?
}