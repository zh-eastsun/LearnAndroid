package com.zdy.application.learnandroid.mvvm.home

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.zdy.application.learnandroid.base.mvvm.BaseFragment
import com.zdy.application.learnandroid.databinding.FragmentHomeBinding

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/24/20
 * Time: 8:25 PM
 */

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override fun initView() {

    }

    override fun observeData() {
        viewModel.bannerData.observe(this,
            { Log.e("ddd", it.data[0].url) })
    }

    override fun doWork() {
        super.doWork()
        viewModel.getBannerData()
    }

    override fun bindView(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun bindViewModel(): HomeViewModel {
        return ViewModelProvider.AndroidViewModelFactory.getInstance(activity!!.application)
            .create(HomeViewModel::class.java)
    }

}