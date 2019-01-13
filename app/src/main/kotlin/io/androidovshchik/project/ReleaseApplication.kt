/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project

import android.annotation.SuppressLint
import io.androidovshchik.project.utils.ReleaseTree
import timber.log.Timber

@SuppressLint("Registered")
@Suppress("unused")
class ReleaseApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(ReleaseTree())
    }

    override fun isMainProcess(): Boolean {
        return true
    }

    override fun catchAppError(t: Throwable) {
        System.exit(1)
    }
}