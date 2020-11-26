package com.zdy.application.learnandroid.mvvm.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zdy.application.common.base.mvvm.BaseFragment
import com.zdy.application.learnandroid.databinding.FragmentHomeBinding

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/24/20
 * Time: 8:25 PM
 */
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

}