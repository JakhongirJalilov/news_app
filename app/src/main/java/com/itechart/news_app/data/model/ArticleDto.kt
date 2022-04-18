package com.itechart.news_app.data.model

import com.itechart.news_app.domain.model.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class ArticleDto(
    @field:Json(name = "author")
    val author: String?,
    @field:Json(name = "content")
    val content: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "publishedAt")
    val publishedAt: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "urlToImage")
    val urlToImage: String?
) {
    fun toArticle(): Article = Article(
        author = author ?: "",
        content = content,
        description = description,
        publishedAt = publishedAt,
//        sourceDto = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}