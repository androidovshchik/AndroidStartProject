/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.remote

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PlaceholderFactory {

    fun createPlaceholderService(): PlaceholderApi {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder()
                .setLenient()
                .create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(PlaceholderApi::class.java)
    }
}