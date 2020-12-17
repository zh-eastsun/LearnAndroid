package com.zdy.application.learnandroid.base

import android.app.Application
import android.content.Context
import com.zdy.application.learnandroid.util.LogUtil

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */

open class BaseApplication : Application() {

    companion object {
        lateinit var context: Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initLogUtil()
        LogUtil.e("BaseApplication", "attachBaseContext")
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    private fun initLogUtil() {
        LogUtil.init("Learn Android", true)
    }
}