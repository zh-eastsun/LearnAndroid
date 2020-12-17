package com.zdy.application.learnandroid.mvvm.home

import androidx.lifecycle.MutableLiveData
import com.zdy.application.learnandroid.base.mvvm.BaseViewModel
import com.zdy.application.learnandroid.bean.BannerData

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/1/20
 * Time: 8:24 PM
 */

class HomeViewModel : BaseViewModel() {

    val bannerData = MutableLiveData<BannerData>()

    fun getBannerData() {

    }
}