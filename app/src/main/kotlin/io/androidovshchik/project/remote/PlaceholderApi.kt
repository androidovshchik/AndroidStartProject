/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.remote

import io.androidovshchik.project.models.Post
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.POST

interface PlaceholderApi {

    @POST("/posts")
    fun getPosts(): Deferred<Response<List<Post>>>
}