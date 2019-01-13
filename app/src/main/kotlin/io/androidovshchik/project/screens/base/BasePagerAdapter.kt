/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.base

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

@Suppress("MemberVisibilityCanBePrivate")
class BasePagerAdapter(manager: FragmentManager, val fragments: ArrayList<BaseFragment> = arrayListOf()) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): BaseFragment = fragments[position]

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int) = fragments[position].title

    override fun getItemPosition(any: Any): Int {
        return POSITION_NONE
    }
}