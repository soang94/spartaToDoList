package com.teamsparta.todolist.domain.card.service

import com.teamsparta.todolist.domain.card.dto.CardResponse
import com.teamsparta.todolist.domain.card.dto.CreateCardRequest
import com.teamsparta.todolist.domain.card.dto.UpdateCardRequest
import com.teamsparta.todolist.domain.todolist.dto.CreateToDoListRequest
import com.teamsparta.todolist.domain.todolist.dto.ToDoListResponse
import com.teamsparta.todolist.domain.todolist.dto.UpdateToDoListRequest

interface CardService {
    fun getCardList(): List<CardResponse>

    fun getCardById(cardId: Long): CardResponse

    fun createCard(request: CreateCardRequest): CardResponse

    fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse

    fun deleteCard(cardId: Long)

    fun getToDoListList(cardId: Long): List<ToDoListResponse>

    fun getToDoList(cardId: Long, todolistId: Long): ToDoListResponse

    fun createToDoList(cardId: Long, request: CreateToDoListRequest): ToDoListResponse

    fun updateToDoList(cardId: Long, todolistId: Long, request:UpdateToDoListRequest): ToDoListResponse

    fun removeToDoList(cardId: Long, todolistId: Long)
}