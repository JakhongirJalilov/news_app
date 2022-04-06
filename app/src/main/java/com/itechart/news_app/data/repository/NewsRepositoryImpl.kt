package com.itechart.news_app.data.repository

import android.util.Log
import com.itechart.news_app.BuildConfig
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(search: String): Flow<ResultWrapper<List<Article>>> = flow {
        emit(ResultWrapper.Loading)
        try {
            val news = newsService.getNews(search, BuildConfig.API_KEY)
            emit(ResultWrapper.Success(news.articles.map { it.toArticle() }))
        } catch (e: Exception) {
            emit(ResultWrapper.Error(e.message))
        }
    }
}