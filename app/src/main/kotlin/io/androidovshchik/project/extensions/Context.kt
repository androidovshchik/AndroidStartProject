/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.app.AlarmManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.os.SystemClock
import android.widget.Toast
import io.androidovshchik.project.receivers.ToastReceiver
import org.jetbrains.anko.alarmManager
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startService
import org.jetbrains.anko.stopService

fun Context.bgToast(message: String) = sendBroadcast(intentFor<ToastReceiver>().apply {
    putExtra(ToastReceiver.EXTRA_MESSAGE, message)
    putExtra(ToastReceiver.EXTRA_DURATION, Toast.LENGTH_SHORT)
})

fun Context.longBgToast(message: String) = sendBroadcast(intentFor<ToastReceiver>().apply {
    putExtra(ToastReceiver.EXTRA_MESSAGE, message)
    putExtra(ToastReceiver.EXTRA_DURATION, Toast.LENGTH_LONG)
})

inline fun <reified T : Service> Context.restartService() {
    stopService<T>()
    startService<T>()
}

inline fun <reified T : Service> Context.restartForegroundService() {
    stopService<T>()
    startForegroundService<T>()
}

inline fun <reified T : Service> Context.startForegroundService() {
    if (isOreoPlus()) {
        startForegroundService(intentFor<T>())
    } else {
        startService<T>()
    }
}

inline fun <reified T : BroadcastReceiver> Context.nextAlarm(interval: Int) {
    cancelAlarm<T>()
    when {
        isMarshmallowPlus() -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, pendingReceiverFor<T>())
        else -> alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, pendingReceiverFor<T>())
    }
}