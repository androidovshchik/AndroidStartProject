/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.project.extensions

import android.content.res.TypedArray
import timber.log.Timber

inline fun <T : TypedArray?, R> T.use(block: (T) -> R): R {
    try {
        return block(this)
    } catch (e: Throwable) {
        throw e
    } finally {
        try {
            this?.recycle()
        } catch (t: Throwable) {
            Timber.e(t)
        }
    }
}