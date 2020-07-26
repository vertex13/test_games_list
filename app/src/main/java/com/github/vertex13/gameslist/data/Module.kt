package com.github.vertex13.gameslist.data

import com.github.vertex13.gameslist.data.api.rawg.RawgApi
import com.github.vertex13.gameslist.domain.GamesRepository
import org.koin.dsl.module

val dataModule = module {
    single<GamesRepository> { RawgGamesRepository(get()) }
    single { RawgApi.create() }
}
