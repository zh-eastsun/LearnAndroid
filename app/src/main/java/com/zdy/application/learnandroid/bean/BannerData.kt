package com.zdy.application.learnandroid.bean

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/1/20
 * Time: 8:14 PM
 */

data class BannerData(
    val `data`: List<BannerItemData>,
    val errorCode: Int,
    val errorMsg: String
)

data class BannerItemData(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)