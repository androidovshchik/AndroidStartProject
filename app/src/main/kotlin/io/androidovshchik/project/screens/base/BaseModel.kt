/*
 * Copyright (c) 2019. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.screens.base

import java.io.Serializable

open class BaseModel : Serializable {

    @Transient
    var viewType = 0
}