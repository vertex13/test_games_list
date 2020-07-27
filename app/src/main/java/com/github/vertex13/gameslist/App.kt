package com.github.vertex13.gameslist

import android.app.Application
import com.github.vertex13.gameslist.data.dataModule
import com.github.vertex13.gameslist.presentation.presentationModule
import com.github.vertex13.gameslist.usecases.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(dataModule, useCaseModule, presentationModule)
        }
    }
}
