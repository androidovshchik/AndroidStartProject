/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.project.extensions

import java.util.*

const val NUMBERS = "0123456789"
const val UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
val LOWER_LETTERS = UPPER_LETTERS.toLowerCase()
val LETTERS = UPPER_LETTERS + LOWER_LETTERS
val CHARS = NUMBERS + LETTERS

fun Random.nextInt(range: IntRange) = range.start + nextInt(range.last - range.start + 1)

fun Random.nextString(range: IntRange, chars: String = CHARS) = StringBuilder().apply {
    for (i in 0 until nextInt(range)) {
        append(chars[Math.floor(Math.random() * chars.length).toInt()])
    }
}.toString()