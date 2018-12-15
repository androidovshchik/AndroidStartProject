/*
 * Copyright (c) 2018. Vlad Kalyuzhnyu <vladkalyuzhnyu@gmail.com>
 */

@file:Suppress("unused")

package io.androidovshchik.project

import java.text.SimpleDateFormat
import java.util.*

const val NOISY_CHANNEL_ID = "app_noisy_channel"
const val QUITE_CHANNEL_ID = "app_quite_channel"

const val NUMBERS = "0123456789"
const val UPPER_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
const val LOWER_LETTERS = "abcdefghijklmnopqrstuvwxyz"
const val LETTERS = UPPER_LETTERS + LOWER_LETTERS
const val CHARS = NUMBERS + LETTERS

const val DB_NAME = "project.db"
const val DB_VERSION = 1

val DATETIME = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)