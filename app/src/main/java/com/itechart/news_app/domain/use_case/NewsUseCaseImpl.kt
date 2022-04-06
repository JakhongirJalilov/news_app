package com.itechart.news_app.domain.use_case

import android.util.Log
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.repository.NewsRepository
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class NewsUseCaseImpl(
    private val newsRepository: NewsRepository,
    private val dispatcher: CoroutineDispatcher
) : NewsUseCase {
    override suspend fun getNews(search: String): Flow<ResultWrapper<List<Article>>> =
        withContext(dispatcher) {
            newsRepository.getNews(search)
        }
}