/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import java.util.*

const val NUMBERS = "0123456789"
const val UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz"
const val LETTERS = UPPER_LETTERS + LOWER_LETTERS
const val CHARS = NUMBERS + LETTERS

fun Random.nextInt(range: IntRange): Int = range.start + nextInt(range.last - range.start + 1)

fun Random.nextString(range: IntRange, chars: String = CHARS): String {
    var string = ""
    for (i in 0 until nextInt(range)) {
        string += chars[Math.floor(Math.random() * chars.length).toInt()]
    }
    return string
}