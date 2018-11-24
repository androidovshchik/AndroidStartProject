/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.content.Context
import android.view.View

fun View.appContext(): Context = context.applicationContext

fun View.isVisible(): Boolean = this.visibility == View.VISIBLE

fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE

fun View.isGone(): Boolean = this.visibility == View.GONE

fun View.setVisible() {
    this.visibility = View.VISIBLE
}

fun View.setInvisible() {
    this.visibility = View.INVISIBLE
}

fun View.setGone() {
    this.visibility = View.GONE
}
