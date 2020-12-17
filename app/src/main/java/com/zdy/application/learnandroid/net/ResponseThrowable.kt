package com.zdy.application.learnandroid.net

import java.lang.Exception

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/17/20
 * Time: 8:25 PM
 */
class ResponseThrowable(val code: String, val errMsg: String, val e: Throwable? = null) : Exception(e) {
}