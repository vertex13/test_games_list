package com.github.vertex13.gameslist.data

import com.github.vertex13.gameslist.data.api.rawg.*
import com.github.vertex13.gameslist.domain.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

class RawgGamesRepository(
    private val api: RawgApi
) : GamesRepository {

    override suspend fun getGames(category: GameCategory, page: Int, size: Int): Page<Game> {
        val rawgPageNumber = page.inc()
        return when (category) {
            MostAnticipatedCategory -> getMostAnticipated(rawgPageNumber, size)
            LatestCategory -> getLatest(rawgPageNumber, size)
            is MostRatedCategory -> getMostRated(category.year, rawgPageNumber, size)
        }
    }

    private suspend fun getMostAnticipated(page: Int, size: Int): Page<Game> {
        val from = Calendar.getInstance()
        val to = Calendar.getInstance().apply {
            set(Calendar.YEAR, from.get(Calendar.YEAR) + 1)
        }

        return withContext(Dispatchers.IO) {
            api.getGames(
                page = page,
                pageSize = size,
                ordering = Ordering.ADDED,
                dates = Dates(from = Date(from.timeInMillis), to = Date(to.timeInMillis))
            )
        }.toPage(page)
    }

    private suspend fun getLatest(page: Int, size: Int): Page<Game> {
        val to = Calendar.getInstance()
        val from = Calendar.getInstance().apply {
            set(Calendar.MONTH, to.get(Calendar.MONTH) - 1)
        }

        return withContext(Dispatchers.IO) {
            api.getGames(
                page = page,
                pageSize = size,
                dates = Dates(from = Date(from.timeInMillis), to = Date(to.timeInMillis))
            )
        }.toPage(page)
    }

    private suspend fun getMostRated(year: Int, page: Int, size: Int): Page<Game> {
        val from = Calendar.getInstance().apply {
            timeInMillis = 0L
            set(Calendar.YEAR, year)
        }
        val to = Calendar.getInstance().apply {
            timeInMillis = 0L
            set(Calendar.YEAR, year + 1)
            add(Calendar.MILLISECOND, -1)
        }

        return withContext(Dispatchers.IO) {
            api.getGames(
                page = page,
                pageSize = size,
                ordering = Ordering.RATING,
                dates = Dates(from = Date(from.timeInMillis), to = Date(to.timeInMillis))
            )
        }.toPage(page)
    }

}

private fun GetGamesResponse.toPage(pageNumber: Int) = Page(
    pageNumber = pageNumber,
    isLast = next == null,
    content = results.map(GameResult::toGame)
)
