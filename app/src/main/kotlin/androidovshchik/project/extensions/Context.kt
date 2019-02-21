/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package androidovshchik.project.extensions

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.app.Service
import android.content.ActivityNotFoundException
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaScannerConnection
import android.os.SystemClock
import android.widget.Toast
import androidovshchik.project.receivers.EXTRA_DURATION
import androidovshchik.project.receivers.EXTRA_MESSAGE
import androidovshchik.project.receivers.ToastReceiver
import androidx.annotation.DrawableRes
import androidx.core.content.PermissionChecker.PermissionResult
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import org.jetbrains.anko.*

val Context.allAppPermissions: Array<String>
    get() = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
        ?: arrayOf()

val Context.launchAppIntent: Intent
    get() = packageManager.getLaunchIntentForPackage(packageName) ?: Intent()

inline fun <reified T : Activity> Context.pendingActivityFor(flags: Int = PendingIntent.FLAG_UPDATE_CURRENT, vararg params: Pair<String, Any?>): PendingIntent =
    PendingIntent.getActivity(applicationContext, 0, intentFor<T>(*params), flags)

inline fun <reified T : BroadcastReceiver> Context.pendingReceiverFor(flags: Int = PendingIntent.FLAG_UPDATE_CURRENT, vararg params: Pair<String, Any?>): PendingIntent =
    PendingIntent.getBroadcast(applicationContext, 0, intentFor<T>(*params), flags)

inline fun <reified T : BroadcastReceiver> Context.pendingReceiverFor(action: String, flags: Int = PendingIntent.FLAG_UPDATE_CURRENT): PendingIntent =
    PendingIntent.getBroadcast(applicationContext, 0, Intent(action), flags)

inline fun <reified T : Service> Context.isServiceRunning() = activityManager.isServiceRunning<T>()

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

inline fun <reified T : BroadcastReceiver> Context.cancelAlarm() = alarmManager.cancel(pendingReceiverFor<T>())

inline fun <reified T : BroadcastReceiver> Context.createAlarm(interval: Int) {
    cancelAlarm<T>()
    when {
        isMarshmallowPlus() -> alarmManager.setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, pendingReceiverFor<T>())
        else -> alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, pendingReceiverFor<T>())
    }
}

@PermissionResult
fun Context.areGranted(vararg permissions: String): Boolean {
    for (permission in permissions) {
        if (checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            return false
        }
    }
    return true
}

fun Context.bgToast(message: String) = sendBroadcast(intentFor<ToastReceiver>().apply {
    putExtra(EXTRA_MESSAGE, message)
    putExtra(EXTRA_DURATION, Toast.LENGTH_SHORT)
})

fun Context.longBgToast(message: String) = sendBroadcast(intentFor<ToastReceiver>().apply {
    putExtra(EXTRA_MESSAGE, message)
    putExtra(EXTRA_DURATION, Toast.LENGTH_LONG)
})

fun Context.createNoisyChannel() = notificationManager.createNoisyChannel()

fun Context.createSilentChannel() = notificationManager.createSilentChannel()

fun Context.createXmlDrawable(@DrawableRes drawable: Int) = if (drawable != 0) {
    VectorDrawableCompat.create(resources, drawable, theme)
} else null

fun Context.scanFiles(vararg paths: String) {
    MediaScannerConnection.scanFile(applicationContext, paths, null, null)
}

fun Context.openGooglePlay() {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, "market://details?id=$packageName".toUri()).newTask())
    } catch (e: ActivityNotFoundException) {
        browse("https://play.google.com/store/apps/details?id=$packageName", true)
    }
}