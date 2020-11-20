package com.zdy.application.common.base.mvvm;

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zdy.application.common.R
import com.zdy.application.common.util.WRITE_STORAGE_PERMISSION_CODE
import com.zdy.application.common.view.LoadingDialog

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/20/20
 * Time: 11:36 PM
 */
abstract class BaseView : AppCompatActivity() {

    lateinit var baseDialog: AlertDialog
    lateinit var loadingDialog: LoadingDialog

    abstract fun initViewModels()
    abstract fun observeData()
    abstract fun otherOperate()
    abstract fun initLayoutRes(): Int

    open fun initWindow() {
        val localLayoutParams = window.attributes
        localLayoutParams.flags =
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
    }

    open fun beforeContentView(){}

    protected fun hideTipDialog() {
        if (baseDialog.isShowing) baseDialog.dismiss()
    }

    protected fun hideLoadingDialog() {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
    }

    protected fun showLoadingDialog() {
        loadingDialog = LoadingDialog(this)
        loadingDialog.show()
    }

    protected fun showTipDialog(tip: String, title: String = "") {
        baseDialog = AlertDialog.Builder(this)
            .setCancelable(true)
            .setTitle(title)
            .setMessage(tip)
            .setPositiveButton(
                R.string.positive_button_text
            ) { _: DialogInterface, _: Int ->
                hideTipDialog()
            }
            .create()
        baseDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        beforeContentView()
        setContentView(initLayoutRes())
        initViewModels()
        observeData()
        otherOperate()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            WRITE_STORAGE_PERMISSION_CODE ->
                if (grantResults.isNotEmpty() && grantResults.first() == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "您已授权读写文件", Toast.LENGTH_SHORT).show()
                } else {
                    showTipDialog("Warning", "存储权限是程序运行的必要权限\n请您授予..")
                }
        }
    }
}
