/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.PowerManager
import io.androidovshchik.project.extensions.context.newIntent
import io.androidovshchik.project.extensions.context.newWakeLock
import io.androidovshchik.project.triggers.ToastTrigger

@SuppressLint("Registered")
@Suppress("MemberVisibilityCanBePrivate", "unused")
open class BaseService : Service() {

    private var wakeLock: PowerManager.WakeLock? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @SuppressLint("WakelockTimeout")
    protected fun acquireWakeLock() {
        wakeLock = newWakeLock(javaClass.simpleName)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return Service.START_NOT_STICKY
    }

    protected fun showToast(message: String) {
        sendBroadcast(newIntent(ToastTrigger::class.java).apply {
            putExtra(ToastTrigger.EXTRA_MESSAGE, message)
        })
    }

    protected fun stopWork() {
        stopForeground(true)
        stopSelf()
    }

    override fun onDestroy() {
        super.onDestroy()
        wakeLock?.release()
        wakeLock = null
    }
}
