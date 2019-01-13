/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.base

import android.content.Intent
import android.os.Binder
import android.os.IBinder

abstract class BaseBindingService : BaseService() {

    private val binder = BaseBinder()

    override fun onBind(intent: Intent): IBinder? {
        return binder
    }

    override fun onUnbind(intent: Intent): Boolean {
        return true
    }

    @Suppress("unused")
    inner class BaseBinder : Binder() {

        val service: BaseBindingService get() = this@BaseBindingService
    }
}
