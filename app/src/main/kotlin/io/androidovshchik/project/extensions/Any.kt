/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.net.Uri
import android.os.Build
import android.os.Looper
import java.io.File

val sep: String
    get() = File.separator

val newLine: String
    get() = System.getProperty("line.separator") ?: "\n"

val mailTo: Uri
    get() = Uri.parse("mailto:")

val isUiThread: Boolean
    get() = Looper.myLooper() == Looper.getMainLooper()

fun isLollipopPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isPiePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P