/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project.screens.base

import androidx.recyclerview.widget.RecyclerView

@Suppress("unused")
abstract class BaseAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    @Suppress("MemberVisibilityCanBePrivate")
    val items = arrayListOf<T>()

    override fun getItemViewType(position: Int) = position

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBindItem(items[position])
    }

    override fun getItemCount() = items.size
}