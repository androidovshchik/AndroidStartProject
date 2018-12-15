/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.androidovshchik.project.R

class FAQActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)
        setTitle(R.string.title_gms)
        //rcv_faq.s
    }
}