package com.itechart.news_app.domain.use_case

import androidx.paging.PagingData
import com.itechart.news_app.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    suspend fun getNews(search: String): Flow<PagingData<Article>>
}