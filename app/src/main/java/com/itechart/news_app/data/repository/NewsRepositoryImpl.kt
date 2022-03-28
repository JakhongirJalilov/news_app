package com.itechart.news_app.data.repository

import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository

class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(): Result<List<Article>> {
        return try {
            val news = newsService.getNews("android","569951e530d04db0a8b2733b3c81a0b4")
            Result.success(news.articles.map { it.toArticle() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}