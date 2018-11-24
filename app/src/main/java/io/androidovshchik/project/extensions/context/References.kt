/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions.context

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.graphics.Point
import android.media.AudioManager
import android.os.PowerManager
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import com.gun0912.tedpermission.TedPermission
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

fun Context.isDenied(permission: String): Boolean = TedPermission.isDenied(applicationContext, permission)

fun Context.isGranted(permission: String): Boolean = areGranted(permission)

fun Context.areGranted(vararg permissions: String): Boolean = TedPermission.isGranted(applicationContext, *permissions)

fun Context.createSilentChannel() = notificationManager().createSilentChannel()

fun Context.createNoisyChannel() = notificationManager().createNoisyChannel()

fun Context.screenSize(): Point = windowManager().getScreenSize()

fun Context.windowSize(): Point = windowManager().getWindowSize()

fun Context.showKeyboard() = inputMethodManager().showKeyboard()

fun Context.hideKeyboard() = inputMethodManager().hideKeyboard()

fun Context.newWakeLock(name: String): PowerManager.WakeLock = powerManager().newWakeLock(name)

fun Context.cancelAlarm(receiverClass: Class<out BroadcastReceiver>) = alarmManager().cancel(newPendingReceiver(receiverClass))