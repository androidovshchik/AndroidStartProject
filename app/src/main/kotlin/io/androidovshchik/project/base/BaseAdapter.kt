/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import androidx.recyclerview.widget.RecyclerView

@Suppress("UNUSED_PARAMETER")
abstract class BaseAdapter<M : BaseModel> : RecyclerView.Adapter<BaseViewHolder<M>>() {

    @Suppress("MemberVisibilityCanBePrivate")
    val items = arrayListOf<M>()

    override fun getItemViewType(position: Int) = items[position].viewType

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int) {
        holder.bindItem(items[position])
    }

    override fun getItemCount() = items.size
}