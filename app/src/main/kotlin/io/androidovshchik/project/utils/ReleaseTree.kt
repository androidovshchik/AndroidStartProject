/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.utils

import android.annotation.SuppressLint
import android.util.Log
import io.androidovshchik.project.extensions.newLine
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
        if (message.length < MAX_LOG_LENGTH) {
            if (priority == Log.ASSERT) {
                Log.wtf(tag, message)
            } else {
                Log.println(priority, tag, message)
            }
            return
        }
        var i = 0
        val length = message.length
        while (i < length) {
            var maxI = message.indexOf(newLine, i)
            if (maxI < 0) {
                maxI = length
            }
            do {
                val endI = Math.min(maxI, i + MAX_LOG_LENGTH)
                val part = message.substring(i, endI)
                if (priority == Log.ASSERT) {
                    Log.wtf(tag, part)
                } else {
                    Log.println(priority, tag, part)
                }
                i = endI
            } while (i < maxI)
            i++
        }
    }

    companion object {

        private const val MAX_LOG_LENGTH = 4000
    }
}