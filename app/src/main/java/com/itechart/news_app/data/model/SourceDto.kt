package com.itechart.news_app.data.model

import com.itechart.news_app.domain.model.Source
import com.squareup.moshi.Json

data class SourceDto(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "name")
    val name: String
) {
    fun toSource(): Source = Source(id = id, name = name)
}