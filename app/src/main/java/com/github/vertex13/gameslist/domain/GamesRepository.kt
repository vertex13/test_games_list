package com.github.vertex13.gameslist.domain

interface GamesRepository {

    suspend fun getMostAnticipated(page: Int, size: Int): Page<Game>

    suspend fun getLatest(page: Int, size: Int): Page<Game>

    suspend fun getMostRated(year: Int, page: Int, size: Int): Page<Game>

}
