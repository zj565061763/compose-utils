package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
fun <T> fFlowStateWithLifecycle(
   inspectionValue: T,
   getFlow: GetFlowScope.() -> StateFlow<T>,
): State<T> {
   if (LocalInspectionMode.current) {
      return remember(inspectionValue) {
         mutableStateOf(inspectionValue)
      }
   }

   val coroutineScope = rememberCoroutineScope()
   val getFlowUpdated by rememberUpdatedState(getFlow)
   return remember(coroutineScope) {
      with(GetFlowScopeImpl(coroutineScope)) { getFlowUpdated() }
   }.collectAsStateWithLifecycle()
}

interface GetFlowScope {
   fun <T> Flow<T>.asStateFlow(
      initialValue: T,
      started: SharingStarted = SharingStarted.Lazily,
   ): StateFlow<T>
}

private class GetFlowScopeImpl(
   private val coroutineScope: CoroutineScope,
) : GetFlowScope {
   override fun <T> Flow<T>.asStateFlow(
      initialValue: T,
      started: SharingStarted,
   ): StateFlow<T> {
      return stateIn(
         scope = coroutineScope,
         started = started,
         initialValue = initialValue,
      )
   }
}