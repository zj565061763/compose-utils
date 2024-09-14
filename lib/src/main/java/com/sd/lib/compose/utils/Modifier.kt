package com.sd.lib.compose.utils

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput

/**
 * 消费所有指针事件
 */
fun Modifier.fConsumePointerEvent(
   pass: PointerEventPass = PointerEventPass.Initial,
): Modifier = pointerInput(Unit) {
   awaitEachGesture {
      val event = awaitPointerEvent(pass)
      event.changes.forEach { it.consume() }
   }
}