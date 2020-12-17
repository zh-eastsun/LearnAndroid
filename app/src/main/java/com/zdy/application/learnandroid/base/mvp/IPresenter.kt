package com.zdy.application.learnandroid.base.mvp

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
interface IPresenter {
    fun attachView(view: IView)
    fun detachView()
}