/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions.context

import android.app.Activity
import android.app.PendingIntent
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Point
import android.media.MediaScannerConnection
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import io.androidovshchik.project.R
import io.androidovshchik.project.extensions.*
import org.jetbrains.anko.*

val Context.windowSize: Point
    get() = windowManager.windowSize

val Context.screenSize: Point
    get() = windowManager.screenSize

fun Context.createSilentChannel() = notificationManager.createSilentChannel()

fun Context.createNoisyChannel() = notificationManager.createNoisyChannel()

inline fun <reified T : Service> Context.isServiceRunning(): Boolean = activityManager.isServiceRunning<T>()

inline fun <reified T : Activity> Context.pendingActivityFor(flags: Int = PendingIntent.FLAG_UPDATE_CURRENT, vararg params: Pair<String, Any?>): PendingIntent =
    PendingIntent.getActivity(applicationContext, 0, intentFor<T>(*params), flags)

inline fun <reified T : BroadcastReceiver> Context.pendingReceiverFor(flags: Int = PendingIntent.FLAG_UPDATE_CURRENT, vararg params: Pair<String, Any?>): PendingIntent =
    PendingIntent.getBroadcast(applicationContext, 0, intentFor<T>(*params), flags)

inline fun <reified T : BroadcastReceiver> Context.pendingReceiverFor(action: String, flags: Int = PendingIntent.FLAG_UPDATE_CURRENT): PendingIntent =
    PendingIntent.getBroadcast(applicationContext, 0, Intent(action), flags)

inline fun <reified T : BroadcastReceiver> Context.cancelAlarm() = alarmManager.cancel(pendingReceiverFor<T>())

fun Context.createChooser(intent: Intent): Intent = Intent.createChooser(intent, getString(R.string.choose_app))

fun Context.openGooglePlay(newTask: Boolean = false) = browse("https://play.google.com/store/apps/details?id=$packageName", newTask)

fun Context.inflateView(@LayoutRes layout: Int, parent: ViewGroup? = null): View = LayoutInflater.from(applicationContext).inflate(layout, parent, false)

fun Context.allPermissions(): Array<String> = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS).requestedPermissions
    ?: arrayOf()

fun Context.scanFiles(vararg paths: String) = MediaScannerConnection.scanFile(applicationContext, paths, null, null)