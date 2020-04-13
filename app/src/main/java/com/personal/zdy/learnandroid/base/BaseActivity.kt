package com.personal.zdy.learnandroid.base

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.personal.zdy.learnandroid.R

abstract class BaseActivity<P : BasePresenterImpl> : AppCompatActivity(), BaseView {

    protected open lateinit var mPresenter: P
    lateinit var baseDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutRes = initLayout()
        doSthBeforeSetContentView()
        setContentView(layoutRes)
        initData()
    }

    fun doSthBeforeSetContentView() {}

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