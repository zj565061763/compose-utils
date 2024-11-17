package com.sd.lib.compose.utils

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.app.OnNewIntentProvider
import androidx.core.util.Consumer

@Composable
fun <T> android.app.Activity.fIntentExtra(
   getExtra: (Intent) -> T,
): T {
   var intentState by remember(intent) { mutableStateOf(intent) }
   if (this is OnNewIntentProvider) {
      DisposableEffect(this) {
         val consumer = Consumer<Intent> { intentState = it }
         addOnNewIntentListener(consumer)
         onDispose { removeOnNewIntentListener(consumer) }
      }
   }
   return remember(intentState) { getExtra(intentState) }
}

@Composable
fun android.app.Activity.FFinish() {
   LaunchedEffect(this) {
      finish()
   }
}