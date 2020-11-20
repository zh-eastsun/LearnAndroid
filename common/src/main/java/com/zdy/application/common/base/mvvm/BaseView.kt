package com.zdy.application.common.base.mvvm;

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/20/20
 * Time: 11:36 PM
 */
abstract class BaseView : AppCompatActivity() {

    abstract fun initViewModels()
    abstract fun observeData()
    abstract fun otherOperate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModels()
        observeData()
        otherOperate()
    }
}
