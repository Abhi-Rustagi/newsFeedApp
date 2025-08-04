package com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedList

import androidx.paging.PagingData
import com.rustagi.newsapp.domain.model.Article
import com.rustagi.newsapp.domain.model.SortOption
import com.rustagi.newsapp.presentation.common.utils.Constants.UIConstants.DEFAULT_SEARCH_QUERY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class NewsListUiState(
    val searchQuery: String = DEFAULT_SEARCH_QUERY,
    val sortOption: SortOption = SortOption.PUBLISHED_AT,
    val articles: Flow<PagingData<Article>> = emptyFlow()
)