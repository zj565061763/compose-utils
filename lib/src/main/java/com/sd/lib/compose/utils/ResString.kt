package com.sd.lib.compose.utils

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun fResString(vararg items: Any): String {
    val context = LocalContext.current
    var result by remember { mutableStateOf(items.toResString(context)) }
    LaunchedEffect(items) {
        result = items.toResString(context)
    }
    return result
}

private fun Array<*>.toResString(context: Context): String {
    return buildString {
        for (item in this@toResString) {
            val itemString = when (item) {
                is Int -> context.getString(item)
                is String -> item
                else -> item.toString()
            }
            append(itemString)
        }
    }
}