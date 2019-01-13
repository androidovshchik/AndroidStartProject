/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused", "NOTHING_TO_INLINE")

package io.androidovshchik.project.extensions

import android.net.Uri
import android.util.Patterns

val String.isEmail: Boolean
    get() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toUri(): Uri = Uri.parse(this)

fun String.toFileUri(): Uri = Uri.parse("file://$this")