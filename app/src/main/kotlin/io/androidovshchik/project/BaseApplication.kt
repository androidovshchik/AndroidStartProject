/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

@SuppressLint("Registered")
@Suppress("unused")
abstract class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (!isMainProcess()) {
            return
        }
        Thread.setDefaultUncaughtExceptionHandler { _, t ->
            Timber.e(t)
            catchAppError(t)
        }
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        ViewPump.init(ViewPump.builder()
            .addInterceptor(CalligraphyInterceptor(
                CalligraphyConfig.Builder()
                    .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                    .setFontAttrId(R.attr.fontPath)
                    .build()))
            .build())
    }

    abstract fun isMainProcess(): Boolean

    abstract fun catchAppError(t: Throwable)

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}