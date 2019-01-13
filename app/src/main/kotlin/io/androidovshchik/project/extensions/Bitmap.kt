/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.graphics.Bitmap
import timber.log.Timber
import java.io.ByteArrayOutputStream

inline fun <T : Bitmap?, R> T.use(block: (T) -> R): R {
    try {
        return block(this)
    } catch (e: Throwable) {
        throw e
    } finally {
        try {
            if (this?.isRecycled == false) {
                recycle()
            }
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }
}

fun Bitmap.toByteArray(): ByteArray {
    use {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }
}