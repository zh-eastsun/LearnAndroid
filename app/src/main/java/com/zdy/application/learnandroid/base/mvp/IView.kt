package com.zdy.application.learnandroid.base.mvp

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
interface IView {
    fun showLoadingDialog()
    fun showTipDialog(title: String = "", tip: String)
    fun hideTipDialog()
    fun hideLoadingDialog()
}