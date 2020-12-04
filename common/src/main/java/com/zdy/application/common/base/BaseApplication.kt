package com.zdy.application.common.base

import android.app.Application
import android.content.Context
import com.zdy.application.common.util.LogUtil
import dagger.hilt.android.HiltAndroidApp

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */

@HiltAndroidApp
open class BaseApplication : Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        initLogUtil()
        LogUtil.e("BaseApplication", "attachBaseContext")
    }

    private fun initLogUtil() {
        LogUtil.init("Learn Android", true)
    }
}