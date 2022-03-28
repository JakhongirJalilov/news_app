package com.itechart.news_app.data.model

import com.itechart.news_app.domain.model.Source

data class SourceDto(
    val id: String?,
    val name: String
) {
    fun toSource(): Source = Source(id = id, name = name)
}