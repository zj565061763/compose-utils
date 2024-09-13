package com.sd.demo.compose_utils

import junit.framework.TestCase.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
   @Test
   fun splitWithDelimiterTest() {
      "".splitWithDelimiter("1").let { list ->
         listOf("").assertContentEquals(list)
      }

      "a".splitWithDelimiter("1").let { list ->
         listOf("a").assertContentEquals(list)
      }

      "1".splitWithDelimiter("1").let { list ->
         listOf("1").assertContentEquals(list)
      }

      "111".splitWithDelimiter("1").let { list ->
         listOf("1", "1", "1").assertContentEquals(list)
      }

      "1xx".splitWithDelimiter("1").let { list ->
         listOf("1", "xx").assertContentEquals(list)
      }

      "xx1".splitWithDelimiter("1").let { list ->
         listOf("xx", "1").assertContentEquals(list)
      }

      "xx1xx".splitWithDelimiter("1").let { list ->
         listOf("xx", "1", "xx").assertContentEquals(list)
      }

      "1xx1xx".splitWithDelimiter("1").let { list ->
         listOf("1", "xx", "1", "xx").assertContentEquals(list)
      }

      "xx1xx1".splitWithDelimiter("1").let { list ->
         listOf("xx", "1", "xx", "1").assertContentEquals(list)
      }

      "1xx1xx1".splitWithDelimiter("1").let { list ->
         listOf("1", "xx", "1", "xx", "1").assertContentEquals(list)
      }
   }
}

private fun CharSequence.splitWithDelimiter(
   delimiter: String,
   ignoreCase: Boolean = false,
): List<String> {
   var currentOffset = 0
   var nextIndex = indexOf(delimiter, currentOffset, ignoreCase)
   if (nextIndex == -1) {
      return listOf(this.toString())
   }

   if (nextIndex == 0 && length == delimiter.length) {
      return listOf(this.toString())
   }

   val result = ArrayList<String>(10)
   do {
      val substring = substring(currentOffset, nextIndex)
      if (substring.isNotEmpty()) {
         result.add(substring)
      }
      result.add(delimiter)
      currentOffset = nextIndex + delimiter.length
      nextIndex = indexOf(delimiter, currentOffset, ignoreCase)
   } while (nextIndex != -1)

   val substring = substring(currentOffset, length)
   if (substring.isNotEmpty()) {
      result.add(substring)
   }
   return result
}

private fun List<*>.assertContentEquals(other: List<*>) {
   assertEquals(true, this.contentEquals(other))
}

private fun List<*>.contentEquals(other: List<*>): Boolean {
   if (this.size != other.size) return false
   for (index in this.indices) {
      if (this[index] != other[index]) {
         return false
      }
   }
   return true
}