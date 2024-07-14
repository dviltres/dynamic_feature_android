package com.dviltres.dynamic_feature_android.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dviltres.dynamic_feature_android.ui.theme.DynamicFeatureTheme

@Composable
fun ButtonMaterial3(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        elevation = null,
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(
            1.dp,
            MaterialTheme.colorScheme.primary
        ),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = Color.Gray
        ),
        modifier = modifier.defaultMinSize(
            minHeight = Dp.Unspecified,
            minWidth = Dp.Unspecified
        ),
        contentPadding = PaddingValues(
            horizontal = 26.dp,
            vertical = 18.dp
        )
    ) {
        TextButtonMaterial3(
            text = text,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.labelLarge,
            modifier = modifier
        )
    }
}

@Preview("ButtonMaterial3")
@Composable
fun PreviewPillButtonDarkTheme() {
    DynamicFeatureTheme(darkTheme = true) {
        Surface {
            Column {
                ButtonMaterial3(
                    text = "Primary button",
                ) { }
            }
        }
    }
}
