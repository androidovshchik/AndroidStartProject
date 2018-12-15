/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.content.res.Resources
import android.os.Build
import android.os.Looper
import java.io.File

val sep: String = File.separator

val newLine: String = System.getProperty("line.separator") ?: "\n"

fun dpToPx(dp: Int): Int = Math.round(dp * Resources.getSystem().displayMetrics.density)

fun spToPx(sp: Int): Float = sp * Resources.getSystem().displayMetrics.scaledDensity

fun isUiThread(): Boolean = Looper.myLooper() == Looper.getMainLooper()

fun isPreLollipop(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP

fun isPreMarshmallow(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.M

fun isPreNougat(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.N

fun isPreOreo(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.O

fun isPrePie(): Boolean = Build.VERSION.SDK_INT < Build.VERSION_CODES.P

fun isLollipopPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun isMarshmallowPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun isNougatPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

fun isOreoPlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun isPiePlus(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P