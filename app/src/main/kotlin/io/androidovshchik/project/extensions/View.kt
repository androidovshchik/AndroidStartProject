/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.content.Context
import android.view.View

val View.appContext: Context
    get() = context.applicationContext

val View.isVisible: Boolean
    get() = visibility == View.VISIBLE

val View.isInvisible: Boolean
    get() = visibility == View.INVISIBLE

val View.isGone: Boolean
    get() = visibility == View.GONE

fun View.setVisible() {
    if (!isVisible) {
        visibility = View.VISIBLE
    }
}

fun View.setInvisible() {
    if (!isInvisible) {
        visibility = View.INVISIBLE
    }
}

fun View.setGone() {
    if (!isGone) {
        visibility = View.GONE
    }
}
