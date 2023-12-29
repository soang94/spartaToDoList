package com.teamsparta.todolist.domain.card.repository

import com.teamsparta.todolist.domain.card.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository: JpaRepository<Card, Long> {
}