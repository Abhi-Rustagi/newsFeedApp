package com.rustagi.newsapp.di

import com.rustagi.newsapp.data.repository.NewsFeedRepository
import com.rustagi.newsapp.data.repository.impl.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun bindNewsRepository(impl: NewsRepositoryImpl): NewsFeedRepository
}