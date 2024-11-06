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
      top = this.calculateTopPadding() + other.calculateTopPadding(),
      bottom = this.calculateBottomPadding() + other.calculateBottomPadding(),
      start = this.calculateStartPadding(layoutDirection) + other.calculateStartPadding(layoutDirection),
      end = this.calculateEndPadding(layoutDirection) + other.calculateEndPadding(layoutDirection),
   )
}

@Composable
fun PaddingValues.fCalculateStartPadding(
   layoutDirection: LayoutDirection = LocalLayoutDirection.current,
): Dp = calculateStartPadding(layoutDirection)

@Composable
fun PaddingValues.fCalculateEndPadding(
   layoutDirection: LayoutDirection = LocalLayoutDirection.current,
): Dp = calculateEndPadding(layoutDirection)