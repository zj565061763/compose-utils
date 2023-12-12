package com.sd.lib.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow

/**
 * [PagerState]设置为[index]的位置
 *
 * @param onChange 回调[PagerState]的位置变化
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerState.FSyncIndex(
    index: Int,
    anim: Boolean = false,
    onChange: (Int) -> Unit,
) {
    val pagerState = this
    val onChangeUpdated by rememberUpdatedState(onChange)

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
                onChangeUpdated(it)
            }
    }
}