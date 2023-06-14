package com.sd.lib.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshotFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerState.FSyncIndex(
    index: MutableState<Int>,
    anim: Boolean = false,
) {
    val pagerState = this
    LaunchedEffect(pagerState, index.value) {
        if (pagerState.targetPage != index.value) {
            if (anim) {
                pagerState.animateScrollToPage(index.value)
            } else {
                pagerState.scrollToPage(index.value)
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect {
                index.value = it
            }
    }
}