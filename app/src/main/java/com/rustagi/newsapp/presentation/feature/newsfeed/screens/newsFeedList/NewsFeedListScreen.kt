package com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.rustagi.newsapp.domain.model.Article
import com.rustagi.newsapp.presentation.common.utils.Constants.UIConstants.ERROR_PREFIX
import com.rustagi.newsapp.presentation.common.utils.Constants.UIConstants.NO_ARTICLES_FOUND
import com.rustagi.newsapp.presentation.common.utils.Constants.UIConstants.RETRY_BUTTON_TEXT
import com.rustagi.newsapp.presentation.common.utils.Constants.UIConstants.TOP_NEWS_TITLE
import com.rustagi.newsapp.presentation.common.widgets.ArticleItem
import com.rustagi.newsapp.presentation.common.widgets.SearchBar
import com.rustagi.newsapp.presentation.common.widgets.SortFilter
import com.rustagi.newsapp.presentation.theme.NewsAppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedListScreen(
    onArticleClick: (Article) -> Unit,
    viewModel: NewsListViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val articles = uiState.articles.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()

    Scaffold(topBar = { TopAppBar(title = { Text(TOP_NEWS_TITLE) }) }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            // Search and Sort UI components
            SearchBar(
                query = uiState.searchQuery,
                onQueryChanged = { newQuery ->
                    scope.launch { viewModel.sendIntent(NewsListIntent.SearchNews(newQuery)) }
                }
            )
            SortFilter(
                selectedOption = uiState.sortOption,
                onOptionSelected = { newSortOption ->
                    scope.launch {
                        viewModel.sendIntent(
                            NewsListIntent.SelectSortOption(
                                newSortOption
                            )
                        )
                    }
                }
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(NewsAppTheme.dimensions.dimens16),
                verticalArrangement = Arrangement.spacedBy(NewsAppTheme.dimensions.dimens16)
            ) {
                items(
                    items = articles,
                    key = { article -> article.url }
                ) { article ->
                    article?.let {
                        ArticleItem(article = it, onClick = { onArticleClick(it) })
                    }
                }

                if (articles.itemCount == 0 && articles.loadState.refresh is LoadState.NotLoading) {
                    item {
                        Text(
                            NO_ARTICLES_FOUND,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        )
                    }
                }

                articles.loadState.apply {
                    when {
                        // Full screen loading indicator for the initial load
                        refresh is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier.fillParentMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        // Loading indicator at the bottom of the list when appending new items
                        append is LoadState.Loading -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(NewsAppTheme.dimensions.dimens16),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        // Full screen error message for an initial load failure
                        refresh is LoadState.Error -> {
                            val e = articles.loadState.refresh as LoadState.Error
                            item {
                                Column(
                                    modifier = Modifier.fillParentMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = "$ERROR_PREFIX ${e.error.localizedMessage}",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                    Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens8))
                                    Button(onClick = { articles.retry() }) {
                                        Text(RETRY_BUTTON_TEXT)
                                    }
                                }
                            }
                        }

                        // A small retry button at the bottom of the list for an append failure
                        append is LoadState.Error -> {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(NewsAppTheme.dimensions.dimens16),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Button(onClick = { articles.retry() }) {
                                        Text(RETRY_BUTTON_TEXT)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}