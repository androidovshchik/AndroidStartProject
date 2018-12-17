/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.os.Build
import android.os.Looper
import java.io.File

val sep: String
    get() = File.separator

val newLine: String
    get() = System.getProperty("line.separator") ?: "\n"

val isUiThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()

fun isLollipopPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMarshmallowPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNougatPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isOreoPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isPiePlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P