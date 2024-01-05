package com.teamsparta.todolist.domain.comments.controller

import com.teamsparta.todolist.domain.comments.dto.CommentsResponse
import com.teamsparta.todolist.domain.comments.dto.CreateCommentsRequest
import com.teamsparta.todolist.domain.comments.dto.UpdateCommentsRequest
import com.teamsparta.todolist.domain.todolist.service.ToDoListService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todolist/{todolistId}/comments")
@RestController
class CommentsController(
    private val toDoListService: ToDoListService
) {
    @Operation(summary = "댓글 전체 조회")
    @GetMapping
    fun getCommentsList(@PathVariable todolistId: Long): ResponseEntity<List<CommentsResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoListService.getCommentsList(todolistId))
    }

    @Operation(summary = "댓글 단건 조회")
    @GetMapping("/{commentsId}")
    fun getComments(
        @PathVariable todolistId: Long,
        @PathVariable commentsId: Long
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoListService.getComments(todolistId, commentsId))
    }

    @Operation(summary = "댓글 추가")
    @PostMapping
    fun createComments(
        @PathVariable todolistId: Long,
        @RequestBody createCommentsRequest: CreateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toDoListService.createComments(todolistId, createCommentsRequest))

    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{commentsId}")
    fun updateComments(
        @PathVariable todolistId: Long,
        @PathVariable commentsId: Long,
        @RequestBody updateCommentsRequest: UpdateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoListService.updateComments(todolistId, commentsId, updateCommentsRequest))
    }

    @Operation(summary = "댓글 전체 삭제")
    @DeleteMapping
    fun removeCommentsList(@PathVariable todolistId: Long): ResponseEntity<Unit> {
        toDoListService.removeCommentsList(todolistId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentsId}")
    fun removeComments(
        @PathVariable todolistId: Long,
        @PathVariable commentsId: Long
    ): ResponseEntity<Unit> {
        toDoListService.removeComments(todolistId, commentsId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}