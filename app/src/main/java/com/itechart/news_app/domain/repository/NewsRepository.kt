package com.itechart.news_app.domain.repository

import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    suspend fun getNews(search: String): Flow<ResultWrapper<List<Article>>>
}