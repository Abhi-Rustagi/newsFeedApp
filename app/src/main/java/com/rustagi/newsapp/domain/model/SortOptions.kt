package com.rustagi.newsapp.domain.model

enum class SortOption(val filter: String) {
    PUBLISHED_AT("publishedAt"), POPULARITY("popularity"), RELEVANCY("relevancy")
}
