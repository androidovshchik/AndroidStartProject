/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.utils

import android.annotation.SuppressLint
import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        return "${super.createStackElementTag(element)}:${element.methodName}:${element.lineNumber}"
    }

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return priority != Log.VERBOSE && priority != Log.DEBUG && priority != Log.INFO
    }

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (!isLoggable(tag, priority)) {
            return
        }
        super.log(priority, tag, message, t)
    }
}