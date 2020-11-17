package com.zdy.application.learnandroid.net.interceptor

import android.content.Context
import android.text.TextUtils
import com.zdy.application.common.util.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/17/20
 * Time: 10:23 PM
 */
class AddCookiesInterceptor(private val context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        val cookie = PreferenceUtils.getString(context, PreferenceUtils.COOKIES_KEY)
        if (TextUtils.isEmpty(cookie)) {
            builder.addHeader("Cookie", cookie!!)
        }

        return chain.proceed(request)
    }
}