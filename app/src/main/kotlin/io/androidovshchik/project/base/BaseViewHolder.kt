/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNUSED_PARAMETER")
abstract class BaseViewHolder<M : BaseModel>(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindItem(item: M)

    fun context(): Context = itemView.context.applicationContext
}