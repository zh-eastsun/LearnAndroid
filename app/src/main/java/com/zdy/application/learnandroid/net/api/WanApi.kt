package com.zdy.application.learnandroid.net.api

import com.zdy.application.learnandroid.bean.ApiResponse
import com.zdy.application.learnandroid.bean.BannerData
import com.zdy.application.learnandroid.bean.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/9/20
 * Time: 7:46 PM
 */
interface WanApi {

    @GET("banner/json")
    suspend fun bannerJson(): ApiResponse<BannerData>

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>
}