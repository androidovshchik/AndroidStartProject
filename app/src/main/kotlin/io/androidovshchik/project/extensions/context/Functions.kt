/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions.context

import android.app.AlarmManager
import android.app.Service
import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.SystemClock
import android.widget.Toast
import io.androidovshchik.project.extensions.isMarshmallowPlus
import io.androidovshchik.project.extensions.isOreoPlus
import io.androidovshchik.project.triggers.ToastTrigger
import timber.log.Timber

fun Context.toastShort(message: String) = sendBroadcast(newIntent(ToastTrigger::class.java).apply {
    putExtra(ToastTrigger.EXTRA_MESSAGE, message)
    putExtra(ToastTrigger.EXTRA_DURATION, Toast.LENGTH_SHORT)
})

fun Context.toastLong(message: String) = sendBroadcast(newIntent(ToastTrigger::class.java).apply {
    putExtra(ToastTrigger.EXTRA_MESSAGE, message)
    putExtra(ToastTrigger.EXTRA_DURATION, Toast.LENGTH_LONG)
})

fun Context.startActionView(link: String): Boolean {
    return try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
        true
    } catch (e: ActivityNotFoundException) {
        Timber.e(e)
        false
    }
}

fun Context.startForegroundService(serviceClass: Class<out Service>) {
    if (isOreoPlus()) {
        startForegroundService(newIntent(serviceClass))
    } else {
        startService(newIntent(serviceClass))
    }
}

fun Context.restartService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startService(newIntent(serviceClass))
}

fun Context.restartForegroundService(serviceClass: Class<out Service>) {
    stopService(serviceClass)
    startForegroundService(serviceClass)
}

fun Context.stopService(serviceClass: Class<out Service>) {
    if (isServiceRunning(serviceClass)) {
        stopService(newIntent(serviceClass))
    }
}

fun Context.nextAlarm(interval: Int, receiverClass: Class<out BroadcastReceiver>) {
    cancelAlarm(receiverClass)
    when {
        isMarshmallowPlus() -> alarmManager().setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
        else -> alarmManager().setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
    }
}