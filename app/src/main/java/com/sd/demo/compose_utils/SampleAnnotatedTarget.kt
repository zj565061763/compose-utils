package com.sd.demo.compose_utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_utils.ui.theme.AppTheme
import com.sd.lib.compose.utils.fAnnotatedTarget

class SampleAnnotatedTarget : ComponentActivity() {

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
   content: String = "123456789 123456789 123456789 123456789",
   target: String = "456",
) {
   val annotated = remember(content, target) {
      content.fAnnotatedTarget(target) {
         withStyle(SpanStyle(color = Color.Red)) {
            append(it)
         }
      }
   }

   Column(
      modifier = modifier
         .fillMaxSize()
         .padding(10.dp),
      horizontalAlignment = Alignment.CenterHorizontally,
   ) {
      Text(text = annotated)
   }
}