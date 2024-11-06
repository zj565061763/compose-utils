package com.sd.lib.compose.utils

import android.content.res.Resources
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext

/**
 * 把[items]拼接为字符串，如果[items]为空返回空字符串，[items]内容对应的字符串规则如下：
 * * null -> 空字符串
 * * String -> 字符串本身
 * * Int -> 当作字符串资源ID读取字符串，如果资源ID不存在则转为字符串
 * * 其他 -> 调用对象的toString()
 */
@Composable
fun fString(vararg items: Any?): String {
   return when (items.size) {
      0 -> ""
      1 -> items.first().anyToString(resources())
      else -> {
         val resources = resources()
         return remember(resources, items) { items.itemsToString(resources) }
      }
   }
}

/**
 * 获取资源[Resources]
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
   LocalConfiguration.current
   return LocalContext.current.resources
}

private fun Array<*>.itemsToString(resources: Resources): String {
   val items = this
   return buildString {
      for (item in items) {
         append(item.anyToString(resources))
      }
   }
}

private fun Any?.anyToString(resources: Resources): String {
   return when (val any = this) {
      null -> ""
      is String -> any
      is Int -> try {
         resources.getString(any)
      } catch (e: Resources.NotFoundException) {
         any.toString()
      }
      else -> any.toString()
   }
}