/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project

import android.annotation.SuppressLint
import androidovshchik.project.timber.ReleaseTree
import timber.log.Timber

@SuppressLint("Registered")
@Suppress("unused")
class ReleaseApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(ReleaseTree())
    }
}