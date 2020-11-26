package com.zdy.application.common.base.mvvm;

import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.zdy.application.common.R
import com.zdy.application.common.base.IView
import com.zdy.application.common.util.WRITE_STORAGE_PERMISSION_CODE
import com.zdy.application.common.view.LoadingDialog

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/20/20
 * Time: 11:36 PM
 */
abstract class BaseActivity : AppCompatActivity(), IView {

    lateinit var baseDialog: AlertDialog
    lateinit var loadingDialog: LoadingDialog

    abstract fun otherOperate()
    abstract fun bindView()

    open fun observeData() {}                    // 订阅被观察者的数据
    open fun initViewModels() {}                 // 初始化viewModel
    open fun initWindow() {
        val localLayoutParams = window.attributes
        localLayoutParams.flags =
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
    }

    override fun hideTipDialog() {
        if (baseDialog.isShowing) baseDialog.dismiss()
    }

    override fun hideLoadingDialog() {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
    }

    override fun showLoadingDialog() {
        loadingDialog = LoadingDialog(this)
        loadingDialog.show()
    }

    override fun showTipDialog(tip: String, title: String) {
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
        bindView()
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
