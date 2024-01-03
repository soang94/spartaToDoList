package com.teamsparta.todolist.domain.card.model

import com.teamsparta.todolist.domain.card.dto.CardResponse
import com.teamsparta.todolist.domain.todolist.model.ToDoList
import jakarta.persistence.*

@Entity
@Table(name = "card")
class Card(
    @Column(name = "username")
    var userName: String,

    @OneToMany(mappedBy = "card", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    var todoList: MutableList<ToDoList> = mutableListOf(),
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun createToDoList(toDoList: ToDoList) {
        todoList.add(toDoList)
    }

    fun removeToDoList(toDoList: ToDoList) {
        todoList.remove(toDoList)
    }
}

fun Card.toResponse(): CardResponse {
    return CardResponse(
        id = id!!,
        userName = userName,
    )
}