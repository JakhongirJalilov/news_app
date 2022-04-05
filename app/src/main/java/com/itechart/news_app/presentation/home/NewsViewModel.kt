package com.itechart.news_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itechart.news_app.domain.model.Article
import com.itechart.news_app.domain.use_case.NewsUseCase
import kotlinx.coroutines.launch

class NewsViewModel(val newsUseCase: NewsUseCase) : ViewModel() {
    private val _news = MutableLiveData<Result<List<Article>>>()
    val news: LiveData<Result<List<Article>>> = _news

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getNews(search: String) {
        viewModelScope.launch {
            try {
                _isLoading.postValue(true)
                val news = newsUseCase.getNews(search)
                if (news.isSuccess) {
                    _news.postValue(news)
                    _isLoading.postValue(false)
                }
            } catch (e: Exception) {

            }
        }
    }
}