/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItem
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItemHyperionPlugin
import io.androidovshchik.project.base.BaseApplication
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.acra.ACRA
import org.acra.ReportField
import org.acra.ReportingInteractionMode
import org.acra.config.ConfigurationBuilder
import timber.log.Timber
import java.io.BufferedReader

@SuppressLint("Registered")
@Suppress("unused")
class DebugApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()
        if (!isMainProcess()) {
            return
        }
        Timber.plant(DebugTree())
        ACRA.init(this, ConfigurationBuilder(this)
            .setMailTo(getString(R.string.developer_email))
            .setReportingInteractionMode(ReportingInteractionMode.DIALOG)
            .setResDialogTheme(R.style.DialogTheme_Support)
            .setResDialogText(R.string.error_crash)
            .setResDialogCommentPrompt(R.string.error_comment)
            .setCustomReportContent(
                ReportField.APP_VERSION_CODE,
                ReportField.APP_VERSION_NAME,
                ReportField.ANDROID_VERSION,
                ReportField.BRAND,
                ReportField.PHONE_MODEL,
                ReportField.PRODUCT,
                ReportField.USER_COMMENT,
                ReportField.USER_APP_START_DATE,
                ReportField.USER_CRASH_DATE,
                ReportField.STACK_TRACE,
                ReportField.LOGCAT
            ))
        SimpleItemHyperionPlugin.addItem(SimpleItem.Builder(getString(R.string.hype_report_name))
            .text(getString(R.string.hype_report_subtitle))
            .image(R.drawable.ic_send_grey_500_24dp)
            .clickListener {
                Toast.makeText(applicationContext, getString(R.string.wait),
                    Toast.LENGTH_SHORT).show()
                Observable.fromCallable {
                    val appLogs = try {
                        val process = Runtime.getRuntime().exec("logcat -d")
                        process.inputStream.bufferedReader().use(BufferedReader::readText)
                    } catch (e: Exception) {
                        Timber.e(e)
                        e.toString()
                    }
                    appLogs
                }.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { result: String ->
                        if (!TextUtils.isEmpty(result)) {
                            sendEmail(result)
                        } else {
                            Toast.makeText(applicationContext, getString(R.string.hype_report_failed_get),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }
            .build())
    }

    override fun isMainProcess(): Boolean {
        return !ACRA.isACRASenderServiceProcess()
    }

    private fun sendEmail(text: String) {
        try {
            startActivity(Intent.createChooser(Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.developer_email)))
                putExtra(Intent.EXTRA_SUBJECT, getString(R.string.hype_report_logs))
                putExtra(Intent.EXTRA_TEXT, text)
            }, getString(R.string.hype_report_send)))
        } catch (e: Exception) {
            Timber.e(e)
            Toast.makeText(applicationContext, getString(R.string.hype_report_failed_send),
                Toast.LENGTH_SHORT).show()
        }
    }
}