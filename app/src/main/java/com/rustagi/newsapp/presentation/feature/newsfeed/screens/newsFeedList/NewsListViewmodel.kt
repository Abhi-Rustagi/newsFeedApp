package com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.rustagi.newsapp.domain.model.SortOption
import com.rustagi.newsapp.domain.usecases.GetNewsUseCase
import com.rustagi.newsapp.presentation.common.utils.Constants.UIConstants.DEFAULT_SEARCH_QUERY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    private val _userIntent = MutableSharedFlow<NewsListIntent>()

    private val _searchQuery = MutableStateFlow(DEFAULT_SEARCH_QUERY)
    private val _sortOption = MutableStateFlow(SortOption.PUBLISHED_AT)

    val uiState: StateFlow<NewsListUiState> =
        combine(_searchQuery, _sortOption) { query, sort ->
            Pair(query, sort)
        }
            .debounce(500).filter { (query, _) -> query.length >= 2 || query.isEmpty() }
            .flatMapLatest { (query, sort) ->
                flow {
                    val articles = getNewsUseCase(query, sort).cachedIn(viewModelScope)
                    emit(NewsListUiState(query, sort, articles))
                }
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = NewsListUiState()
            )

    init {
        handleIntent()
    }

    fun sendIntent(intent: NewsListIntent) {
        viewModelScope.launch {
            _userIntent.emit(intent)
        }
    }

    private fun handleIntent() {
        viewModelScope.launch {
            _userIntent.collect { intent ->
                when (intent) {
                    is NewsListIntent.SearchNews -> _searchQuery.value = intent.query
                    is NewsListIntent.SelectSortOption -> _sortOption.value = intent.sortBy
                }
            }
        }
    }
}