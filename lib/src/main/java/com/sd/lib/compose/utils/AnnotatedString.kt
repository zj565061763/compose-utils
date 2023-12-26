package com.sd.lib.compose.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

fun String.fAnnotatedWithTarget(
    target: String,
    ignoreCase: Boolean = false,
    targetBlock: AnnotatedString.Builder.(String) -> Unit,
): AnnotatedString {
    val content = this@fAnnotatedWithTarget
    val list = splitWithTarget(
        target = target,
        ignoreCase = ignoreCase,
    )
    return buildAnnotatedString {
        val builder = this@buildAnnotatedString
        if (list.isEmpty()) {
            append(content)
        } else {
            list.forEach { item ->
                if (item == target) {
                    builder.targetBlock(target)
                } else {
                    append(item)
                }
            }
        }
    }
}

private fun String.splitWithTarget(
    target: String,
    ignoreCase: Boolean = false,
): List<String> {
    if (this == target) return listOf(this)
    val list = split(target, ignoreCase = ignoreCase)
    if (list.isEmpty()) return emptyList()
    if (list.size == 1) {
        val item = list.first()
        if (item == this) return emptyList()
    }
    return mutableListOf<String>().apply {
        list.forEachIndexed { index, item ->
            if (item.isEmpty()) {
                add(target)
            } else {
                add(item)
                if (index != list.lastIndex) {
                    add(target)
                }
            }
        }
    }
}