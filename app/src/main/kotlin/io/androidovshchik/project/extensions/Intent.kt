/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.content.Context
import android.content.Intent
import io.androidovshchik.project.R

fun Intent.withChooser(context: Context): Intent = Intent.createChooser(this, context.getString(R.string.choose_app))