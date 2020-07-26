package com.github.vertex13.gameslist.domain

interface GamesRepository {

    suspend fun getGames(category: GameCategory, page: Int, size: Int): Page<Game>

}

sealed class GameCategory

object MostAnticipatedCategory : GameCategory()

object LatestCategory : GameCategory()

class MostRatedCategory(val year: Int) : GameCategory()
