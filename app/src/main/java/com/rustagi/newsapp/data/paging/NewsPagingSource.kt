package com.rustagi.newsapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rustagi.newsapp.data.model.ArticleDto
import com.rustagi.newsapp.data.remote.api.FettchNewsApiService
import okio.IOException
import retrofit2.HttpException

class NewsPagingSource(
    private val apiService: FettchNewsApiService,
    private val query: String,
    private val sortBy: String
) : PagingSource<Int, ArticleDto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleDto> {
        if (query.isBlank()) return LoadResult.Page(emptyList(), null, null)
        val page = params.key ?: 1
        return try {
            val response = apiService.getNews(query, sortBy, page, params.loadSize)
            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.articles.isEmpty()) null else page + 1
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArticleDto>): Int? = state.anchorPosition
}
