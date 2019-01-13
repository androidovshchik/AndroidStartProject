/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Build
import android.os.Looper
import java.io.File

val sep: String
    get() = File.separator

val newLine: String
    get() = System.getProperty("line.separator") ?: "\n"

val isUiThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()

fun createRect(width: Int, height: Int = width): Rect = Rect(0, 0, width, height)

fun createBitmap(width: Int, height: Int = width): Bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)

fun isLollipopPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isPiePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P