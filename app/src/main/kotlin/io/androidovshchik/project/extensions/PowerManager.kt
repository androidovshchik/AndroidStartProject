/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.annotation.SuppressLint
import android.os.PowerManager

@SuppressLint("WakelockTimeout")
fun PowerManager.newWakeLock(name: String): PowerManager.WakeLock {
    val wakeLock = newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, name)
    wakeLock.acquire()
    return wakeLock
}