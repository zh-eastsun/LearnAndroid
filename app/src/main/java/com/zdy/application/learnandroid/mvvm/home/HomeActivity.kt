package com.zdy.application.learnandroid.mvvm.home

import androidx.databinding.DataBindingUtil
import com.zdy.application.common.base.mvvm.BaseView
import com.zdy.application.learnandroid.R
import com.zdy.application.learnandroid.databinding.ActivityHomeBinding

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/20/20
 * Time: 11:52 PM
 */

class HomeActivity : BaseView() {

    override fun otherOperate() {
        TODO("Not yet implemented")
    }

    override fun setContentView() {
        DataBindingUtil.setContentView<ActivityHomeBinding>(this, R.layout.activity_home)
    }

}