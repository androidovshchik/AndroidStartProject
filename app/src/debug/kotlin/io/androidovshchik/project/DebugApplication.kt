/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project

import android.annotation.SuppressLint
import android.content.Intent
import android.text.TextUtils
import android.widget.Toast
import com.facebook.stetho.Stetho
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItem
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItemHyperionPlugin
import io.androidovshchik.project.base.BaseApplication
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.acra.ACRA
import org.acra.config.CoreConfigurationBuilder
import org.acra.config.DialogConfigurationBuilder
import org.acra.config.MailSenderConfigurationBuilder
import org.acra.data.StringFormat
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
        Stetho.initializeWithDefaults(applicationContext)
        val acraBuilder = CoreConfigurationBuilder(applicationContext)
        acraBuilder.setBuildConfigClass(BuildConfig::class.java)
            .setReportFormat(StringFormat.KEY_VALUE_LIST)
        acraBuilder.getPluginConfigurationBuilder(MailSenderConfigurationBuilder::class.java)
            .setMailTo(getString(R.string.developer_email))
            .setReportFileName("logs.txt")
            .setReportAsFile(true)
            .setEnabled(true)
        acraBuilder.getPluginConfigurationBuilder(DialogConfigurationBuilder::class.java)
            .setResTheme(R.style.CrashDialogTheme)
            .setResTitle(R.string.error_title)
            .setResText(R.string.error_text)
            .setResCommentPrompt(R.string.error_comment)
            .setResEmailPrompt(R.string.error_email)
            .setEnabled(true)
        ACRA.init(this, acraBuilder)
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