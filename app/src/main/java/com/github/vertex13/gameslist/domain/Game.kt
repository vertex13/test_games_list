package com.github.vertex13.gameslist.domain

data class Game(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val rating: Float
)