package com.rustagi.newsapp.presentation.common.utils

object  Constants {

    object SearchBarConstants {
        const val SEARCH_LABEL = "Search Topics"
        const val SEARCH_ICON_DESCRIPTION = "Search Icon"
        const val CLEAR_ICON_DESCRIPTION = "Clear"
    }


    object NewsFeedDetailsStrings {
        // Content Descriptions
        const val BACK_CONTENT_DESCRIPTION = "Back"
        const val SHARE_CONTENT_DESCRIPTION = "Share"
        const val BOOKMARK_CONTENT_DESCRIPTION = "Bookmark"
        const val SELECTED_CONTENT_DESC = "Selected"

        const val ARTICLE_CONTENT_TITLE = "Article Content"

        const val READ_FULL_ARTICLE_BUTTON = "Read Full Article"
        const val CONTENT_NOT_AVAILABLE = "Full content not available for this article. Please visit the source website to read the complete article."


        const val INPUT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'"
        const val OUTPUT_DATE_PATTERN = "MMM dd, yyyy"

        const val DATE_FALLBACK_LENGTH = 10

        const val TITLE_MAX_LINES = 1
        const val AUTHOR_MAX_LINES = 1
        const val GRADIENT_START_Y = 200f

        const val GRADIENT_ALPHA = 0.7f
        const val SURFACE_ALPHA = 0.5f

        const val DESCRIPTION_LINE_HEIGHT_MULTIPLIER = 1.4f
        const val CONTENT_LINE_HEIGHT_MULTIPLIER = 1.6f
    }

    object UIConstants {
        const val TOP_NEWS_TITLE = "Top News"
        const val DEFAULT_SEARCH_QUERY = "Sports"

        const val NO_ARTICLES_FOUND = "No articles found"
        const val ERROR_PREFIX = "Error: "
        const val RETRY_BUTTON_TEXT = "Retry"

    }
}