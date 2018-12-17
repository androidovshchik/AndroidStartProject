/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.media.MediaScannerConnection
import android.os.SystemClock
import android.widget.Toast
import io.androidovshchik.project.R
import io.androidovshchik.project.receivers.ToastReceiver
import org.jetbrains.anko.*

fun Context.bgToast(message: String) = sendBroadcast(intentFor<ToastReceiver>().apply {
    putExtra(ToastReceiver.EXTRA_MESSAGE, message)
    putExtra(ToastReceiver.EXTRA_DURATION, Toast.LENGTH_SHORT)
})

fun Context.longBgToast(message: String) = sendBroadcast(intentFor<ToastReceiver>().apply {
    putExtra(ToastReceiver.EXTRA_MESSAGE, message)
    putExtra(ToastReceiver.EXTRA_DURATION, Toast.LENGTH_LONG)
})

fun Context.createChooser(intent: Intent): Intent = Intent.createChooser(intent, getString(R.string.choose_app))

inline fun <reified T : Activity> Context.pendingActivityFor(flags: Int = PendingIntent.FLAG_UPDATE_CURRENT, vararg params: Pair<String, Any?>): PendingIntent =
    PendingIntent.getActivity(applicationContext, 0, intentFor<T>(*params), flags)

inline fun <reified T : BroadcastReceiver> Context.pendingReceiverFor(flags: Int = PendingIntent.FLAG_UPDATE_CURRENT, vararg params: Pair<String, Any?>): PendingIntent =
    PendingIntent.getBroadcast(applicationContext, 0, intentFor<T>(*params), flags)

inline fun <reified T : BroadcastReceiver> Context.pendingReceiverFor(action: String, flags: Int = PendingIntent.FLAG_UPDATE_CURRENT): PendingIntent =
    PendingIntent.getBroadcast(applicationContext, 0, Intent(action), flags)

inline fun <reified T : Service> Context.isServiceRunning(): Boolean = activityManager.isServiceRunning<T>()

inline fun <reified T : Service> Context.restartService() {
    if (isServiceRunning<T>()) {
        stopService<T>()
    }
    startService<T>()
}

inline fun <reified T : Service> Context.restartForegroundService() {
    if (isServiceRunning<T>()) {
        stopService<T>()
    }
    startForegroundService<T>()
}

inline fun <reified T : Service> Context.startForegroundService() {
    if (isOreoPlus()) {
        startForegroundService(intentFor<T>())
    } else {
        startService<T>()
    }
}

fun Context.createSilentChannel() = notificationManager.createSilentChannel()

fun Context.createNoisyChannel() = notificationManager.createNoisyChannel()

inline fun <reified T : BroadcastReceiver> Context.createAlarm(interval: Int) {
    cancelAlarm<T>()
    when {
        isMarshmallowPlus() -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, pendingReceiverFor<T>())
        else -> alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, pendingReceiverFor<T>())
    }
}

inline fun <reified T : BroadcastReceiver> Context.cancelAlarm() = alarmManager.cancel(pendingReceiverFor<T>())

val Context.allPermissions: Array<String>
    get() = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
        ?: arrayOf()

val Context.windowSize: Point
    get() = windowManager.windowSize

val Context.screenSize: Point
    get() = windowManager.screenSize

fun Context.openGooglePlay(newTask: Boolean = false) = browse("https://play.google.com/store/apps/details?id=$packageName", newTask)

fun Context.scanFiles(vararg paths: String) = MediaScannerConnection.scanFile(applicationContext, paths, null, null)