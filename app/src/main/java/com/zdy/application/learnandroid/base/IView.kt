package com.zdy.application.learnandroid.base

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/26/20
 * Time: 8:39 PM
 */
interface IView {
    fun showTipDialog(tip: String, title: String = "")
    fun showLoadingDialog()
    fun hideLoadingDialog()
    fun hideTipDialog()
}