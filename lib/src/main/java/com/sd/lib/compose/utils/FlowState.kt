package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

@Composable
fun <T> fFlowStateWithLifecycle(
   /** 预览模式的值 */
   inspectionValue: T,
   /** 要监听的[Flow] */
   getFlow: GetFlowScope.() -> StateFlow<T>,
): State<T> {
   val inspectionMode = LocalInspectionMode.current
   if (inspectionMode) {
      return remember(inspectionValue) { mutableStateOf(inspectionValue) }
   }

   val coroutineScope = rememberCoroutineScope()
   val scopeImpl = remember { GetFlowScopeImpl(coroutineScope) }

   return remember {
      with(scopeImpl) { getFlow() }
   }.collectAsStateWithLifecycle()
}

interface GetFlowScope {
   fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T>
}

private class GetFlowScopeImpl(
   private val coroutineScope: CoroutineScope,
) : GetFlowScope {
   override fun <T> Flow<T>.asStateFlow(initialValue: T): StateFlow<T> {
      return stateIn(
         scope = coroutineScope,
         started = SharingStarted.Eagerly,
         initialValue = initialValue,
      )
   }
}