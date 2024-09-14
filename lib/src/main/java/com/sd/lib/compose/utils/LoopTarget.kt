package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState

/**
 * 循环返回[list]的项
 */
@Composable
fun <T> fLoopTarget(
   list: List<T>,
   onLoop: suspend () -> Unit,
): State<T?> {
   return remember {
      mutableStateOf(list.firstOrNull())
   }.also { state ->
      if (list.isEmpty()) {
         state.value = null
      } else {
         val listUpdated by rememberUpdatedState(list)
         val onLoopUpdated by rememberUpdatedState(onLoop)
         LaunchedEffect(Unit) {
            var index = 0
            while (true) {
               onLoopUpdated()
               index = (index + 1).takeIf { it <= listUpdated.lastIndex } ?: 0
               state.value = listUpdated.getOrNull(index)
            }
         }
      }
   }
}