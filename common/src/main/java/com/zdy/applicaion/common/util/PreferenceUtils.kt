package com.zdy.applicaion.common.util

import android.content.Context

/**
 * 操作SharedPreference
 *
 * @author zhangdongyang
 * @date 2020/07/07
 */
object PreferenceUtils {

    const val SP_NAME = "PRIVATE_DATA"
    const val USERNAME_KEY = "USERNAME_KEY"
    const val PASSWORD_KEY = "PASSWORD_KEY"
    const val COOKIES_KEY = "COOKIE_KEY"

    fun putString(context: Context, key: String, data: String) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(key, data)
            .commit()
    }

    fun getString(context: Context, key: String) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, "")
    }
}