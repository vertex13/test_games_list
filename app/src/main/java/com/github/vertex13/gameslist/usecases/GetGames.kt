package com.github.vertex13.gameslist.usecases

import com.github.vertex13.gameslist.domain.Game
import com.github.vertex13.gameslist.domain.GameCategory
import com.github.vertex13.gameslist.domain.GamesRepository
import com.github.vertex13.gameslist.domain.Page

class GetGames(private val repository: GamesRepository) {
    suspend operator fun invoke(category: GameCategory, page: Int, size: Int): Page<Game> {
        return repository.getGames(category, page, size)
    }
}
