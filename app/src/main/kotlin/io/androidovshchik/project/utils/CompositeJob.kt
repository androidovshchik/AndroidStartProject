/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.utils

import kotlinx.coroutines.Job

class CompositeJob {

    private val map = hashMapOf<String, Job?>()

    fun add(job: Job, key: String = job.hashCode().toString()) = map.put(key, job)

    fun cancel(key: String) {
        map[key]?.cancel()
        map.remove(key)
    }

    fun cancelAll() {
        map.forEach {
            it.value?.cancel()
        }
        map.clear()
    }
}