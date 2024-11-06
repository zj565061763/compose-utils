package com.sd.lib.compose.utils

import androidx.lifecycle.Lifecycle
import kotlinx.coroutines.flow.first
import kotlin.coroutines.cancellation.CancellationException

internal suspend fun Lifecycle.fAtLeastState(
   state: Lifecycle.State = Lifecycle.State.STARTED,
): Boolean {
   if (currentState == Lifecycle.State.DESTROYED) throw CancellationException()
   if (currentState.isAtLeast(state)) return true
   currentStateFlow.first { it.isAtLeast(state) }
   return false
}