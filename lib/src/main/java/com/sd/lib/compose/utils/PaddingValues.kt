package com.sd.lib.compose.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

@Composable
operator fun PaddingValues.plus(other: PaddingValues): PaddingValues {
   val layoutDirection = LocalLayoutDirection.current
   return PaddingValues(
      top = this.fTop() + other.fTop(),
      bottom = this.fBottom() + other.fBottom(),
      start = this.fStart(layoutDirection) + other.fStart(layoutDirection),
      end = this.fEnd(layoutDirection) + other.fEnd(layoutDirection),
   )
}

fun PaddingValues.fTop(): Dp = calculateTopPadding()

fun PaddingValues.fBottom(): Dp = calculateBottomPadding()

@Composable
fun PaddingValues.fStart(
   layoutDirection: LayoutDirection = LocalLayoutDirection.current,
): Dp = calculateStartPadding(layoutDirection)

@Composable
fun PaddingValues.fEnd(
   layoutDirection: LayoutDirection = LocalLayoutDirection.current,
): Dp = calculateEndPadding(layoutDirection)