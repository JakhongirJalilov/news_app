package com.itechart.news_app.di

import com.itechart.news_app.domain.use_case.NewsUseCase
import com.itechart.news_app.domain.use_case.NewsUseCaseImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val useCaseModule = module {
    single<NewsUseCase> {
        NewsUseCaseImpl(get(), Dispatchers.IO)
    }
}