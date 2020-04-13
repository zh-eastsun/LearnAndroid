package com.personal.zdy.learnandroid.base

import android.app.Application
import android.content.Context

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