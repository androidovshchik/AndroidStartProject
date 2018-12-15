/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.text.Html
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.EditText

@Suppress("DEPRECATION")
fun EditText.setHtml(html: String) {
    if (isNougatPlus()) {
        setText(Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY))
    } else {
        setText(Html.fromHtml(html))
    }
}

fun EditText.maxLength(maxLength: Int) {
    val array = arrayOfNulls<InputFilter>(1)
    array[0] = InputFilter.LengthFilter(maxLength)
    filters = array
}

fun EditText.onlyNumbers() {
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or
        InputType.TYPE_NUMBER_FLAG_SIGNED
    keyListener = DigitsKeyListener.getInstance("0123456789")
}

fun EditText.onlyUppercase() {
    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
    filters = arrayOf(InputFilter.AllCaps())
}