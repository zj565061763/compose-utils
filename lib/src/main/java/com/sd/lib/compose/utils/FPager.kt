package com.sd.lib.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerState.FSyncIndex(
    index: Int,
    anim: Boolean = false,
    onChange: (Int) -> Unit,
) {
    val pagerState = this
    LaunchedEffect(pagerState, index) {
        if (pagerState.targetPage != index) {
            if (anim) {
                pagerState.animateScrollToPage(index)
            } else {
                pagerState.scrollToPage(index)
            }
        }
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }
            .collect {
                onChange(it)
            }
    }
}