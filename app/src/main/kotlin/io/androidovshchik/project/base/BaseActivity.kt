/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.androidovshchik.project.extensions.context.newIntent
import io.androidovshchik.project.screens.GMSActivity
import io.androidovshchik.project.utils.CompositeJob

@SuppressLint("Registered")
@Suppress("MemberVisibilityCanBePrivate")
open class BaseActivity : AppCompatActivity() {

    protected val job = CompositeJob()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(applicationContext) != ConnectionResult.SUCCESS) {
            startActivity(newIntent(GMSActivity::class.java))
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelAll()
    }
}