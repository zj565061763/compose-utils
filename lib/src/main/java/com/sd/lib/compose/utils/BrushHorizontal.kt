package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

@Composable
fun fBrushToEnd(
    colors: List<Color>,
    tileMode: TileMode = TileMode.Clamp,
): Brush {
    return brushHorizontal(
        colors = colors,
        startX = 0.0f,
        endX = Float.POSITIVE_INFINITY,
        tileMode = tileMode,
    )
}

@Composable
fun fBrushToStart(
    colors: List<Color>,
    tileMode: TileMode = TileMode.Clamp,
): Brush {
    return brushHorizontal(
        colors = colors,
        startX = Float.POSITIVE_INFINITY,
        endX = 0.0f,
        tileMode = tileMode,
    )
}

@Composable
private fun brushHorizontal(
    colors: List<Color>,
    startX: Float,
    endX: Float,
    tileMode: TileMode = TileMode.Clamp,
): Brush {
    return when (LocalLayoutDirection.current) {
        LayoutDirection.Ltr -> Brush.horizontalGradient(
            colors = colors,
            startX = startX,
            endX = endX,
            tileMode = tileMode,
        )

        LayoutDirection.Rtl -> Brush.horizontalGradient(
            colors = colors,
            startX = endX,
            endX = startX,
            tileMode = tileMode,
        )
    }
}