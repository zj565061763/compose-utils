package com.sd.demo.compose_utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_utils.ui.theme.AppTheme
import com.sd.lib.compose.utils.fRememberUpdatedState
import kotlinx.coroutines.delay

class SampleRememberUpdatedState : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         AppTheme {
            Content()
         }
      }
   }
}

@Composable
private fun Content(
   modifier: Modifier = Modifier,
) {
   var toggle by remember { mutableStateOf(false) }

   val blockFalse = remember {
      {
         logMsg { "block false" }
      }
   }

   val blockTrue = remember {
      {
         logMsg { "block true" }
      }
   }

   val block = if (toggle) blockTrue else blockFalse

   Column(
      modifier = modifier.fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(10.dp),
   ) {
      Switch(
         checked = toggle,
         onCheckedChange = { toggle = it },
      )

      WhileLoop(block)
   }
}

@Composable
private fun WhileLoop(
   block: () -> Unit,
) {
   val blockUpdated by block.fRememberUpdatedState()
   LaunchedEffect(Unit) {
      while (true) {
         delay(1_000)
         blockUpdated()
      }
   }
}