/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.project.extensions

import android.content.Intent

fun Intent.asChooser(title: CharSequence): Intent = Intent.createChooser(this, title)