package com.teamsparta.todolist.domain.todolist.controller

import com.teamsparta.todolist.domain.card.service.CardService
import com.teamsparta.todolist.domain.todolist.dto.CreateToDoListRequest
import com.teamsparta.todolist.domain.todolist.dto.ToDoListResponse
import com.teamsparta.todolist.domain.todolist.dto.UpdateToDoListRequest
import io.swagger.v3.oas.annotations.Operation
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

    @Operation(summary = "todolist 전체 조회")
    @GetMapping
    fun getToDoListList(@PathVariable cardId: Long): ResponseEntity<List<ToDoListResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getToDoListList(cardId))
    }

    @Operation(summary = "todolist 단건 조회")
    @GetMapping("/{todolistId}")
    fun getToDoList(
        @PathVariable cardId: Long,
        @PathVariable todolistId: Long
    ): ResponseEntity<ToDoListResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(cardService.getToDoList(cardId, todolistId))
    }

    @Operation(summary = "todolist 생성")
    @PostMapping
    fun createToDoList(
        @PathVariable cardId: Long,
        @RequestBody createToDoListRequest: CreateToDoListRequest
    ): ResponseEntity<ToDoListResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(cardService.createToDoList(cardId, createToDoListRequest))
    }

    @Operation(summary = "todolist 수정")
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

    @Operation(summary = "todolist 삭제")
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