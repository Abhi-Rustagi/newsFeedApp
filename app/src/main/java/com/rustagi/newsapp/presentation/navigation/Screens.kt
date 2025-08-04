package com.rustagi.newsapp.presentation.navigation

import com.rustagi.newsapp.domain.model.Article
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens(val route: String) {

    @Serializable
    data object NewsList : Screens("newsList")

    @Serializable
    data class NewsFeedDetails(val article: Article) : Screens("newsDetails")
}