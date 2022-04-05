package com.itechart.news_app.domain.repository

import com.itechart.news_app.domain.model.Article

interface NewsRepository {
    suspend fun getNews(search: String): Result<List<Article>>
}