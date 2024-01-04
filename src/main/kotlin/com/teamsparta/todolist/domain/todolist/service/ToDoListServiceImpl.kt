package com.teamsparta.todolist.domain.todolist.service

import com.teamsparta.todolist.domain.comments.dto.CommentsResponse
import com.teamsparta.todolist.domain.comments.dto.CreateCommentsRequest
import com.teamsparta.todolist.domain.comments.dto.UpdateCommentsRequest
import com.teamsparta.todolist.domain.comments.model.Comments
import com.teamsparta.todolist.domain.comments.model.toResponse
import com.teamsparta.todolist.domain.comments.repository.CommentsRepository
import com.teamsparta.todolist.domain.exception.ModelNotFoundException
import com.teamsparta.todolist.domain.todolist.repository.ToDoListRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToDoListServiceImpl(
    private val toDoListRepository: ToDoListRepository,
    private val commentsRepository: CommentsRepository
): ToDoListService {

    override fun getCommentsList(todolistId: Long): List<CommentsResponse> {
        val todolist = toDoListRepository.findByIdOrNull(todolistId) ?: throw ModelNotFoundException("todolist", todolistId)

        return todolist.comment.map { it.toResponse() }
    }

    override fun getComments(todolistId: Long, commentsId: Long): CommentsResponse {
        val comment = commentsRepository.findByIdAndId(todolistId, commentsId) ?: throw ModelNotFoundException("comments", todolistId)

        return comment.toResponse()
    }

    @Transactional
    override fun createComments(todolistId: Long, request: CreateCommentsRequest): CommentsResponse {
        val todolist = toDoListRepository.findByIdOrNull(todolistId) ?: throw ModelNotFoundException("todolist", todolistId)

         val comments = Comments(
             comments = request.comments,
             commentsUserName = request.commentUserName,
             commentsUserPW = request.commentUserPW,
             todolist = todolist
         )

        todolist.createComments(comments)
        toDoListRepository.save(todolist)

        return comments.toResponse()
    }

    @Transactional
    override fun updateComments(todolistId: Long, commentsId: Long, request: UpdateCommentsRequest): CommentsResponse {
        val comment = commentsRepository.findByIdAndId(todolistId, commentsId) ?: throw ModelNotFoundException("comments", todolistId)

        val (comments, commentsUserName, commentsUserPW) = request
        comment.comments = comments
        comment.commentsUserName = commentsUserName
        comment.commentsUserPW = commentsUserPW
        return comment.toResponse()
    }

    @Transactional
    override fun removeComments(todolistId: Long, commentsId: Long) {
        val todolist = toDoListRepository.findByIdOrNull(todolistId) ?: throw ModelNotFoundException("ToDoList", todolistId)
        val comment = commentsRepository.findByIdOrNull(commentsId) ?: throw ModelNotFoundException("Comments", commentsId)

        todolist.removeComments(comment)
        toDoListRepository.save(todolist)
    }
}