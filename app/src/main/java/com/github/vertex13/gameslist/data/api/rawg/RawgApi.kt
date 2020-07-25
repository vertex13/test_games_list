package com.github.vertex13.gameslist.data.api.rawg

import com.github.vertex13.gameslist.data.api.createRetrofitApi
import retrofit2.http.GET
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.util.*

private val actualPlatforms = listOf(4, 187, 1, 18, 186)

interface RawgApi {
    companion object {
        fun create(): RawgApi = createRetrofitApi("https://api.rawg.io/api/")
    }

    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
        @Query("dates") dates: Dates? = null,
        @Query("platforms") platforms: List<Int>? = actualPlatforms,
        @Query("ordering") ordering: Ordering? = null
    ): GetGamesResponse
}

class Dates(private val from: Date, private val to: Date) {
    companion object {
        private val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    }

    override fun toString(): String {
        return "${formatter.format(from)}, ${formatter.format(to)}"
    }
}

enum class Ordering {
    ADDED, RATING;

    override fun toString(): String {
        return "-${super.toString().toLowerCase(Locale.US)}"
    }
}