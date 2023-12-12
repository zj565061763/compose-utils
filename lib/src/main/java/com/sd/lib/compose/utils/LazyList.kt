package com.sd.lib.compose.utils

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable

fun LazyListScope.fItem(
    key: Any,
    contentType: Any = key,
    content: @Composable LazyItemScope.() -> Unit,
) {
    item(
        key = key,
        contentType = contentType,
        content = content,
    )
}

@OptIn(ExperimentalFoundationApi::class)
fun LazyListScope.fStickyHeader(
    key: Any,
    contentType: Any = key,
    content: @Composable LazyItemScope.() -> Unit,
) {
    stickyHeader(
        key = key,
        contentType = contentType,
        content = content,
    )
}