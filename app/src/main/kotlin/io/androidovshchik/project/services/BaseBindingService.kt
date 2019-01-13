/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.services

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
