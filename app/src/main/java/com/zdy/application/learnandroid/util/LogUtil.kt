package com.zdy.application.learnandroid.util

import android.text.TextUtils
import android.util.Log

/**
 * 日志工具类
 *
 * @author zhangdongyang
 * @date 2020/04/17
 */
class LogUtil private constructor() {
    companion object {
        private var defaultTag: String = "DefaultTag --> "
        private var isDebug = false

        fun init(defaultTag: String, isDebug: Boolean) {
            this.defaultTag = defaultTag
            this.isDebug = isDebug
        }

        private fun getFinalTag(tag: String): String =
            if (!TextUtils.isEmpty(tag)) "$tag --> " else "$defaultTag --> "

        private fun getTargetStackTraceElement(): StackTraceElement? {
            var targetStackTraceElement: StackTraceElement? = null
            var shouldTrace = false
            val stackTrace = Thread.currentThread().stackTrace
            run breaking@{
                stackTrace.forEach continuing@{
                    val isLogMethod = it.className == LogUtil.javaClass.name
                    if (shouldTrace && !isLogMethod) {
                        targetStackTraceElement = it
                        return@breaking
                    }
                    shouldTrace = isLogMethod
                }
            }
            return targetStackTraceElement
        }

        fun e(TAG: String, message: String) {
            if (!isDebug) return
            val finalTag = getFinalTag(TAG)
            val stackTraceElement = getTargetStackTraceElement()
            Log.e(
                finalTag,
                "$message\nin (${stackTraceElement!!.fileName} : ${stackTraceElement.lineNumber})"
            )
        }
    }
}