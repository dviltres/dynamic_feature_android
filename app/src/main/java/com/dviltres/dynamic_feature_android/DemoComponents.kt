package com.dviltres.dynamic_feature_android

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dviltres.dynamic_feature_android.components.ButtonMaterial3
import com.dviltres.dynamic_feature_android.ui.LocalSpacing

@Composable
fun DemoComponents(
    logState: String,
    modifier: Modifier = Modifier,
    onClickDefaultUI: () -> Unit = {},
    paddingValues: PaddingValues,
) {
    val spacing = LocalSpacing.current
    Column(
        modifier
            .fillMaxWidth()
            .fillMaxSize()
            .padding(
                start = spacing.padding36,
                end = spacing.padding36,
                bottom = paddingValues.calculateBottomPadding()
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .padding(spacing.padding16)
                .fillMaxWidth()
        ) {
            ButtonMaterial3(
                text = "Start on demand module",
                onClick = onClickDefaultUI,
                enabled = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
        LazyColumn(
            modifier = Modifier
                .padding(spacing.padding16)
                .fillMaxWidth()
        ) {
            item {
                Text(
                    text = logState,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
                )
            }
        }
    }
}
