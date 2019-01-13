/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.base

import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
abstract class BaseAdapter<T : BaseModel> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    @Suppress("MemberVisibilityCanBePrivate")
    val items = arrayListOf<T>()

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount() = items.size
}