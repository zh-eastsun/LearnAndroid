package com.zdy.application.learnandroid.net

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/17/20
 * Time: 9:01 PM
 */
interface IBaseResponse<T> {
    fun code(): Int
    fun msg(): String
    fun data(): T
    fun isSuccess(): Boolean
}
