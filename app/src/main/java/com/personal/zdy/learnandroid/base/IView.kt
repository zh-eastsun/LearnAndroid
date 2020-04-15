package com.personal.zdy.learnandroid.base

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
interface IView {
    fun showLoadingDialog(title: String, tip: String)
    fun showTipDialog(title: String, tip: String)
    fun hideDialog()
}