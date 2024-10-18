package com.sd.lib.compose.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp

@Composable
operator fun PaddingValues.plus(paddingValues: PaddingValues): PaddingValues {
   return PaddingValues(
      top = this.top + paddingValues.top,
      bottom = this.bottom + paddingValues.bottom,
      start = this.start + paddingValues.start,
      end = this.end + paddingValues.end,
   )
}

val PaddingValues.top: Dp
   @Composable @ReadOnlyComposable get() = calculateTopPadding()

val PaddingValues.bottom: Dp
   @Composable @ReadOnlyComposable get() = calculateBottomPadding()

val PaddingValues.start: Dp
   @Composable @ReadOnlyComposable get() {
      val layoutDirection = LocalLayoutDirection.current
      return calculateStartPadding(layoutDirection)
   }

val PaddingValues.end: Dp
   @Composable @ReadOnlyComposable get() {
      val layoutDirection = LocalLayoutDirection.current
      return calculateEndPadding(layoutDirection)
   }