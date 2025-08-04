package com.rustagi.newsapp.data.repository

import androidx.paging.PagingData
import com.rustagi.newsapp.data.model.ArticleDto
import kotlinx.coroutines.flow.Flow

interface NewsFeedRepository {
    fun getArticleStream(query: String, sortBy: String): Flow<PagingData<ArticleDto>>
}