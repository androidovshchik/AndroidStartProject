/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.gui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.androidovshchik.project.R
import io.androidovshchik.project.extensions.context.startActionView
import kotlinx.android.synthetic.main.activity_gms.*

class GMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gms)
        setTitle(R.string.title_gms)
        update.setCompoundDrawablesWithIntrinsicBounds(VectorDrawableCompat.create(resources,
            R.drawable.ic_shop_white_24dp, theme), null, null, null)
        update.setOnClickListener {
            startActionView("https://play.google.com/store/apps/details?id=" +
                GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE)
        }
    }

    override fun onStart() {
        super.onStart()
        if (GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(applicationContext) == ConnectionResult.SUCCESS) {
            startActivity(packageManager.getLaunchIntentForPackage(packageName)!!.apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            })
            finish()
        }
    }

    override fun onBackPressed() {}
}