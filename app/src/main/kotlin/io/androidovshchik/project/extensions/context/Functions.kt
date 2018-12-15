/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions.context

import android.annotation.SuppressLint
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
import android.net.Uri
import android.os.Build
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import io.androidovshchik.project.R
import io.androidovshchik.project.extensions.toFileUri
import timber.log.Timber

fun Context.allAppPermissions(): Array<String> = packageManager.getPackageInfo(packageName,
    PackageManager.GET_PERMISSIONS).requestedPermissions ?: arrayOf()

fun Context.inflateView(@LayoutRes layout: Int, parent: ViewGroup?): View = LayoutInflater.from(applicationContext).inflate(layout, parent, false)

fun Context.newIntent(anyClass: Class<out Any>): Intent = Intent(applicationContext, anyClass)

fun Context.newPendingActivity(activityClass: Class<out Activity>): PendingIntent = PendingIntent.getActivity(applicationContext, 0, newIntent(activityClass), PendingIntent.FLAG_UPDATE_CURRENT)

fun Context.newPendingReceiver(receiverClass: Class<out BroadcastReceiver>): PendingIntent = PendingIntent.getBroadcast(applicationContext, 0, newIntent(receiverClass), PendingIntent.FLAG_UPDATE_CURRENT)

fun Context.newPendingReceiver(action: String): PendingIntent = PendingIntent.getBroadcast(applicationContext, 0, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT)

fun Context.toastShort(text: String) = Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()

fun Context.toastLong(text: String) = Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()

fun Context.createChooser(intent: Intent): Intent = Intent.createChooser(intent, getString(R.string.choose_app))

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
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
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

@SuppressLint("NewApi")
fun Context.nextAlarm(interval: Int, receiverClass: Class<out BroadcastReceiver>) {
    cancelAlarm(receiverClass)
    val sdkInt = Build.VERSION.SDK_INT
    when {
        sdkInt >= Build.VERSION_CODES.M -> alarmManager().setExactAndAllowWhileIdle(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
        sdkInt >= android.os.Build.VERSION_CODES.KITKAT -> alarmManager().setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
        else -> alarmManager().set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
            SystemClock.elapsedRealtime() + interval, newPendingReceiver(receiverClass))
    }
}

fun Context.scanFile(path: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        MediaScannerConnection.scanFile(applicationContext, arrayOf(path), null, null)
    } else {
        sendBroadcast(Intent(Intent.ACTION_MEDIA_MOUNTED, path.toFileUri()))
    }
}