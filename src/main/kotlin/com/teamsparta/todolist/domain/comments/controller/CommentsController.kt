package com.teamsparta.todolist.domain.comments.controller

import com.teamsparta.todolist.domain.comments.dto.CommentsResponse
import com.teamsparta.todolist.domain.comments.dto.CreateCommentsRequest
import com.teamsparta.todolist.domain.comments.dto.UpdateCommentsRequest
import com.teamsparta.todolist.domain.todolist.service.ToDoListService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todolist/{todolistId}/comments")
@RestController
class CommentsController(
    private val toDoListService: ToDoListService
) {
    //댓글 전체 리스트
    @GetMapping
    fun getCommentsList(@PathVariable todolistId: Long): ResponseEntity<List<CommentsResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoListService.getCommentsList(todolistId))
    }

    //댓글 단건 조회
    @GetMapping("/{commentsId}")
    fun getComments(
        @PathVariable todolistId: Long,
        @PathVariable commentsId: Long
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(toDoListService.getComments(todolistId, commentsId))
    }

    //댓글 생성
    @PostMapping
    fun createComments(
        @PathVariable todolistId: Long,
        commentsId: Long,
        @RequestBody createCommentsRequest: CreateCommentsRequest
    ): ResponseEntity<CommentsResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(toDoListService.createComments(todolistId, createCommentsRequest))

    }

    //댓글 수정
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

    //댓글 삭제
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