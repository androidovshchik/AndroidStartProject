/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.project.extensions

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import com.google.android.material.snackbar.Snackbar

val View.appContext: Context
    get() = context.applicationContext

val View.parentActivity: Activity?
    get() {
        var context = context
        while (context is ContextWrapper) {
            if (context is Activity) {
                return context
            }
            context = context.baseContext
        }
        return null
    }

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

fun View.snackbar(message: CharSequence) = Snackbar
    .make(this, message, Snackbar.LENGTH_SHORT)
    .apply { show() }

fun View.longSnackbar(message: CharSequence) = Snackbar
    .make(this, message, Snackbar.LENGTH_LONG)
    .apply { show() }

fun View.snackbar(message: CharSequence, actionText: CharSequence, action: (View) -> Unit) = Snackbar
    .make(this, message, Snackbar.LENGTH_SHORT)
    .setAction(actionText, action)
    .apply { show() }

fun View.longSnackbar(message: CharSequence, actionText: CharSequence, action: (View) -> Unit) = Snackbar
    .make(this, message, Snackbar.LENGTH_LONG)
    .setAction(actionText, action)
    .apply { show() }