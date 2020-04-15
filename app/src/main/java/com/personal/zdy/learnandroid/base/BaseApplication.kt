package com.personal.zdy.learnandroid.base

import android.app.Application
import android.content.Context

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
class BaseApplication : Application() {
    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}