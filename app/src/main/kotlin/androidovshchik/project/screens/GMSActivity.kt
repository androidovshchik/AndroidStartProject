/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project.screens

import android.os.Bundle
import androidovshchik.project.R
import androidovshchik.project.extensions.launchAppIntent
import androidovshchik.project.extensions.setCompoundXmlDrawables
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
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