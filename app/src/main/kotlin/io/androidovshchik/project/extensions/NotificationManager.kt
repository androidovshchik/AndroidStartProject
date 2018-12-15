/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project.extensions

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import io.androidovshchik.project.NOISY_CHANNEL_ID
import io.androidovshchik.project.QUITE_CHANNEL_ID

fun NotificationManager.createSilentChannel() {
    if (isOreoPlus()) {
        val channel = NotificationChannel(QUITE_CHANNEL_ID, QUITE_CHANNEL_ID, NotificationManager.IMPORTANCE_LOW)
        channel.lockscreenVisibility = Notification.VISIBILITY_SECRET
        createNotificationChannel(channel)
    }
}

fun NotificationManager.createNoisyChannel() {
    if (isOreoPlus()) {
        val channel = NotificationChannel(NOISY_CHANNEL_ID, NOISY_CHANNEL_ID, NotificationManager.IMPORTANCE_HIGH)
        channel.enableLights(true)
        channel.enableVibration(true)
        channel.lightColor = Color.BLUE
        channel.vibrationPattern = longArrayOf(1000, 1000)
        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        createNotificationChannel(channel)
    }
}