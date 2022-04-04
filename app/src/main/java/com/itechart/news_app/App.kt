package com.itechart.news_app

import android.app.Application
import com.itechart.news_app.di.apiModule
import com.itechart.news_app.di.repositoryModule
import com.itechart.news_app.di.useCaseModule
import com.itechart.news_app.di.viewModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    apiModule, repositoryModule, useCaseModule, viewModules
                )
            )
        }
    }
}