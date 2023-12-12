package com.sd.lib.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

fun LazyListScope.fKeyedItem(
    key: Any,
    content: @Composable LazyItemScope.() -> Unit,
) {
    item(
        key = key,
        contentType = key,
        content = content,
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.fKeyedStickyHeader(
    key: Any,
    content: @Composable LazyItemScope.() -> Unit,
) {
    stickyHeader(
        key = key,
        contentType = key,
        content = content,
    )
}