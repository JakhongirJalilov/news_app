package com.itechart.news_app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.use_case.NewsUseCase
import com.itechart.news_app.uitils.ResultWrapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class NewsViewModel(private val newsUseCase: NewsUseCase) : ViewModel() {
    private var _news = MutableSharedFlow<ResultWrapper<PagingData<Article>>>()
    val news: MutableSharedFlow<ResultWrapper<PagingData<Article>>> = _news

    fun getNews(search: String) {
        viewModelScope.launch {
            ResultWrapper.Loading
            newsUseCase.getNews(search).collectLatest { pagingData ->
                _news.emit(ResultWrapper.Success(pagingData))
            }
        }
    }
}