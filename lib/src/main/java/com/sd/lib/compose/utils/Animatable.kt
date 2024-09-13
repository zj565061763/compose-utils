package com.sd.lib.compose.utils

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState

/**
 * 动画
 */
@SuppressLint("ComposableNaming")
@Composable
fun <T, V : AnimationVector> Animatable<T, V>.fRepeat(
   /** 重复次数 */
   count: Int,
   /** 开始回调 */
   onStart: suspend Animatable<T, V>.() -> Unit = {},
   /** 结束回调 */
   onFinish: suspend Animatable<T, V>.() -> Unit = {},
   /** 重复回调 */
   onRepeat: suspend Animatable<T, V>.() -> Unit,
) {
   require(count > 0)

   val countUpdated by rememberUpdatedState(count)
   val onStartUpdated by rememberUpdatedState(onStart)
   val onFinishUpdated by rememberUpdatedState(onFinish)
   val onRepeatUpdated by rememberUpdatedState(onRepeat)

   LaunchedEffect(this) {
      onStartUpdated()

      var current = 0
      while (current < countUpdated) {
         onRepeatUpdated()
         current++
      }

      onFinishUpdated()
   }
}