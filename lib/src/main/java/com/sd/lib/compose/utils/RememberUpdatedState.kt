package com.sd.lib.compose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.rememberUpdatedState

@Composable
fun <T> T.fRememberUpdatedState(): State<T> {
   return rememberUpdatedState(this)
}