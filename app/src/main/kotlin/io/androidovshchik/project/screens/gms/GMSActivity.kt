/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.gms

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.androidovshchik.project.R
import org.jetbrains.anko.clearTask
import org.jetbrains.anko.setContentView

class GMSActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GMSLayout().setContentView(this)
        setTitle(R.string.title_gms)
        /*btn_update.setCompoundDrawablesWithIntrinsicBounds(VectorDrawableCompat.create(resources,
            R.drawable.ic_shop_white_24dp, theme), null, null, null)
        btn_update.setOnClickListener {
            startActionView("https://play.google.com/store/apps/details?id=" +
                GoogleApiAvailability.GOOGLE_PLAY_SERVICES_PACKAGE)
        }*/
    }

    override fun onStart() {
        super.onStart()
        if (GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(applicationContext) == ConnectionResult.SUCCESS) {
            startActivity(packageManager.getLaunchIntentForPackage(packageName)!!.clearTask())
            finish()
        }
    }

    override fun onBackPressed() {}
}