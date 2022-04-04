package com.itechart.news_app.data.repository

import android.util.Log
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(): Result<List<Article>> {
        return try {
            val news = newsService.getNews("everything","622673bba19146119f8eec6ffc60d4da")
            Result.success(news.articles.map { it.toArticle() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}