package com.zdy.application.common.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

const val WRITE_STORAGE_PERMISSION_CODE = 0x0000001

/**
 * 请求一个或多个权限时使用的方法
 *
 * @param activity
 * @param permissions 请求的权限
 * @param requestCode 权限请求码
 *
 */
@RequiresApi(Build.VERSION_CODES.M)
fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
    for (permission in permissions) {
        if (!hasPermission(activity, permission)) {
            activity.requestPermissions(arrayOf(permission), requestCode)
        }
    }
}

/**
 * 判断是否具有某个权限的方法
 *
 * @param activity
 * @param permission
 * @return 是否具有某个权限
 *
 */
fun hasPermission(activity: Activity, permission: String): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        return ActivityCompat.checkSelfPermission(
            activity,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }
    return true
}


/**
 * 判断是否具有多个权限的方法
 *
 * @param activity
 * @param permissions
 * @return 是否具有这些权限
 *
 */
fun hasPermissions(activity: Activity, permissions: Array<String>): Boolean {
    for (permission in permissions) {
        if (!hasPermission(activity, permission))
            return false
    }
    return true
}