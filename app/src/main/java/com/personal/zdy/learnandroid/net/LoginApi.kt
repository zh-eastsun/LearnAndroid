package com.personal.zdy.learnandroid.net

import com.personal.zdy.learnandroid.bean.User
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface LoginApi {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com/"
    }

    @FormUrlEncoded
    @POST("user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>
}