package com.zdy.application.learnandroid.mvvm.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zdy.application.learnandroid.bean.BannerData

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/1/20
 * Time: 8:24 PM
 */

class HomeViewModel : ViewModel() {
    private val bannerData: MutableLiveData<List<BannerData>> by lazy {
        bannerData.also {
            loadBannerData()
        }
    }

    fun getBannerData(): LiveData<List<BannerData>>{
        return bannerData
    }

    private fun loadBannerData(){

    }
}