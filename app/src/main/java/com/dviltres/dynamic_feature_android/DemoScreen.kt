package com.dviltres.dynamic_feature_android

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun DemoScreen(
    logState: String,
    onClickDefaultUI: () -> Unit = {}
) {
    Scaffold {
        DemoComponents(
            logState = logState,
            onClickDefaultUI = onClickDefaultUI,
            paddingValues = it
        )
    }
}
