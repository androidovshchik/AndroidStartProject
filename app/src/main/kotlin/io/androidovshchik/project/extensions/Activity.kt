/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused", "DEPRECATION")

package io.androidovshchik.project.extensions

import android.app.Activity
import android.app.ProgressDialog
import com.gun0912.tedpermission.TedPermissionResult
import com.tedpark.tedpermission.rx2.TedRx2Permission
import io.androidovshchik.project.R
import io.reactivex.Single

fun Activity.newProgressDialog(): ProgressDialog = ProgressDialog.show(this, getString(R.string.wait), null, true)

fun Activity.requestPermissions(vararg permissions: String): Single<TedPermissionResult> {
    val builder = TedRx2Permission.with(this)
    builder.setPermissions(*permissions)
    builder.setDeniedTitle(R.string.permission_title)
    builder.setDeniedMessage(R.string.permission_message)
    builder.setDeniedCloseButtonText(R.string.permission_close)
    builder.setGotoSettingButtonText(R.string.permission_setting)
    return builder.request()
}