package com.sd.lib.compose.utils

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

@Composable
fun fResString(vararg items: Any): String {
    val resources = resources()
    return when (items.size) {
        0 -> ""
        1 -> items.first().anyToResString(resources)
        else -> {
            var result by remember { mutableStateOf(items.itemsToResString(resources)) }
            LaunchedEffect(items) {
                result = items.itemsToResString(resources)
            }
            result
        }
    }
}

private fun Array<*>.itemsToResString(resources: Resources): String {
    return buildString {
        for (item in this@itemsToResString) {
            append(item.anyToResString(resources))
        }
    }
}

private fun Any?.anyToResString(resources: Resources): String {
    return when (this) {
        null -> ""
        is String -> this
        is Int -> resources.getString(this)
        else -> this.toString()
    }
}

@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}