package com.dviltres.dynamic_feature_android.ui

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val padding0: Dp = 0.dp,
    val padding4: Dp = 4.dp,
    val padding5: Dp = 5.dp,
    val padding8: Dp = 8.dp,
    val padding10: Dp = 10.dp,
    val padding16: Dp = 16.dp,
    val padding24: Dp = 24.dp,
    val padding32: Dp = 32.dp,
    val padding36: Dp = 36.dp,
    val padding64: Dp = 64.dp
)

val LocalSpacing = compositionLocalOf { Dimensions() }