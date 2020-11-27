package com.zdy.application.learnandroid.mvp.main

import com.zdy.application.common.base.mvp.BaseActivity
import com.zdy.application.common.base.Origin
import com.zdy.application.learnandroid.R

class MainActivity : BaseActivity<MainPresenter>() {
    override fun onBindPresenter(): MainPresenter =
        MainPresenter(this)

    override fun initLayout(): Int =
        R.layout.activity_content

    override fun initData() {
        val origin = intent.extras?.get(Origin.BUNDLE_KEY)
        if (origin == Origin.LOGIN_ACTIVITY_PATH) {
            // 用户登陆成功的逻辑
            showTipDialog(title="注意",tip = "登录成功")
        }
    }

}
