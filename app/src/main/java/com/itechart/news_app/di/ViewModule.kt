package com.itechart.news_app.di

import com.itechart.news_app.presentation.home.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModules = module {
    viewModel {
        NewsViewModel(get())
    }
}