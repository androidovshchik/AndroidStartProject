/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import io.androidovshchik.project.CHARS
import java.util.*

fun Random.nextInt(range: IntRange): Int = range.start + nextInt(range.last - range.start + 1)

fun Random.nextString(range: IntRange, chars: String = CHARS): String {
    var output = ""
    val size = nextInt(range)
    for (i in 0 until size) {
        output += chars[Math.floor(Math.random() * chars.length).toInt()]
    }
    return output
}