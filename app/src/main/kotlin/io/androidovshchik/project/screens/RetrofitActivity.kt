/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens

import android.os.Bundle
import io.androidovshchik.project.R
import io.androidovshchik.project.base.BaseActivity
import io.androidovshchik.project.extensions.context.toastShort
import io.androidovshchik.project.remote.PlaceholderFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RetrofitActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val service = PlaceholderFactory.createPlaceholderService()
        GlobalScope.launch(Dispatchers.Main) {
            val request = service.getPosts()
            val response = request.await()
            if (response.isSuccessful) {

            } else {
                toastShort("Error ${response.code()}")
            }
        }
    }
}
