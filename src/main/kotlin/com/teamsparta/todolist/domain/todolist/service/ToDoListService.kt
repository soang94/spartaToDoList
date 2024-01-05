package com.teamsparta.todolist.domain.todolist.service

import com.teamsparta.todolist.domain.comments.dto.CommentsResponse
import com.teamsparta.todolist.domain.comments.dto.CreateCommentsRequest
import com.teamsparta.todolist.domain.comments.dto.UpdateCommentsRequest

interface ToDoListService {
    fun getCommentsList(todolistId: Long): List<CommentsResponse>

    fun getComments(todolistId: Long, commentsId: Long): CommentsResponse

    fun createComments(todolistId: Long, request: CreateCommentsRequest): CommentsResponse

    fun updateComments(todolistId: Long, commentsId: Long, request: UpdateCommentsRequest): CommentsResponse

    fun removeComments(todolistId: Long, commentsId: Long)

    fun removeCommentsList(todolistId: Long)
}