/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.gms

import android.view.Gravity
import io.androidovshchik.project.R
import org.jetbrains.anko.*

class GMSLayout : AnkoComponent<GMSActivity> {

    override fun createView(ui: AnkoContext<GMSActivity>) = with(ui) {
        androidx.constraintlayout.widget.ConstraintLayout {
            padding = dip(16)

            textView(R.string.old_gms_version) {
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
            }.lparams(width = matchParent, height = wrapContent)
        }
    }
}