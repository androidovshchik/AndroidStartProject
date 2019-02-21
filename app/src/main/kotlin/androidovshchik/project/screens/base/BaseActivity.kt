/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project.screens.base

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import androidovshchik.project.screens.GMSActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import org.jetbrains.anko.intentFor

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (GoogleApiAvailability.getInstance()
                .isGooglePlayServicesAvailable(applicationContext) != ConnectionResult.SUCCESS) {
            startActivity(intentFor<GMSActivity>())
            finish()
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }
}