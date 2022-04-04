package com.itechart.news_app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ArticlesDto(
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "totalResults")
    val totalResults: Int,
    @field:Json(name = "articles")
    val articles: List<ArticleDto>
)