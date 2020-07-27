package com.github.vertex13.gameslist.usecases

import org.koin.dsl.module

val useCaseModule = module {
    factory { GetGames(get()) }
}
