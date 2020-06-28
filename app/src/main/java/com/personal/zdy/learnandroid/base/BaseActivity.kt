package com.personal.zdy.learnandroid.base

import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.personal.zdy.learnandroid.R
import kotlinx.coroutines.*

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
abstract class BaseActivity<P : IPresenter> : AppCompatActivity(), IView ,CoroutineScope by MainScope(){

    lateinit var baseDialog: AlertDialog
    lateinit var mPresenter: P
    // 主线程协程
    val uiJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + uiJob)
    // IO线程协程
    val ioJob = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + ioJob)

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
        // 取消IO协程，释放资源
        ioJob.cancel()
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
            .setPositiveButton(
                R.string.positive_button_text
            ) { _: DialogInterface, _: Int ->
                hideDialog()
            }
            .create()
        baseDialog.show()
    }
}