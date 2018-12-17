/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.gms

import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.constraint.layout.constraintLayout
import org.jetbrains.anko.dip
import org.jetbrains.anko.padding

class GMSLayout : AnkoComponent<GMSActivity> {

    override fun createView(ui: AnkoContext<GMSActivity>) = with(ui) {
        constraintLayout {
            padding = dip(16)

            /*textView(R.string.old_gms_version) {
                gravity = Gravity.CENTER
                textColor = R.color.textLightPrimary
                textSize = 17f
                app:layout_constraintBottom_toBottomOf = parent
                app:layout_constraintLeft_toLeftOf = parent
                app:layout_constraintRight_toRightOf = parent
                app:layout_constraintTop_toTopOf = parent
            }.lparams(width = wrapContent, height = wrapContent)
            button(R.string.update_gms) {
                drawablePadding = dip(8)
                gravity = Gravity.CENTER
                id = Ids.btn_update
                padding = dip(12)
                app:layout_constraintBottom_toBottomOf = parent
            }.lparams(width = matchParent, height = wrapContent)*/
        }
    }
}