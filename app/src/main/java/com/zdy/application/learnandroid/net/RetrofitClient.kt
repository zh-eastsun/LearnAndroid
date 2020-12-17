package com.zdy.application.learnandroid.net

import com.zdy.application.common.base.BaseApplication
import com.zdy.application.learnandroid.net.interceptor.SaveCookiesInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/9/20
 * Time: 7:53 PM
 */
object RetrofitClient {
    private val httpClient: OkHttpClient
        get() {
            val builder = OkHttpClient.Builder()
            builder.apply {
                addInterceptor(SaveCookiesInterceptor(BaseApplication.context))
                connectTimeout(10, TimeUnit.SECONDS)
                readTimeout(5, TimeUnit.SECONDS)
                writeTimeout(5, TimeUnit.SECONDS)
            }
            return builder.build()
        }

    fun <T> getApi(baseUrl: String, service: Class<T>): T {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
        return builder.build().create(service)
    }
}