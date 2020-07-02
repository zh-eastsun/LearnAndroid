package com.personal.zdy.learnandroid.base

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.util.WRITE_STORAGE_PERMISSION_CODE
import com.personal.zdy.learnandroid.view.LoadingDialog

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), IView {

    lateinit var baseDialog: AlertDialog
    lateinit var loadingDialog: LoadingDialog
    lateinit var mPresenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutRes = initLayout()
        doSthBeforeSetContentView()
        setContentView(layoutRes)
        initView()
        mPresenter = onBindPresenter()
        mPresenter.attachView(this)
        initData()
        doWork()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
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
                    Toast.makeText(this, "该权限为应用必要权限，为了保证您的正常使用，请授予该权限", Toast.LENGTH_SHORT).show()
                    requestPermissions(
                        arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        WRITE_STORAGE_PERMISSION_CODE
                    )
                }
        }
    }

    open fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            val localLayoutParams = window.attributes
            localLayoutParams.flags =
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or localLayoutParams.flags
        }
    }

    open fun doWork() {}
    open fun doSthBeforeSetContentView() {}

    abstract fun onBindPresenter(): P

    abstract fun initData()

    abstract fun initLayout(): Int

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

    override fun showTipDialog(title: String, tip: String) {
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
}