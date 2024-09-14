package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner

/**
 * 生命周期大于等于[atLeastState]时循环返回[list]的项
 */
@Composable
fun <T> fLoopTarget(
   list: List<T>,
   atLeastState: Lifecycle.State = Lifecycle.State.STARTED,
   onLoop: suspend () -> Unit,
): State<T?> {
   return remember {
      mutableStateOf(list.firstOrNull())
   }.also { state ->
      if (list.isEmpty()) {
         state.value = null
      } else {
         val listUpdated by rememberUpdatedState(list)
         val atLeastStateUpdated by rememberUpdatedState(atLeastState)
         val onLoopUpdated by rememberUpdatedState(onLoop)
         val lifecycle = LocalLifecycleOwner.current.lifecycle
         LaunchedEffect(lifecycle) {
            var index = 0
            while (lifecycle.currentState != Lifecycle.State.DESTROYED) {
               onLoopUpdated()
               lifecycle.fAtLeastState(atLeastStateUpdated)
               index = (index + 1).takeIf { it <= listUpdated.lastIndex } ?: 0
               state.value = listUpdated.getOrNull(index)
            }
         }
      }
   }
}