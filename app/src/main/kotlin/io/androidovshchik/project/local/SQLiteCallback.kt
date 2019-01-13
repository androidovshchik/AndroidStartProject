/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

package io.androidovshchik.project.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.androidovshchik.project.extensions.sep
import java.io.File
import java.io.FileOutputStream

private const val DB_NAME = "project.db"
private const val DB_VERSION = 1

@Suppress("unused")
class SQLiteCallback(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {}

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // db.execSQL("ALTER TABLE ADD COLUMN TEXT NOT NULL DEFAULT ''")
    }

    fun copyFromAssets(context: Context) {
        val path = File("${context.applicationInfo.dataDir}${sep}databases")
        if (!path.exists()) {
            path.mkdir()
        }
        val file = context.getDatabasePath(DB_NAME)
        if (!file.exists()) {
            val input = context.assets.open(DB_NAME)
            val output = FileOutputStream(file)
            input.use {
                output.use {
                    input.copyTo(output)
                }
            }
        }
    }
}