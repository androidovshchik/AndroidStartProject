/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.content.res.Resources
import android.os.Looper
import java.io.File

val sep: String = File.separator

val newLine: String = System.getProperty("line.separator") ?: "\n"

fun dpToPx(dp: Int): Int = Math.round(dp * Resources.getSystem().displayMetrics.density)

fun spToPx(sp: Int): Float = sp * Resources.getSystem().displayMetrics.scaledDensity

fun isUiThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()