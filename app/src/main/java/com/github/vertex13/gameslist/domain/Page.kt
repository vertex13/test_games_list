package com.github.vertex13.gameslist.domain

class Page<T>(
    val pageNumber: Int,
    val isLast: Boolean,
    val content: List<T>
)
