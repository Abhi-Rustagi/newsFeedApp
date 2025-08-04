package com.rustagi.newsapp.data.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.rustagi.newsapp.data.paging.NewsPagingSource
import com.rustagi.newsapp.data.remote.api.FettchNewsApiService
import com.rustagi.newsapp.data.repository.NewsFeedRepository
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService: FettchNewsApiService
) : NewsFeedRepository {
    override fun getArticleStream(query: String, sortBy: String) = Pager(
        config = PagingConfig(pageSize = FettchNewsApiService.NETWORK_PAGE_SIZE),
        pagingSourceFactory = { NewsPagingSource(apiService, query, sortBy) }
    ).flow
}