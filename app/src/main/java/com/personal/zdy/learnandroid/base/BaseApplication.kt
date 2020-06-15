package com.personal.zdy.learnandroid.base

import android.app.Application
import android.content.Context
import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.util.LogUtil

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initLogUtil()
        LogUtil.e("BaseApplication", "attachBaseContext")
    }

    private fun initLogUtil() {
        LogUtil.init(getString(R.string.app_name), true)
    }

    override fun onCreate() {
        super.onCreate()
    }
}