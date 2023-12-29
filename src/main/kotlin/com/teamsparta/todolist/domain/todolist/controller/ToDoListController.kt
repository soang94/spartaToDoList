package com.teamsparta.todolist.domain.todolist.controller

import com.teamsparta.todolist.domain.card.service.CardService
import com.teamsparta.todolist.domain.todolist.dto.AddToDoListRequest
import com.teamsparta.todolist.domain.todolist.dto.ToDoListResponse
import com.teamsparta.todolist.domain.todolist.dto.UpdateToDoListRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/card/{cardId}/todolist")
@RestController
class ToDoListController(
    private val cardService: CardService
) {

    // todolist 전체 목록 조회
    @GetMapping
    fun getToDoListList(@PathVariable cardId: Long): ResponseEntity<List<ToDoListResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getToDoListList(cardId))
    }

    // todolist 단건 조회
    @GetMapping("/{todolistId}")
    fun getToDoList(
        @PathVariable cardId: Long,
        @PathVariable todolistId: Long
    ): ResponseEntity<ToDoListResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getToDoList(cardId, todolistId))
    }

    // todolist 생성
    @PostMapping
    fun addToDoList(
        @PathVariable cardId: Long,
        @RequestBody addToDoListRequest: AddToDoListRequest
    ): ResponseEntity<ToDoListResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.addToDoList(cardId, addToDoListRequest))
    }

    // todolist 수정
    @PutMapping("/{todolistId}")
    fun updateToDoList(
        @PathVariable cardId: Long,
        @PathVariable todolistId: Long,
        @RequestBody updateToDoListRequest: UpdateToDoListRequest
    ): ResponseEntity<ToDoListResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.updateToDoList(cardId, todolistId, updateToDoListRequest))
    }

    // todolist 삭제
    @DeleteMapping("/{todolistId}")
    fun removeToDoList(
        @PathVariable cardId: Long,
        @PathVariable todolistId: Long
    ): ResponseEntity<Unit> {
        cardService.removeToDoList(cardId, todolistId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}