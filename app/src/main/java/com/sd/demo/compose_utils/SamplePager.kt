package com.sd.demo.compose_utils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sd.demo.compose_utils.ui.theme.AppTheme
import com.sd.lib.compose.utils.fAutoPlay
import com.sd.lib.compose.utils.fCurrentPage
import com.sd.lib.compose.utils.fSettledPage
import kotlinx.coroutines.launch

class SamplePager : ComponentActivity() {
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
   var selectedIndex by remember { mutableIntStateOf(0) }

   val state = rememberPagerState { 10 }
   state.fCurrentPage { selectedIndex = it }

   val coroutineScope = rememberCoroutineScope()

   Column(
      modifier = modifier.fillMaxSize()
   ) {
      AutoPlay(state = state)

      ButtonsRow(
         count = 10,
         selectedTabIndex = selectedIndex,
         onClick = {
            coroutineScope.launch {
               state.animateScrollToPage(it)
            }
         },
      )

      PagerView(
         modifier = Modifier.weight(1f),
         state = state,
      )
   }
}

@Composable
private fun AutoPlay(
   modifier: Modifier = Modifier,
   state: PagerState,
) {
   var autoPlay by remember { mutableStateOf(false) }

   Row(
      modifier = modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically,
   ) {
      Text("AutoPlay:")
      Checkbox(
         modifier = modifier,
         checked = autoPlay,
         onCheckedChange = {
            autoPlay = it
         },
      )
   }

   if (autoPlay) {
      state.fAutoPlay()
   }
}

@Composable
private fun ButtonsRow(
   modifier: Modifier = Modifier,
   count: Int,
   selectedTabIndex: Int,
   onClick: (Int) -> Unit,
) {
   ScrollableTabRow(
      modifier = modifier,
      selectedTabIndex = selectedTabIndex,
      edgePadding = 0.dp,
   ) {
      repeat(count) { index ->
         Tab(
            modifier = Modifier.height(40.dp),
            selected = index == selectedTabIndex,
            onClick = { onClick(index) },
         ) {
            Text(text = index.toString())
         }
      }
   }
}

@Composable
private fun PagerView(
   modifier: Modifier = Modifier,
   state: PagerState,
) {
   state.fCurrentPage {
      logMsg { "fCurrentPage:$it" }
   }
   state.fSettledPage {
      logMsg { "fSettledPage:$it" }
   }

   HorizontalPager(
      state = state,
      modifier = modifier.fillMaxSize(),
   ) { index ->
      Box(
         modifier = Modifier.fillMaxSize(),
         contentAlignment = Alignment.Center,
      ) {
         Text(text = index.toString())
      }
   }
}