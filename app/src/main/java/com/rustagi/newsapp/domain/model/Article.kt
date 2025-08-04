package com.rustagi.newsapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?,
    val content: String?,
    val url: String,
    val author: String?,
    val publishedAt: String?

)