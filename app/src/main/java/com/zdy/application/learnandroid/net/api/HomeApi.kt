package com.zdy.application.learnandroid.net.api

import com.zdy.application.learnandroid.bean.BannerData
import com.zdy.application.learnandroid.bean.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/3/20
 * Time: 8:55 PM
 */
interface HomeApi {
    @FormUrlEncoded
    @GET("banner/json")
    fun bannerJson(): Call<BannerData>
}