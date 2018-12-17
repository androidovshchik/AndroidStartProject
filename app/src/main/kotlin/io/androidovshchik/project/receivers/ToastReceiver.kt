/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ToastReceiver : BroadcastReceiver() {

    companion object {

        const val EXTRA_MESSAGE = "message"
        const val EXTRA_DURATION = "duration"
    }

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.hasExtra(EXTRA_MESSAGE)) {
            val duration = intent.getIntExtra(EXTRA_DURATION, Toast.LENGTH_SHORT)
            Toast.makeText(context, intent.getStringExtra(EXTRA_MESSAGE), duration)
                .show()
        }
    }
}