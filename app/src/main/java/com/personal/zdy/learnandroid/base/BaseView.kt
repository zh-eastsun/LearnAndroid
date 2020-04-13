package com.personal.zdy.learnandroid.base

interface BaseView {
    fun showLoadingDialog(title: String, tip: String)
    fun showTipDialog(title: String, tip: String)
    fun hideDialog()
}