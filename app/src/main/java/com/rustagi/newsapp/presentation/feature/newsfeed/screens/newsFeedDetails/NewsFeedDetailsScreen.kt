package com.rustagi.newsapp.presentation.feature.newsfeed.screens.newsFeedDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.rustagi.newsapp.domain.model.Article
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.ARTICLE_CONTENT_TITLE
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.BACK_CONTENT_DESCRIPTION
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.BOOKMARK_CONTENT_DESCRIPTION
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.CONTENT_LINE_HEIGHT_MULTIPLIER
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.CONTENT_NOT_AVAILABLE
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.DESCRIPTION_LINE_HEIGHT_MULTIPLIER
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.GRADIENT_ALPHA
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.GRADIENT_START_Y
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.SHARE_CONTENT_DESCRIPTION
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.SURFACE_ALPHA
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.TITLE_MAX_LINES
import com.rustagi.newsapp.presentation.common.utils.Utility.formatDate
import com.rustagi.newsapp.presentation.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsFeedDetailsScreen(
    article: Article,
    onBackClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onBookmarkClick: () -> Unit = {}
) {
    var isBookmarked by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = article.title,
                        maxLines = TITLE_MAX_LINES,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleMedium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = BACK_CONTENT_DESCRIPTION
                        )
                    }
                },
                actions = {
                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = SHARE_CONTENT_DESCRIPTION
                        )
                    }
                    IconButton(onClick = {
                        isBookmarked = !isBookmarked
                        onBookmarkClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = BOOKMARK_CONTENT_DESCRIPTION,
                            tint = if (isBookmarked) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
        ) {
            article.urlToImage?.let { imageUrl ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(NewsAppTheme.dimensions.dimens280)
                ) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = article.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = GRADIENT_ALPHA)
                                    ),
                                    startY = GRADIENT_START_Y
                                )
                            )
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(NewsAppTheme.dimensions.dimens20)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(NewsAppTheme.dimensions.dimens16),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    article.author?.let { author ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(NewsAppTheme.dimensions.dimens4)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = null,
                                modifier = Modifier.size(NewsAppTheme.dimensions.dimens16),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = author,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    // Published Date
                    article.publishedAt?.let { publishedAt ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(NewsAppTheme.dimensions.dimens4)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Schedule,
                                contentDescription = null,
                                modifier = Modifier.size(NewsAppTheme.dimensions.dimens16),
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = formatDate(publishedAt),
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens16))

                Text(
                    text = article.title,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = MaterialTheme.typography.headlineMedium.lineHeight
                )

                Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens20))


                article.description?.let { description ->
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(NewsAppTheme.dimensions.dimens12)),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = SURFACE_ALPHA)
                    ) {
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontWeight = FontWeight.Medium
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(NewsAppTheme.dimensions.dimens16),
                            lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * DESCRIPTION_LINE_HEIGHT_MULTIPLIER
                        )
                    }

                    Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens20))
                }


                HorizontalDivider(
                    modifier = Modifier.padding(vertical = NewsAppTheme.dimensions.dimens8),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens12))

                Text(
                    text = ARTICLE_CONTENT_TITLE,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens12))

                Text(
                    text = article.content
                        ?: CONTENT_NOT_AVAILABLE,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = MaterialTheme.typography.bodyLarge.lineHeight * CONTENT_LINE_HEIGHT_MULTIPLIER
                )

                Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens24))


                article.url?.let { url ->
                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(NewsAppTheme.dimensions.dimens48),
                        shape = RoundedCornerShape(NewsAppTheme.dimensions.dimens12)
                    ) {
                        Text(
                            text = "Read Full Article",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(NewsAppTheme.dimensions.dimens32))
            }
        }
    }
}
