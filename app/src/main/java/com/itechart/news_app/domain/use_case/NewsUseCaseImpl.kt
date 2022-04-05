package com.itechart.news_app.domain.use_case

import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class NewsUseCaseImpl(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher
) : NewsUseCase {
    override suspend fun getNews(search: String): Result<List<Article>> = withContext(dispatcher) {
        newsRepository.getNews(search)
    }
}