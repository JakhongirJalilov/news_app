package com.itechart.news_app.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.itechart.news_app.BuildConfig
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.data.paging.NewsPagingSource
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(search: String): NewsPagingSource {
        return NewsPagingSource(newsService, search)
    }
}


