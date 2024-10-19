package com.sd.lib.compose.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection

@Composable
operator fun PaddingValues.plus(paddingValues: PaddingValues): PaddingValues {
   return PaddingValues(
      top = this.fTop() + paddingValues.fTop(),
      bottom = this.fBottom() + paddingValues.fBottom(),
      start = this.fStart() + paddingValues.fStart(),
      end = this.fEnd() + paddingValues.fEnd(),
   )
}

@Composable
@ReadOnlyComposable
fun PaddingValues.fTop(): Dp = calculateTopPadding()

@Composable
@ReadOnlyComposable
fun PaddingValues.fBottom(): Dp = calculateBottomPadding()

@Composable
@ReadOnlyComposable
fun PaddingValues.fStart(
   layoutDirection: LayoutDirection = LocalLayoutDirection.current,
): Dp = calculateStartPadding(layoutDirection)

@Composable
@ReadOnlyComposable
fun PaddingValues.fEnd(
   layoutDirection: LayoutDirection = LocalLayoutDirection.current,
): Dp = calculateEndPadding(layoutDirection)