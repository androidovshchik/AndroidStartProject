/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import com.facebook.stetho.Stetho
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItem
import com.github.takahirom.hyperion.plugin.simpleitem.SimpleItemHyperionPlugin
import io.androidovshchik.project.base.BaseApplication
import io.androidovshchik.project.extensions.context.createChooser
import io.androidovshchik.project.extensions.context.toastShort
import io.androidovshchik.project.extensions.isLollipopPlus
import io.androidovshchik.project.extensions.newTask
import io.androidovshchik.project.utils.DebugTree
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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

    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        if (!isMainProcess()) {
            return
        }
        Timber.plant(DebugTree())
        Timber.d("*** DEVICE INFO ***")
        Timber.d("SDK_INT: ${Build.VERSION.SDK_INT}")
        Timber.d("OS_ARCH: ${System.getProperty("os.arch")}")
        if (isLollipopPlus()) {
            Timber.d("SUPPORTED_32_BIT_ABIS: ${Build.SUPPORTED_32_BIT_ABIS.asList()}")
            Timber.d("SUPPORTED_64_BIT_ABIS: ${Build.SUPPORTED_64_BIT_ABIS.asList()}")
        } else {
            Timber.d("ABI: ${Build.CPU_ABI}")
            Timber.d("ABI2: ${Build.CPU_ABI2}")
        }
        Timber.d("MANUFACTURER: ${Build.MANUFACTURER}")
        Timber.d("BRAND: ${Build.BRAND}")
        Timber.d("MODEL: ${Build.MODEL}")
        Timber.d("DEVICE: ${Build.DEVICE}")
        Timber.d("PRODUCT: ${Build.PRODUCT}")
        Timber.d("*******************")
        Stetho.initializeWithDefaults(applicationContext)
        ACRA.init(this, CoreConfigurationBuilder(applicationContext)
            .setBuildConfigClass(BuildConfig::class.java)
            .setReportFormat(StringFormat.KEY_VALUE_LIST)
            .setEnabled(true).apply {
                getPluginConfigurationBuilder(MailSenderConfigurationBuilder::class.java)
                    .setMailTo(getString(R.string.developer_email))
                    .setResSubject(R.string.crash_subject)
                    .setReportFileName("logs.txt")
                    .setReportAsFile(true)
                    .setEnabled(true)
                getPluginConfigurationBuilder(DialogConfigurationBuilder::class.java)
                    .setResTheme(R.style.DialogTheme_Crash)
                    .setResTitle(R.string.crash_title)
                    .setResText(R.string.crash_text)
                    .setResCommentPrompt(R.string.crash_comment)
                    .setResEmailPrompt(R.string.crash_email)
                    .setEnabled(true)
            })
        SimpleItemHyperionPlugin.addItem(SimpleItem.Builder()
            .title(getString(R.string.hype_report_title))
            .text(getString(R.string.hype_report_subtitle))
            .image(R.drawable.ic_send_grey_500_24dp)
            .clickListener {
                toastShort(getString(R.string.wait))
                try {
                    CoroutineScope(Dispatchers.IO).launch {
                        val process = Runtime.getRuntime()
                            .exec("logcat -d")
                        startActivity(createChooser(Intent(Intent.ACTION_SEND).apply {
                            type = "message/rfc822"
                            putExtra(Intent.EXTRA_EMAIL, arrayOf(getString(R.string.developer_email)))
                            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.hype_report_logs))
                            putExtra(Intent.EXTRA_TEXT, process.inputStream.bufferedReader()
                                .use(BufferedReader::readText))
                        }).newTask())
                    }
                } catch (e: Exception) {
                    Timber.e(e)
                    toastShort("${e.message}")
                }
            }
            .build())
    }

    override fun isMainProcess(): Boolean {
        return !ACRA.isACRASenderServiceProcess()
    }
}