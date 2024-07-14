package com.dviltres.dynamic_feature_android.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dviltres.dynamic_feature_android.ui.theme.DynamicFeatureTheme

@Composable
fun CircularProgressLevelIndicator(
    text: String,
    modifier: Modifier = Modifier,
    textSize: Dp = TEXT_SIZE_DEFAULT,
    color: Color = Color.Green,
    shadowColor: Color = Color.LightGray,
    backgroundColor: Color = Color.Unspecified,
    size: Dp = INDICATOR_SIZE_DEFAULT,
    progressLevel: Float = 0f,
    indicatorThickness: Dp = INDICATOR_THICKNESS_DEFAULT,
    animationDuration: Int = ANIMATION_DURATION_DEFAULT
) {
    val levelUsageAnimate = animateFloatAsState(
        targetValue = progressLevel,
        animationSpec = tween(
            durationMillis = animationDuration
        ), label = ""
    )

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(size)
        ) {
            drawCircle(
                color = shadowColor,
                radius = this.size.height / COORDINATE_DIVIDER_DEFAULT,
                center = Offset(
                    x = this.size.width / COORDINATE_DIVIDER_DEFAULT,
                    y = this.size.height / COORDINATE_DIVIDER_DEFAULT
                )
            )
            drawCircle(
                color = color,
                radius = (size / COORDINATE_DIVIDER_DEFAULT - indicatorThickness).toPx(),
                center = Offset(
                    x = this.size.width / COORDINATE_DIVIDER_DEFAULT,
                    y = this.size.height / COORDINATE_DIVIDER_DEFAULT
                )
            )
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = (levelUsageAnimate.value) * DEGREES_FULL_CIRCLE / FULL_PERCENTAGE,
                useCenter = false,
                style = Stroke(width = indicatorThickness.toPx(), cap = StrokeCap.Butt),
                size = Size(
                    width = (size - indicatorThickness).toPx(),
                    height = (size - indicatorThickness).toPx()
                ),
                topLeft = Offset(
                    x = (indicatorThickness / COORDINATE_DIVIDER_DEFAULT).toPx(),
                    y = (indicatorThickness / COORDINATE_DIVIDER_DEFAULT).toPx()
                )
            )
        }
        CircularProgressLevelIndicatorText(
            text = text,
            textSize = textSize,
            color = color,
            backgroundColor = backgroundColor
        )
    }
}

private val TEXT_SIZE_DEFAULT = 36.dp
private val INDICATOR_SIZE_DEFAULT = 45.dp
private val INDICATOR_THICKNESS_DEFAULT = 5.dp
private const val ANIMATION_DURATION_DEFAULT = 1000
private const val DEGREES_FULL_CIRCLE = 360
private const val FULL_PERCENTAGE = 100

@Composable
private fun CircularProgressLevelIndicatorText(
    text: String,
    textSize: Dp,
    color: Color,
    backgroundColor: Color
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure(constraints)
                val currentHeight = placeable.height
                val currentWidth = placeable.width
                val newDiameter = maxOf(currentHeight, currentWidth)

                layout(newDiameter, newDiameter) {
                    placeable.placeRelative(
                        (newDiameter - currentWidth) / COORDINATE_DIVIDER_DEFAULT,
                        (newDiameter - currentHeight) / COORDINATE_DIVIDER_DEFAULT
                    )
                }
            }
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
            .size(textSize)
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = color,
        )
    }
}

private const val COORDINATE_DIVIDER_DEFAULT = 2

@Preview
@Composable
fun PreviewCircularProgressLevelIndicator() {
    DynamicFeatureTheme {
        Surface {
            CircularProgressLevelIndicator(
                text = "1",
                color = MaterialTheme.colorScheme.primary,
                backgroundColor = Color.White,
                shadowColor = Color.LightGray,
                progressLevel = 75f,
            )
        }
    }
}
