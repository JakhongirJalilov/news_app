package com.itechart.news_app.domain.use_case

import com.itechart.news_app.data.paging.NewsPagingSource
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    suspend fun getNews(search: String): NewsPagingSource
}