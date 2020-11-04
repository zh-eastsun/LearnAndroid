package com.zdy.application.common.util

import android.content.Context

/**
 * 操作SharedPreference
 *
 * @author zhangdongyang
 * @date 2020/07/07
 */
object PreferenceUtils {

    const val SP_NAME = "LOVE_ANDROID_SP_NAME" // sp名
    const val USERNAME_KEY = "USERNAME_KEY"    // 用户名的键值
    const val PASSWORD_KEY = "PASSWORD_KEY"    // 密码的键值
    const val COOKIES_KEY = "COOKIE_KEY"       // 存储用户cookie的键值
    const val IS_LOGINED = "IS_LOGIN_KEY"      // 用户是否登陆的标记位

    fun putString(context: Context, key: String, data: String) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putString(key, data)
            .apply()
    }

    fun putBoolean(context: Context, key: String, data: Boolean) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().putBoolean(key, data)
            .apply()
    }

    fun getString(context: Context, key: String) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(key, "")
    }

    fun getBoolean(context: Context, key: String) {
        context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getBoolean(key, false)
    }

}