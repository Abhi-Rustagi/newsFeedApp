package com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedList

import com.rustagi.newsapp.domain.model.SortOption

sealed class NewsListIntent {
    data class SearchNews(val query: String) : NewsListIntent()
    data class SelectSortOption(val sortBy: SortOption) : NewsListIntent()
}