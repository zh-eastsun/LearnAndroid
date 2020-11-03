package com.zdy.application.learnandroid.mvp.login

import android.Manifest
import android.app.Activity
import android.content.Context
import com.zdy.application.common.base.BasePresenter
import com.zdy.application.learnandroid.bean.User
import com.zdy.application.learnandroid.net.login.LoginApi
import com.zdy.application.common.util.PreferenceUtils
import com.zdy.application.common.util.hasPermission
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Login业务P层逻辑
 *
 * @author zhangdongyang
 * @date 2020/04/20
 */
class LoginPresenter(val context: Context) : BasePresenter() {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    fun login(
        username: String,
        password: String,
        loginSuccess: () -> Unit,
        loginFail: () -> Unit,
        wrongPassword: () -> Unit
    ) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = retrofit.create(LoginApi::class.java)
        val call = request.login(username, password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                uiScope.launch {
                    // 调用失败的回调逻辑
                    loginFail()
                }
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                uiScope.launch {
                    if (response.body()?.errorCode == 0) {
                        // 记录用户的账号和密码
                        val user = response.body()
                        if (hasPermission(
                                view as Activity,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        ) {
                            PreferenceUtils.putString(
                                context,
                                PreferenceUtils.USERNAME_KEY,
                                user?.data!!.username
                            )
                            PreferenceUtils.putString(
                                context,
                                PreferenceUtils.PASSWORD_KEY,
                                user.data.password
                            )
                            PreferenceUtils.putString(
                                context, PreferenceUtils.COOKIES_KEY,
                                user.data.token
                            )
                        }
                        // 调用成功的回调逻辑
                        loginSuccess()
                    } else {
                        wrongPassword()
                    }
                }
            }
        })
    }

    fun registered() {
        // todo 注册逻辑暂未完成
    }

}