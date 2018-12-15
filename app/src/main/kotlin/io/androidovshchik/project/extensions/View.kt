/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.content.Context
import android.view.View

fun View.appContext(): Context = context.applicationContext

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

fun View.isGone(): Boolean = visibility == View.GONE

fun View.setVisible() {
    if (!isVisible()) {
        visibility = View.VISIBLE
    }
}

fun View.setInvisible() {
    if (!isInvisible()) {
        visibility = View.INVISIBLE
    }
}

fun View.setGone() {
    if (!isGone()) {
        visibility = View.GONE
    }
}
