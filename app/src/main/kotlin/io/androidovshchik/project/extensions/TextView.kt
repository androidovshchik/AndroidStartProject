/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.text.Html
import android.text.InputFilter
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.TextView
import androidx.annotation.DrawableRes

fun TextView.setCompoundXmlDrawables(@DrawableRes vararg drawables: Int) {
    setCompoundDrawablesWithIntrinsicBounds(
        appContext.createXmlDrawable(drawables.getOrNull(0) ?: 0),
        appContext.createXmlDrawable(drawables.getOrNull(1) ?: 0),
        appContext.createXmlDrawable(drawables.getOrNull(2) ?: 0),
        appContext.createXmlDrawable(drawables.getOrNull(3) ?: 0)
    )
}

@Suppress("DEPRECATION")
fun TextView.setHtml(html: String, flags: Int = 0 /*== Html.FROM_HTML_MODE_LEGACY*/) {
    text = if (isNougatPlus()) {
        Html.fromHtml(html, flags)
    } else {
        Html.fromHtml(html)
    }
}

fun TextView.setMaxLength(length: Int) {
    val array = arrayOfNulls<InputFilter>(1)
    array[0] = InputFilter.LengthFilter(length)
    filters = array
}

fun TextView.setOnlyNumbers() {
    inputType = InputType.TYPE_CLASS_NUMBER
    keyListener = DigitsKeyListener.getInstance(NUMBERS)
}

fun TextView.setOnlyUppercase() {
    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
    filters = arrayOf(InputFilter.AllCaps())
}