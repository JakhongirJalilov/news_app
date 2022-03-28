package com.itechart.news_app.domain.use_case

import com.itechart.news_app.domain.model.Article

interface NewsUseCase {
    suspend fun getNews(): Result<List<Article>>
}