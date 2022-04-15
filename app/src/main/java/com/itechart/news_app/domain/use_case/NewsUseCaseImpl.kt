package com.itechart.news_app.domain.use_case

import com.itechart.news_app.data.paging.NewsPagingSource
import com.itechart.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NewsUseCaseImpl(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher
) : NewsUseCase {
    override suspend fun getNews(search: String): NewsPagingSource =
        withContext(dispatcher) {
            newsRepository.getNews(search)
        }
}