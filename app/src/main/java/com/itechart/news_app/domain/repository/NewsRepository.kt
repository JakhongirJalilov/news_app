package com.itechart.news_app.domain.repository

import androidx.paging.PagingData
import com.itechart.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    suspend fun getNews(search: String): Flow<PagingData<Article>>
}