package com.personal.zdy.learnandroid.base

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.personal.zdy.learnandroid.R

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), IView {

    lateinit var baseDialog: AlertDialog
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

    open fun initView(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT)
        {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }
    open fun doWork(){}
    open fun doSthBeforeSetContentView() {}

    abstract fun onBindPresenter(): P

    abstract fun initData()

    abstract fun initLayout(): Int

    override fun hideDialog() {
        if (baseDialog.isShowing) baseDialog.dismiss()
    }

    override fun showLoadingDialog(title: String, tip: String) {
        baseDialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setIcon(R.drawable.ic_launcher_background)
            .setTitle(title)
            .setMessage(tip)
            .create()
        baseDialog.show()
    }

    override fun showTipDialog(title: String, tip: String) {
        baseDialog = AlertDialog.Builder(this)
            .setCancelable(true)
            .setPositiveButton(R.string.positive_button_text
            ) { _: DialogInterface, _: Int ->
                hideDialog()
            }
            .create()
        baseDialog.show()
    }
}