/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused", "DEPRECATION")

package io.androidovshchik.project.extensions

import android.app.Activity
import android.app.ProgressDialog
import io.androidovshchik.project.R

fun Activity.newProgressDialog(): ProgressDialog = ProgressDialog.show(this, getString(R.string.wait), null, true)