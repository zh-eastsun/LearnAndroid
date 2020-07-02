package com.personal.zdy.learnandroid.util

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat

const val WRITE_STORAGE_PERMISSION_CODE = 0x0000001

@RequiresApi(Build.VERSION_CODES.M)
fun requestPermissions(activity: Activity, permissions: Array<String>, requestCode: Int) {
    for (permission in permissions) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                permission
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            activity.requestPermissions(arrayOf(permission), requestCode)
        }
    }
}