package com.itechart.news_app.data.api

import com.itechart.news_app.data.model.ArticlesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {
    @GET("/v2/everything")
    suspend fun getNews(
        @Query("q") search: String,
        @Query("apiKey") apiKey: String
    ): ArticlesDto
}