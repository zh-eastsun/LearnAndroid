package com.zdy.application.learnandroid.net.interceptor

import android.content.Context
import android.text.TextUtils
import com.zdy.application.common.util.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/16/20
 * Time: 11:29 PM
 */
class SaveCookiesInterceptor(
    private val context: Context
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        // set-cookie 可能为多个
        if (response.header("set-cookie")!!.isNotEmpty()) {
            val cookies = response.headers("set-cookie")
            val cookie = encodeCookie(cookies)
            saveCookie(cookie)
        }

        return response
    }

    /**
     * 整合cookie为唯一字符串
     */
    private fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        for (cookie in cookies) {
            val arr = cookie.split(";")
            for (s in arr) {
                if (set.contains(s)) {
                    continue
                }
                set.add(s)
            }
        }

        for (cookie in set) {
            sb.append(cookie).append(";")
        }

        val lastIndex = sb.lastIndexOf(";")
        if (sb.length - 1 == lastIndex) {
            sb.deleteCharAt(lastIndex)
        }
        return sb.toString()
    }

    private fun saveCookie(cookie: String) {
        if (!TextUtils.isEmpty(cookie)) {
            PreferenceUtils.putString(context, PreferenceUtils.COOKIES_KEY, cookie)
        }
    }
}