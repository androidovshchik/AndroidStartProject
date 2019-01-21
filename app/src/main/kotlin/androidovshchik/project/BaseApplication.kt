/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import androidovshchik.project.inject.module.AppModule
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.androidovshchik.project.inject.component.DaggerAppComponent
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("Registered")
@Suppress("unused", "DEPRECATION")
abstract class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        if (!isMainProcess()) {
            return
        }
        Thread.setDefaultUncaughtExceptionHandler { _, t ->
            Timber.e(t)
            catchAppError(t)
        }
        DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .build()
            .inject(this)
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
        MultiDex.install(applicationContext)
    }

    override fun activityInjector() = activityInjector
}