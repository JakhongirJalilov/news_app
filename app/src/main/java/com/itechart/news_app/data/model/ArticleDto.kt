package com.itechart.news_app.data.model

import com.itechart.news_app.domain.model.Article
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleDto(
    @Json(name = "author")
    val author: String,
    @Json(name = "content")
    val content: String,
    @Json(name = "description")
    val description: String,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: String
) {
    fun toArticle(): Article = Article(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
//        sourceDto = source,
        title = title,
        url = url,
        urlToImage = urlToImage
    )
}