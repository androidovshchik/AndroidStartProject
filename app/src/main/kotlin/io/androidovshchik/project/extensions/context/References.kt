/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions.context

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.media.AudioManager
import android.media.MediaScannerConnection
import android.os.PowerManager
import android.telephony.TelephonyManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import io.androidovshchik.project.R
import io.androidovshchik.project.extensions.*

fun Context.activityManager(): ActivityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager

fun Context.audioManager(): AudioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

fun Context.inputMethodManager(): InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun Context.keyguardManager(): KeyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

fun Context.notificationManager(): NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

fun Context.alarmManager(): AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

fun Context.telephonyManager(): TelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

fun Context.windowManager(): WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager

fun Context.powerManager(): PowerManager = getSystemService(Context.POWER_SERVICE) as PowerManager

fun Context.isServiceRunning(serviceClass: Class<out Service>): Boolean = activityManager().isServiceRunning(serviceClass)

fun Context.cancelAlarm(receiverClass: Class<out BroadcastReceiver>) = alarmManager().cancel(newPendingReceiver(receiverClass))

fun Context.newWakeLock(name: String): PowerManager.WakeLock = powerManager().newWakeLock(name)

fun Context.createSilentChannel() = notificationManager().createSilentChannel()

fun Context.createNoisyChannel() = notificationManager().createNoisyChannel()

fun Context.screenSize(): Point = windowManager().getScreenSize()

fun Context.windowSize(): Point = windowManager().getWindowSize()

fun Context.newIntent(anyClass: Class<out Any>): Intent = Intent(applicationContext, anyClass)

fun Context.newPendingActivity(activityClass: Class<out Activity>): PendingIntent = PendingIntent.getActivity(applicationContext, 0, newIntent(activityClass), PendingIntent.FLAG_UPDATE_CURRENT)

fun Context.newPendingReceiver(receiverClass: Class<out BroadcastReceiver>): PendingIntent = PendingIntent.getBroadcast(applicationContext, 0, newIntent(receiverClass), PendingIntent.FLAG_UPDATE_CURRENT)

fun Context.newPendingReceiver(action: String): PendingIntent = PendingIntent.getBroadcast(applicationContext, 0, Intent(action), PendingIntent.FLAG_UPDATE_CURRENT)

fun Context.createChooser(intent: Intent): Intent = Intent.createChooser(intent, getString(R.string.choose_app))

fun Context.openGooglePlay() = startActionView("https://play.google.com/store/apps/details?id=$packageName")

fun Context.inflateView(@LayoutRes layout: Int, parent: ViewGroup? = null): View = LayoutInflater.from(applicationContext).inflate(layout, parent, false)

fun Context.allPermissions(): Array<String> = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
    ?: arrayOf()

fun Context.scanFiles(vararg paths: String) = MediaScannerConnection.scanFile(applicationContext, paths, null, null)