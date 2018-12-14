/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.app.ActivityManager
import android.app.Service

@Suppress("DEPRECATION")
fun ActivityManager.isServiceRunning(serviceClass: Class<out Service>): Boolean {
    for (service in getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}