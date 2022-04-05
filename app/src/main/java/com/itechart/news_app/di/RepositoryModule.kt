package com.itechart.news_app.di

import com.itechart.news_app.data.repository.NewsRepositoryImpl
import com.itechart.news_app.domain.repository.NewsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<NewsRepository> {
        NewsRepositoryImpl(get())
    }
}