package com.zdy.application.learnandroid.mvp.login

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.zdy.application.learnandroid.base.mvp.BasePresenter
import com.zdy.application.learnandroid.base.Origin
import com.zdy.application.learnandroid.bean.User
import com.zdy.application.learnandroid.util.PreferenceUtils
import com.zdy.application.learnandroid.util.hasPermission
import com.zdy.application.learnandroid.ui.activity.content.ContentActivity
import com.zdy.application.learnandroid.net.api.WanApi
import com.zdy.application.learnandroid.net.interceptor.SaveCookiesInterceptor
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
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
     * 自动登陆
     *
     * @param loginSuccess  登陆成功
     * @param loginFail     登陆失败
     * @param wrongPassword 密码错误
     */
    fun autoLogin(
        startLogin: () -> Unit,
        loginSuccess: () -> Unit,
        loginFail: () -> Unit,
        wrongPassword: () -> Unit
    ) {
        val userIsLogin = PreferenceUtils.getBoolean(context, PreferenceUtils.IS_LOGINED)
        if (userIsLogin != null && userIsLogin) {
            startLogin()
            val username = PreferenceUtils.getString(context, PreferenceUtils.USERNAME_KEY)
            val password = PreferenceUtils.getString(context, PreferenceUtils.PASSWORD_KEY)
            login(username!!, password!!, loginSuccess, loginFail, wrongPassword)
        }
    }

    /**
     * 登录
     *
     * @param username      用户名
     * @param password      密码
     * @param loginSuccess  登陆成功
     * @param loginFail     登陆失败
     * @param wrongPassword 密码错误
     * @return
     */
    fun login(
        username: String,
        password: String,
        loginSuccess: () -> Unit,
        loginFail: () -> Unit,
        wrongPassword: () -> Unit
    ) {
        val client = OkHttpClient.Builder()
            .addInterceptor(SaveCookiesInterceptor(context))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val request = retrofit.create(WanApi::class.java)
        val call = request.login(username, password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                uiScope.launch {
                    // 调用失败的回调逻辑
                    loginFail()
                    PreferenceUtils.putBoolean(context, PreferenceUtils.IS_LOGINED, false)
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
                                username
                            )
                            PreferenceUtils.putString(
                                context,
                                PreferenceUtils.PASSWORD_KEY,
                                password
                            )
                            PreferenceUtils.putString(
                                context, PreferenceUtils.COOKIES_KEY,
                                user?.data!!.token
                            )
                            PreferenceUtils.putBoolean(
                                context,
                                PreferenceUtils.IS_LOGINED,
                                true
                            )
                        }
                        // 调用成功的回调逻辑
                        loginSuccess()
                        // 传递源
                        val loginBundle = Bundle()
                        loginBundle.putString(Origin.BUNDLE_KEY, Origin.LOGIN_ACTIVITY_PATH)
                        val intent = Intent(context, ContentActivity::class.java)
                        intent.putExtras(loginBundle)
                        context.startActivity(intent)
                    } else {
                        // 调用密码错误的回调逻辑
                        wrongPassword()
                        PreferenceUtils.putBoolean(context, PreferenceUtils.IS_LOGINED, false)
                    }
                }
            }
        })
    }

    fun registered() {
        // todo 注册逻辑暂未完成
    }

}