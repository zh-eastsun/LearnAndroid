package com.zdy.application.learnandroid.net.api

import com.zdy.application.learnandroid.net.RetrofitClient

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/9/20
 * Time: 7:55 PM
 */
object ApiService {
    fun getApi() =
        RetrofitClient.instance.getApi("https://www.wanandroid.com", WanApi::class.java)
}