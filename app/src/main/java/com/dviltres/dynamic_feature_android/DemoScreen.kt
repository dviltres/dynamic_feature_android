package com.dviltres.dynamic_feature_android

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun DemoScreen(
    onClickDefaultUI: () -> Unit = {}
) {
    Scaffold {
        DemoComponents(
            onClickDefaultUI = onClickDefaultUI,
            paddingValues = it
        )
    }
}
