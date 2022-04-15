package com.itechart.news_app.domain.repository

import androidx.paging.PagingSource
import com.itechart.news_app.data.paging.NewsPagingSource
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
//    suspend fun getNews(search: String): PagingSource<Int, ResultWrapper<List<Article>>>
    suspend fun getNews(search: String): NewsPagingSource
}