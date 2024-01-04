package com.teamsparta.todolist.domain.todolist.model

import com.teamsparta.todolist.domain.card.model.Card
import com.teamsparta.todolist.domain.comments.model.Comments
import com.teamsparta.todolist.domain.todolist.dto.ToDoListResponse
import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "todolist")
class ToDoList(
    @Column(name = "title")
    var title: String,

    @Column(name = "description")
    var description: String?,

    @Column(name = "creationdate")
    var creationDate: LocalDate,

    @Column(name = "username")
    var userName: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    var card: Card,

    @OneToMany(mappedBy = "todolist", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var comment: MutableList<Comments> = mutableListOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun createComments(comments: Comments) {
        comment.add(comments)
    }

    fun removeComments(comments: Comments) {
        comment.remove(comments)
    }
}

fun ToDoList.toResponse(): ToDoListResponse {
    return ToDoListResponse(
        id = id!!,
        title = title,
        description = description,
        creationDate = creationDate,
        userName = userName,
    )
}