package com.github.vertex13.gameslist.domain

class GetMostAnticipatedGames(private val repository: GamesRepository) {
    suspend operator fun invoke(page: Int, size: Int): Page<Game> {
        return repository.getMostAnticipated(page, size)
    }
}

class GetLatestGames(private val repository: GamesRepository) {
    suspend operator fun invoke(page: Int, size: Int): Page<Game> {
        return repository.getLatest(page, size)
    }
}

class GetMostRatedGamesIn2020(private val repository: GamesRepository) {
    suspend operator fun invoke(page: Int, size: Int): Page<Game> {
        return repository.getMostRated(2020, page, size)
    }
}
