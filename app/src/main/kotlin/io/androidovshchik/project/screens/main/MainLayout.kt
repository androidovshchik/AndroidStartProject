/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.main

import io.androidovshchik.project.R
import org.jetbrains.anko.*
import org.jetbrains.anko.constraint.layout.constraintLayout

class MainLayout : AnkoComponent<MainActivity> {

    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        constraintLayout {
            padding = dip(16)

            textView {
                textColor = R.color.textDarkPrimary
                textSize = 17f
            }.lparams(width = matchParent, height = matchParent)
        }
    }
}