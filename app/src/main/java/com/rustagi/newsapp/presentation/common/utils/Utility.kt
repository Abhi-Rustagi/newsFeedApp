package com.rustagi.newsapp.presentation.common.utils

import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.DATE_FALLBACK_LENGTH
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.INPUT_DATE_PATTERN
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.OUTPUT_DATE_PATTERN
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object Utility {

    fun formatDate(dateString: String): String {
        return try {
            val inputFormat = SimpleDateFormat(INPUT_DATE_PATTERN, Locale.getDefault())
            val outputFormat = SimpleDateFormat(OUTPUT_DATE_PATTERN, Locale.getDefault())
            val date = inputFormat.parse(dateString)
            outputFormat.format(date ?: Date())
        } catch (e: Exception) {
            dateString.take(DATE_FALLBACK_LENGTH) // Fallback to first 10 characters
        }
    }
}