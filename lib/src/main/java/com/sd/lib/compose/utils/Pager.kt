package com.sd.lib.compose.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay

/**
 * 滚动到指定[page]
 */
@SuppressLint("ComposableNaming")
@Composable
fun PagerState.fScrollToPage(page: Int) {
   val state = this
   LaunchedEffect(state, page) {
      if (state.targetPage != page) {
         state.scrollToPage(page)
      }
   }
}

/**
 * 动画滚动到指定[page]
 */
@SuppressLint("ComposableNaming")
@Composable
fun PagerState.fAnimateScrollToPage(page: Int) {
   val state = this
   LaunchedEffect(state, page) {
      if (state.targetPage != page) {
         state.animateScrollToPage(page)
      }
   }
}

/**
 * 监听[PagerState.currentPage]
 */
@SuppressLint("ComposableNaming")
@Composable
fun PagerState.fCurrentPage(onChange: (Int) -> Unit) {
   val state = this
   val onChangeUpdated by rememberUpdatedState(onChange)
   LaunchedEffect(state) {
      snapshotFlow { state.currentPage }.collect {
         onChangeUpdated(it)
      }
   }
}

/**
 * 监听[PagerState.settledPage]
 */
@SuppressLint("ComposableNaming")
@Composable
fun PagerState.fSettledPage(onChange: (Int) -> Unit) {
   val state = this
   val onChangeUpdated by rememberUpdatedState(onChange)
   LaunchedEffect(state) {
      snapshotFlow { state.settledPage }.collect {
         onChangeUpdated(it)
      }
   }
}

/**
 * 自动播放
 */
@SuppressLint("ComposableNaming")
@Composable
fun PagerState.fAutoPlay(
   interval: Long = 3000,
   getNextPage: PagerState.() -> Int = { (currentPage + 1).takeIf { it < pageCount } ?: 0 },
) {
   val state = this
   if (state.pageCount <= 1) {
      return
   }

   val isDragged by state.interactionSource.collectIsDraggedAsState()
   if (isDragged) {
      return
   }

   val intervalUpdated by rememberUpdatedState(interval)
   val getNextPageUpdated by rememberUpdatedState(getNextPage)

   val lifecycle = LocalLifecycleOwner.current.lifecycle
   LaunchedEffect(state, lifecycle) {
      while (true) {
         delay(intervalUpdated)

         if (!lifecycle.fAtLeastState()) {
            delay(intervalUpdated)
         }

         val nextPage = getNextPageUpdated()
         state.animateScrollToPage(nextPage)
      }
   }
}