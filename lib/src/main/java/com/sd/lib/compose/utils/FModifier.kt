package com.sd.lib.compose.utils

import android.graphics.drawable.Drawable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.platform.LocalContext

fun Modifier.fBackgroundResource(resId: Int) = composed {
    val context = LocalContext.current
    val drawable = remember(resId) { context.getDrawable(resId) }
    if (drawable != null) {
        fBackgroundDrawable(drawable)
    } else {
        this
    }
}

fun Modifier.fBackgroundDrawable(drawable: Drawable) = drawBehind {
    drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
    drawable.draw(drawContext.canvas.nativeCanvas)
}