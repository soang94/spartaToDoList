package com.teamsparta.todolist.domain.comments.model

import com.teamsparta.todolist.domain.comments.dto.CommentsResponse
import com.teamsparta.todolist.domain.todolist.model.ToDoList
import jakarta.persistence.*

@Entity
@Table(name = "comments")
class Comments(
    @Column(name = "comments")
    var comments: String,

    @Column(name = "comments_user_name")
    var commentsUserName: String,

    @Column(name = "comments_user_pw")
    var commentsUserPW: String,

    @ManyToOne
    @JoinColumn(name = "todolist_id")
    var todolist: ToDoList
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comments.toResponse(): CommentsResponse {
    return CommentsResponse(
        id = id!!,
        comments = comments,
        commentsUserName = commentsUserName,
        commentsUserPW = commentsUserPW,
    )
}