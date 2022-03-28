package com.itechart.news_app.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticlesDto(
    @Json(name = "status")
    val status: String,
    @Json(name = "totalResults")
    val totalResults: Int,
    @Json(name = "articles")
    val articles: List<ArticleDto>
)