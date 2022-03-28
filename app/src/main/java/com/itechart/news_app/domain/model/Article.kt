package com.itechart.news_app.domain.model

import com.itechart.news_app.data.model.SourceDto

data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
//    val sourceDto: SourceDto,
    val title: String,
    val url: String,
    val urlToImage: String
)
