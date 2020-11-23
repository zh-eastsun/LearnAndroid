package com.zdy.application.learnandroid.mvvm.home

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

    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun otherOperate() {

    }

    override fun bindView() {
        setContentView(binding.root)
    }

    /**
     * 清除所有选中状态
     */
    fun clearSelection() {
        binding.homePageImage.setImageResource(R.drawable.home_icon_unselected)
        binding.systemPageImage.setImageResource(R.drawable.system_icon_unselected)
        binding.squarePageImage.setImageResource(R.drawable.square_icon_unselected)
        binding.personalPageImage.setImageResource(R.drawable.personal_icon_unselected)

        binding.homePageText.setTextColor(resources.getColor(R.color.sugar_blue))
        binding.systemPageText.setTextColor(resources.getColor(R.color.sugar_blue))
        binding.squarePageText.setTextColor(resources.getColor(R.color.sugar_blue))
        binding.personalPageText.setTextColor(resources.getColor(R.color.sugar_blue))
    }

}