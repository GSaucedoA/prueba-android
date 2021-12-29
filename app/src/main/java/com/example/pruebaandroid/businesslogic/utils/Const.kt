package com.example.pruebaandroid.businesslogic.utils

import com.example.pruebaandroid.BuildConfig

@JvmField
val VERBOSE_NOTIFICATION_CHANNEL_NAME: CharSequence =
    "Verbose WorkManager Notifications"
const val VERBOSE_NOTIFICATION_CHANNEL_DESCRIPTION =
    "Shows notifications whenever work starts"
const val CHANNEL_ID = "VERBOSE_NOTIFICATION"
const val NOTIFICATION_ID = 1

enum class IMAGE_URL(val url: String) {
    POSTER_W500(BuildConfig.IMAGE_SERVER_URL + "w500")
}