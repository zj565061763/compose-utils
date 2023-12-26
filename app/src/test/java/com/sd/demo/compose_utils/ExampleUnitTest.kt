package com.sd.demo.compose_utils

import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val content = "1xxx1xxx1x"
        kotlin.run {
            val list = content.splitWithDelimiter("1")
            println(list)
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