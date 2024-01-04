package com.teamsparta.todolist.domain.comments.repository

import com.teamsparta.todolist.domain.comments.model.Comments
import org.springframework.data.jpa.repository.JpaRepository

interface CommentsRepository: JpaRepository<Comments, Long> {
    fun findByIdAndId(cardId: Long, toDoListId: Long): Comments?
}