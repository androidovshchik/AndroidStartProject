/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.androidovshchik.project.R
import io.androidovshchik.project.extensions.launchAppIntent
import io.androidovshchik.project.extensions.setCompoundXmlDrawables
import kotlinx.android.synthetic.main.activity_gms.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.clearTask

class GMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gms)
        setTitle(R.string.title_gms)
        btn_update.setCompoundXmlDrawables(R.drawable.ic_shop_white_24dp)
        btn_update.setOnClickListener {
            browse("https://play.google.com/store/apps/details?id=" + GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE)
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(applicationContext) == ConnectionResult.SUCCESS) {
            startActivity(launchAppIntent.clearTask())
            finish()
        }
    }

    override fun onBackPressed() {}
}