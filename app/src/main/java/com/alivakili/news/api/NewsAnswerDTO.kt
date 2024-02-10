package com.alivakili.news.api


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsAnswerDTO(
    @Json(name = "articles")
    val articles: List<Article?>? = listOf(),
    @Json(name = "status")
    val status: String? = "",
    @Json(name = "totalResults")
    val totalResults: Int? = 0
)