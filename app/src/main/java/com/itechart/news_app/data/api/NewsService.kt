package com.itechart.news_app.data.api

import androidx.annotation.IntRange
import com.itechart.news_app.data.model.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") search: String,
        @Query("apiKey") apiKey: String,
        @Query("pageSize") @IntRange(
            from = 1,
            to = MAX_PAGE_SIZE.toLong()
        ) pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("page") @IntRange(from = 1) page: Int = 1
    ): ArticlesDto

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
        const val MAX_PAGE_SIZE = 20
    }
}