package com.rustagi.newsapp.presentation.common.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.rustagi.newsapp.domain.model.SortOption
import com.rustagi.newsapp.presentation.common.utils.Constants.NewsFeedDetailsStrings.SELECTED_CONTENT_DESC
import com.rustagi.newsapp.presentation.theme.NewsAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SortFilter(selectedOption: SortOption, onOptionSelected: (SortOption) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = NewsAppTheme.dimensions.dimens16,
                vertical = NewsAppTheme.dimensions.dimens8
            ),
        horizontalArrangement = Arrangement.spacedBy(NewsAppTheme.dimensions.dimens8)
    ) {
        SortOption.values().forEach { option ->
            FilterChip(
                selected = option == selectedOption,
                onClick = { onOptionSelected(option) },
                label = { Text(option.filter.replaceFirstChar { it.uppercase() }) },
                leadingIcon = if (option == selectedOption) {
                    { Icon(Icons.Default.Done, contentDescription = SELECTED_CONTENT_DESC) }
                } else null
            )
        }
    }
}