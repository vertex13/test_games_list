package com.github.vertex13.gameslist.domain

class GetGames(private val repository: GamesRepository) {
    suspend operator fun invoke(category: GameCategory, page: Int, size: Int): Page<Game> {
        return repository.getGames(category, page, size)
    }
}
