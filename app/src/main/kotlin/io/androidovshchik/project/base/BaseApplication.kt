/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.marcinmoskala.kotlinpreferences.PreferenceHolder

@SuppressLint("Registered")
@Suppress("unused")
abstract class BaseApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (isMainProcess()) {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
            PreferenceHolder.setContext(applicationContext)
        }
    }

    abstract fun isMainProcess(): Boolean
}