package com.zdy.application.learnandroid.mvp.main

import com.zdy.application.common.base.BaseActivity
import com.zdy.application.learnandroid.R

class MainActivity : BaseActivity<MainPresenter>() {
    override fun onBindPresenter(): MainPresenter =
        MainPresenter(this)

    override fun initLayout(): Int =
        R.layout.activity_main

    override fun initData() {
        // TODO("Not yet implemented")
    }

    override fun initView() {
        super.initView()
    }

}
