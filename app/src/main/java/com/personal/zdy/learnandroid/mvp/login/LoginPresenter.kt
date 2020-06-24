package com.personal.zdy.learnandroid.mvp.login

import com.personal.zdy.learnandroid.base.IPresenter
import com.personal.zdy.learnandroid.base.IView

/**
 * Login业务P层逻辑
 *
 * @author zhangdongyang
 * @date 2020/04/20
 */
class LoginPresenter : IPresenter{
    var currentView: IView? = null

    override fun attachView(view: IView) {
        this.currentView = view
    }

    override fun detachView() {
        this.currentView = null
    }

}