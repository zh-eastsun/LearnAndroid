package com.zdy.application.common.base

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
interface IPresenter {
    fun attachView(view: IView)
    fun detachView()
}