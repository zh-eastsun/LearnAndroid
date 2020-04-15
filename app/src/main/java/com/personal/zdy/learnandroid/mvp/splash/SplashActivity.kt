package com.personal.zdy.learnandroid.mvp.splash

import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.base.BaseActivity
import com.personal.zdy.learnandroid.net.RequestApi
import kotlinx.android.synthetic.main.activity_splash.*

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {

    override fun initData() {
        Glide.with(this)
            .load(RequestApi.SPLASH_URL)
            .error(R.mipmap.ic_launcher)
            .into(image_splash)
    }

    override fun initLayout(): Int {
        return R.layout.activity_splash
    }

    override fun onBindPresenter(): SplashPresenter {
        return SplashPresenter()
    }
}