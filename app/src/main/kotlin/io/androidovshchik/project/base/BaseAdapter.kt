/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import io.androidovshchik.project.extensions.appContext

@Suppress("UNUSED_PARAMETER")
abstract class BaseAdapter<M : BaseModel> : RecyclerView.Adapter<BaseViewHolder<M>>() {

    @Suppress("MemberVisibilityCanBePrivate")
    val items = arrayListOf<M>()

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size

    @Suppress("unused")
    protected fun inflateView(@LayoutRes layout: Int, parent: ViewGroup): View = LayoutInflater.from(parent.appContext())
        .inflate(layout, parent, false)
}