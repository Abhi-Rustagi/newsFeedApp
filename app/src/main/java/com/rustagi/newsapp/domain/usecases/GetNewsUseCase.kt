package com.rustagi.newsapp.domain.usecases

import androidx.paging.PagingData
import androidx.paging.map
import com.rustagi.newsapp.data.repository.NewsFeedRepository
import com.rustagi.newsapp.domain.mappers.toDomainModel
import com.rustagi.newsapp.domain.model.Article
import com.rustagi.newsapp.domain.model.SortOption
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsFeedRepository
) {
    operator fun invoke(query: String, sortBy: SortOption): Flow<PagingData<Article>> {
        val dtoPager: Flow<PagingData<com.rustagi.newsapp.data.model.ArticleDto>> =
            repository.getArticleStream(query, sortBy.filter)

        return dtoPager.map { pagingDataDto ->
            pagingDataDto.map { articleDto ->
                articleDto.toDomainModel()
            }
        }
    }
}