package com.rustagi.newsapp.data.remote.api

import com.rustagi.newsapp.BuildConfig
import com.rustagi.newsapp.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FettchNewsApiService {
    companion object {
        const val NETWORK_PAGE_SIZE = 10
        const val API_KEY = BuildConfig.NEWS_API_KEY
    }

    @GET("everything")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("sortBy") sortBy: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = NETWORK_PAGE_SIZE,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}