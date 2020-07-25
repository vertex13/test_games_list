package com.github.vertex13.gameslist.data.api.rawg

import com.github.vertex13.gameslist.domain.Game
import com.squareup.moshi.Json

class GetGamesResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameResult>
)

class GameResult(
    val id: Long,
    val name: String,
    @field:Json(name = "background_image") val backgroundImage: String,
    val rating: Float
) {
    fun toGame(): Game = Game(
        id = id,
        name = name,
        imageUrl = backgroundImage,
        rating = rating
    )
}
