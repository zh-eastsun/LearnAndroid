package com.zdy.applicaion.common.util

import android.app.ActivityManager
import android.content.Context
import android.os.Process

/**
 * 与进程有关的工具类
 *
 * @author zhangdongyang
 * @date 2020/04/16
 */
class ProcessUtils {
    companion object {
        fun getProcessName(context: Context): String {
            val myPid = Process.myPid()
            val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val processList = manager.runningAppProcesses
            var processName = ""
            processList.forEach {
                if (it.pid == myPid) {
                    processName = it.processName
                }
            }
            return processName
        }
    }
}