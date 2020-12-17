package com.zdy.application.learnandroid.net

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
open class RetrofitClient {

    companion object {
        val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }
    }

    private val httpClient: OkHttpClient
        get() {
            var builder = OkHttpClient.Builder()
            builder = setHttpClient(builder)
            return builder.build()
        }

    open fun setHttpClient(builder: OkHttpClient.Builder): OkHttpClient.Builder {
        builder.apply {
            connectTimeout(10, TimeUnit.SECONDS)
            readTimeout(5, TimeUnit.SECONDS)
            writeTimeout(5, TimeUnit.SECONDS)
        }
        return builder
    }

    fun <T> getApi(baseUrl: String, service: Class<T>): T {
        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClient)
        return setRetrofit(builder).build().create(service)
    }

    open fun setRetrofit(builder: Retrofit.Builder): Retrofit.Builder {
        builder.addConverterFactory(GsonConverterFactory.create())
        return builder
    }

}