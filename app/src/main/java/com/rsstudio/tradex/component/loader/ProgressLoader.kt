package com.rsstudio.tradex.component.loader

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import com.rsstudio.tradex.presentation.theme.errorColor
import com.rsstudio.tradex.presentation.theme.progressBarColor
import com.rsstudio.tradex.presentation.theme.progressBarColorPath


@Composable
fun DefaultCircularIndefiniteBar(
    color: Color = progressBarColor,
    backgroundColor: Color = progressBarColorPath,
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val stroke = with(LocalDensity.current) {
            Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Butt
            )
        }
        Canvas(
            modifier = Modifier
        ) {
            val diameterOffset = stroke.width / 2
            drawCircle(
                radius = size.minDimension / 2.0f - diameterOffset,
                color = backgroundColor,
                style = stroke
            )
        }
        CircularProgressIndicator(
            color = color,
            strokeWidth = strokeWidth
        )
    }
}

@Composable
fun ErrorMessage(
    errorMessage: String,
    error: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage,
            color = if (error) errorColor else Color.Black,
            style = MaterialTheme.typography.labelLarge
        )
    }
}