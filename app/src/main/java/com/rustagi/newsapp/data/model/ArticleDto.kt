package com.rustagi.newsapp.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsResponse(val articles: List<ArticleDto>)

@JsonClass(generateAdapter = true)
data class ArticleDto(
    val title: String,
    val author: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String?,
    val content: String?
)