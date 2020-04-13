package com.personal.zdy.learnandroid.mvp.splash

import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.base.BaseActivity

class SplashActivity : BaseActivity<SplashPresenter>() {

    override fun initData() {
        mPresenter = SplashPresenter()

    }

    override fun initLayout(): Int {
        return R.layout.activity_splash
    }
}