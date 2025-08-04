package com.rustagi.newsapp.presentation.common.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.rustagi.newsapp.presentation.common.utils.Constants
import com.rustagi.newsapp.presentation.theme.NewsAppTheme

@Composable
fun SearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var localQuery by remember(query) { mutableStateOf(query) }

    LaunchedEffect(query) {
        localQuery = query
    }

    OutlinedTextField(
        value = localQuery,
        onValueChange = { newQuery ->
            localQuery = newQuery
            onQueryChanged(newQuery)
        },
        label = { Text(Constants.SearchBarConstants.SEARCH_LABEL) },
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = Constants.SearchBarConstants.SEARCH_ICON_DESCRIPTION
            )
        },
        trailingIcon = {
            if (localQuery.isNotEmpty()) {
                IconButton(onClick = {
                    localQuery = ""
                    onQueryChanged("")
                }) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = Constants.SearchBarConstants.CLEAR_ICON_DESCRIPTION
                    )
                }
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = NewsAppTheme.dimensions.dimens16,
                vertical = NewsAppTheme.dimensions.dimens8
            ),
        singleLine = true
    )
}


