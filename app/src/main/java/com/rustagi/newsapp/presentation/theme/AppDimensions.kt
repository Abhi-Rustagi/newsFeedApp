package com.rustagi.newsapp.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val dimens180 : Dp = 180.dp,
    val dimens16: Dp = 16.dp,
    val dimens8: Dp = 8.dp,
    val dimens280: Dp = 280.dp,
    val dimens48: Dp = 48.dp,
    val dimens12: Dp = 12.dp,
    val dimens4: Dp = 4.dp,
    val dimens24: Dp = 24.dp,
    val dimens20: Dp = 20.dp,
    val dimens32: Dp = 32.dp
)
internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }