package com.zdy.application.learnandroid.bean

import com.zdy.application.learnandroid.net.IBaseResponse

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/17/20
 * Time: 8:58 PM
 */
data class ApiResponse<T>(val data: T, val errorCode: Int, val errorMsg: String) :
    IBaseResponse<T> {

    override fun code(): Int = errorCode

    override fun msg(): String = errorMsg

    override fun data(): T = data

    override fun isSuccess(): Boolean = errorCode == 0

}