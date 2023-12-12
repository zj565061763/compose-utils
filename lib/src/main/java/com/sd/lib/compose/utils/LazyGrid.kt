package com.sd.lib.compose.utils

import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.runtime.Composable

fun LazyGridScope.fKeyedItem(
    key: Any,
    span: (LazyGridItemSpanScope.() -> GridItemSpan)? = { GridItemSpan(maxLineSpan) },
    content: @Composable LazyGridItemScope.() -> Unit,
) {
    item(
        key = key,
        contentType = key,
        span = span,
        content = content,
    )
}