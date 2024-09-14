package com.sd.lib.compose.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

/**
 * 闪烁
 */
fun Modifier.fFlicker(
   /** 是否闪烁 */
   flicker: Boolean,
   /** 重复次数 */
   repeatCount: Int = 2,
   /** 初始透明度 */
   initialAlpha: Float = 1f,
   /** 重复回调 */
   onRepeat: suspend Animatable<Float, AnimationVector1D>.(Int) -> Unit = {
      animateTo(0f)
      animateTo(1f)
   },
   /** 结束回调（协程可能已经被取消） */
   onFinish: suspend Animatable<Float, AnimationVector1D>.() -> Unit = {},
): Modifier = if (flicker) {
   composed {
      val animatable = remember(initialAlpha) { Animatable(initialAlpha) }
      animatable.fRepeat(
         count = repeatCount,
         onRepeat = onRepeat,
         onFinish = onFinish,
      )
      graphicsLayer {
         this.alpha = animatable.value
      }
   }
} else {
   this
}

/**
 * 动画
 */
@SuppressLint("ComposableNaming")
@Composable
fun <T, V : AnimationVector> Animatable<T, V>.fRepeat(
   /** 重复次数 */
   count: Int,
   /** 结束回调（协程可能已经被取消） */
   onFinish: suspend Animatable<T, V>.() -> Unit = {},
   /** 重复回调 */
   onRepeat: suspend Animatable<T, V>.(Int) -> Unit,
) {
   require(count > 0)

   val countUpdated by rememberUpdatedState(count)
   val onFinishUpdated by rememberUpdatedState(onFinish)
   val onRepeatUpdated by rememberUpdatedState(onRepeat)

   LaunchedEffect(this) {
      try {
         var current = 0
         while (true) {
            current++
            onRepeatUpdated(current)
            if (current >= countUpdated) break
         }
      } finally {
         onFinishUpdated()
      }
   }
}