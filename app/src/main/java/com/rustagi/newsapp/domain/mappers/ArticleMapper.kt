package com.rustagi.newsapp.domain.mappers

import com.rustagi.newsapp.data.model.ArticleDto
import com.rustagi.newsapp.domain.model.Article

fun ArticleDto.toDomainModel(): Article {
    return Article(
        title = this.title,
        description = this.description,
        urlToImage = this.urlToImage,
        content = this.content,
        url = this.url,
        author = this.author,
        publishedAt = this.publishedAt
    )
}