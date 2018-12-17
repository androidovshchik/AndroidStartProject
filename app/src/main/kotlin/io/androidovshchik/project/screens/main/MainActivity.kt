/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.main

import android.os.Bundle
import android.widget.EditText
import io.androidovshchik.project.base.BaseActivity
import io.androidovshchik.project.screens.gms.GMSActivity
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainLayout().setContentView(this)
        startActivity < GMSActivity::class.java > ("id" to 5)
        EditText(applicationContext).
    }
}
