/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.project.extensions

import android.app.ActivityManager
import android.app.Service

@Suppress("DEPRECATION")
inline fun <reified T : Service> ActivityManager.isServiceRunning(): Boolean {
    for (runningService in getRunningServices(Integer.MAX_VALUE)) {
        if (T::class.java.name == runningService.service.className) {
            return true
        }
    }
    return false
}