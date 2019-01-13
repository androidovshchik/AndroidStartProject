/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.marcinmoskala.kotlinpreferences.PreferenceHolder

@SuppressLint("Registered")
@Suppress("unused")
abstract class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (!isMainProcess()) {
            return
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        PreferenceHolder.setContext(applicationContext)
    }

    abstract fun isMainProcess(): Boolean

    abstract fun catchAppError(t: Throwable)
}