/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.base

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNUSED_PARAMETER", "unused")
abstract class BaseViewHolder<T : BaseModel>(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindItem(item: T)

    val appContext: Context
        get() = itemView.context.applicationContext
}