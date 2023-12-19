package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode

@Composable
fun fBrushToBottom(
    colors: List<Color>,
    tileMode: TileMode = TileMode.Clamp,
): Brush {
    return Brush.verticalGradient(
        colors = colors,
        startY = 0.0f,
        endY = Float.POSITIVE_INFINITY,
        tileMode = tileMode,
    )
}

@Composable
fun fBrushToTop(
    colors: List<Color>,
    tileMode: TileMode = TileMode.Clamp,
): Brush {
    return Brush.verticalGradient(
        colors = colors,
        startY = Float.POSITIVE_INFINITY,
        endY = 0.0f,
        tileMode = tileMode,
    )
}