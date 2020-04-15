package com.personal.zdy.learnandroid.base

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
interface IPresenter {
    fun attachView(view: IView)
    fun detachView()
}