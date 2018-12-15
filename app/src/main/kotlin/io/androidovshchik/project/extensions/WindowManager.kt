/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.annotation.SuppressLint
import android.graphics.Point
import android.view.WindowManager

fun WindowManager.getWindowSize(): Point {
    val size = Point()
    defaultDisplay.getSize(size)
    return size
}

@SuppressLint("ObsoleteSdkInt")
fun WindowManager.getScreenSize(): Point {
    val size = Point()
    defaultDisplay.getRealSize(size)
    return size
}