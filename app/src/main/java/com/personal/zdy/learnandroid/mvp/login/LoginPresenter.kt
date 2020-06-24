package com.personal.zdy.learnandroid.mvp.login

import com.personal.zdy.learnandroid.base.BasePresenter
import com.personal.zdy.learnandroid.base.IPresenter
import com.personal.zdy.learnandroid.base.IView

/**
 * Login业务P层逻辑
 *
 * @author zhangdongyang
 * @date 2020/04/20
 */
class LoginPresenter : BasePresenter(){

    fun login(){

    }

    fun registered(){
        view?.hideDialog()
    }

}