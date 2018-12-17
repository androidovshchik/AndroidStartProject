/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions.context

import android.app.AlarmManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.os.SystemClock
import android.widget.Toast
import io.androidovshchik.project.extensions.isMarshmallowPlus
import io.androidovshchik.project.extensions.isOreoPlus
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

inline fun <reified T : Service> Context.startForegroundService() {
    if (isOreoPlus()) {
        startForegroundService(newIntent(serviceClass))
    } else {
        startService<T>()
    }
}

inline fun <reified T : Service> Context.restartService() {
    stopService<T>()
    startService<T>()
}

fun Context.restartForegroundService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startForegroundService(serviceClass)
}

inline fun <reified T : Service> Context.stopService() {
    if (isServiceRunning<T>()) {
        org.jetbrains.anko.stopService<T>()
    }
}

fun Context.nextAlarm(interval: Int, receiverClass: Class<out BroadcastReceiver>) {
    cancelAlarm(receiverClass)
    when {
        isMarshmallowPlus() -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
        else -> alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
    }
}