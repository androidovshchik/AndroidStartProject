/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.view.inputmethod.InputMethodManager

fun InputMethodManager.showKeyboard() = toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)

fun InputMethodManager.hideKeyboard() = toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)