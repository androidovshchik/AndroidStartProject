/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.utils

import timber.log.Timber

class DebugTree : Timber.DebugTree() {

    override fun createStackElementTag(element: StackTraceElement): String {
        return "${super.createStackElementTag(element)}:${element.methodName}:${element.lineNumber}"
    }
}