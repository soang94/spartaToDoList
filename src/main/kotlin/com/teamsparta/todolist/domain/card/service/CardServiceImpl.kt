package com.teamsparta.todolist.domain.card.service

import com.teamsparta.todolist.domain.card.dto.CardResponse
import com.teamsparta.todolist.domain.card.dto.CreateCardRequest
import com.teamsparta.todolist.domain.card.dto.UpdateCardRequest
import com.teamsparta.todolist.domain.card.model.Card
import com.teamsparta.todolist.domain.card.model.toResponse
import com.teamsparta.todolist.domain.card.repository.CardRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.todolist.dto.CreateToDoListRequest
import com.teamsparta.todolist.domain.todolist.dto.ToDoListResponse
import com.teamsparta.todolist.domain.todolist.dto.UpdateToDoListRequest
import com.teamsparta.todolist.domain.todolist.model.ToDoList
import com.teamsparta.todolist.domain.todolist.model.toResponse
import com.teamsparta.todolist.domain.todolist.repository.ToDoListRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardServiceImpl(
    private val cardRepository: CardRepository,
    private val toDoListRepository: ToDoListRepository
): CardService {
    override fun getCardList(): List<CardResponse> {
        return cardRepository.findAll().map { it.toResponse() }
    }

    override fun getCardById(cardId: Long): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        return card.toResponse()
    }

    @Transactional
    override fun createCard(request: CreateCardRequest): CardResponse {
        return cardRepository.save(
            Card(
                userName = request.userName,
            )
        ).toResponse()
    }

    @Transactional
    override fun updateCard(cardId: Long, request: UpdateCardRequest): CardResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)

        card.userName = request.userName

        return cardRepository.save(card).toResponse()
    }

    @Transactional
    override fun deleteCard(cardId: Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        cardRepository.delete(card)
    }

    override fun getToDoListList(cardId: Long): List<ToDoListResponse> {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        return card.todoList.map { it.toResponse() }
    }

    override fun getToDoList(cardId: Long, todolistId: Long): ToDoListResponse {
        val toToList = toDoListRepository.findByCardIdAndId(cardId, todolistId) ?: throw ModelNotFoundException("ToDoList", todolistId)

        return toToList.toResponse()
    }

    @Transactional
    override fun createToDoList(cardId: Long, request: CreateToDoListRequest): ToDoListResponse {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)

        val toDoList = ToDoList(
            title = request.title,
            description = request.description,
            creationDate = request.creationDate,
            userName = request.userName,
            card = card
        )

        card.createToDoList(toDoList)
        cardRepository.save(card)

        return toDoList.toResponse()
    }

    @Transactional
    override fun updateToDoList(cardId: Long, todolistId: Long, request: UpdateToDoListRequest): ToDoListResponse {
        val toDoList = toDoListRepository.findByCardIdAndId(cardId, todolistId) ?: throw ModelNotFoundException("ToDoList", todolistId)

        val (title, description, userName) = request
        toDoList.title = title
        toDoList.description = description
        toDoList.userName = userName

        return toDoListRepository.save(toDoList).toResponse()
    }

    @Transactional
    override fun updateCompleteToDoList(todolistId: Long) {
        val completeToDoList = toDoListRepository.findByIdOrNull(todolistId) ?: throw ModelNotFoundException("ToDoList", todolistId)

        completeToDoList.complete()
        toDoListRepository.save(completeToDoList)

    }

    @Transactional
    override fun removeToDoList(cardId: Long, todolistId: Long) {
        val card = cardRepository.findByIdOrNull(cardId) ?: throw ModelNotFoundException("Card", cardId)
        val toDoList = toDoListRepository.findByIdOrNull(todolistId) ?: throw ModelNotFoundException("ToDoList", todolistId)

        card.removeToDoList(toDoList)

        cardRepository.save(card)
    }
}