package com.github.vertex13.gameslist.presentation

import com.github.vertex13.gameslist.presentation.gameslist.GamesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { GamesListViewModel(get()) }
}