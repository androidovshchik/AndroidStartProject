/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.extensions

import android.content.pm.Signature
import java.security.MessageDigest

fun Signature.hash(separator: String, algorithm: String): String = MessageDigest.getInstance(algorithm).let {
    it.update(toByteArray())
    return it.digest().toHex(separator)
}