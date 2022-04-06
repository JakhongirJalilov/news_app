package com.itechart.news_app.uitils

sealed class ResultWrapper<out T> {
    object Loading : ResultWrapper<Nothing>()
    data class Success<T>(val data: T?) : ResultWrapper<T>()
    data class Error(val message: String?) : ResultWrapper<Nothing>()
}