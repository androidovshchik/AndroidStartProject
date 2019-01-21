/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun ByteArray.toBitmap(): Bitmap = BitmapFactory.decodeByteArray(this, 0, size)

fun ByteArray.toHex(separator: String = "", uppercase: Boolean = true): String = joinToString(separator) {
    String.format("%02${if (uppercase) "X" else "x"}", it)
}