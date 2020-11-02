package com.personal.zdy.learnandroid.mvp.login

import android.Manifest
import android.app.Activity
import android.content.Context
import com.zdy.application.base.BasePresenter
import com.personal.zdy.learnandroid.bean.User
import com.personal.zdy.learnandroid.net.login.LoginApi
import com.zdy.applicaion.common.util.PreferenceUtils
import com.zdy.applicaion.common.util.hasPermission
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
    fun login(username: String, password: String) {
        view?.showLoadingDialog()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val request = retrofit.create(LoginApi::class.java)
        val call = request.login(username, password)
        call.enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                uiScope.launch {
                    view?.showTipDialog("注意", "登录失败")
                    view?.hideLoadingDialog()
                }
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                uiScope.launch {
                    if (response.body()?.errorCode == 0) {
                        view?.showTipDialog("注意", "登录成功")
                        view?.hideLoadingDialog()
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
                                context,PreferenceUtils.COOKIES_KEY,
                                user.data.token
                            )
                        }
                    } else {
                        view?.showTipDialog("注意", "密码错误")
                        view?.hideLoadingDialog()
                    }
                }
            }
        })
    }

    fun registered() {
    }

}