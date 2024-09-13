package com.sd.demo.compose_utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.sd.demo.compose_utils.ui.theme.AppTheme
import com.sd.lib.compose.utils.fHorizontalLine
import com.sd.lib.compose.utils.fVerticalLine

class SampleConstrainLayout : ComponentActivity() {
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
   ConstraintLayout(
      modifier = modifier.fillMaxSize(),
   ) {
      val hLine = fHorizontalLine(bias = 0.5f, target = null)
      val vLine = fVerticalLine(bias = 0.5f, target = null)

      val refText = createRef()

      Text(
         "text",
         fontSize = 36.sp,
         modifier = Modifier.constrainAs(refText) {
            centerHorizontallyTo(vLine)
            centerVerticallyTo(hLine)
         },
      )
   }
}

@Preview
@Composable
private fun PreviewContentView() {
   Content()
}