/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.androidovshchik.project.utils.CompositeJob

@Suppress("unused")
abstract class BaseFragment : Fragment() {

    abstract val layout: Int

    @Suppress("MemberVisibilityCanBePrivate")
    protected val job = CompositeJob()

    protected val args: Bundle
        get() = arguments ?: Bundle()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancelAll()
    }
}