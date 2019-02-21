/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package androidovshchik.project.screens.base

import android.content.Context
import android.view.View
import androidovshchik.project.extensions.appContext
import androidx.recyclerview.widget.RecyclerView

@Suppress("UNUSED_PARAMETER", "unused")
abstract class BaseViewHolder<T>(itemView: View, viewType: Int) : RecyclerView.ViewHolder(itemView) {

    abstract fun onBindItem(item: T)

    val appContext: Context
        get() = itemView.appContext
}