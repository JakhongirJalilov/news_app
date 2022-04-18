package com.itechart.news_app.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.itechart.news_app.data.api.NewsService
import com.itechart.news_app.data.paging.NewsPagingSource
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow


class NewsRepositoryImpl(private val newsService: NewsService) : NewsRepository {
    override suspend fun getNews(search: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = { NewsPagingSource(newsService, search) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 30
    }
}


