package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp

@Composable
@ReadOnlyComposable
fun Dp.fPx(
   density: Density = LocalDensity.current,
): Float = with(density) { toPx() }

@Composable
@ReadOnlyComposable
fun Dp.fRoundToPx(
   density: Density = LocalDensity.current,
): Int = with(density) { roundToPx() }