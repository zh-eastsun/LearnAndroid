package com.personal.zdy.learnandroid.base

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.personal.zdy.learnandroid.R
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