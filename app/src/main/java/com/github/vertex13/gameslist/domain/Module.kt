package com.github.vertex13.gameslist.domain

import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGames(get()) }
}
