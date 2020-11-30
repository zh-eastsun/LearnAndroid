package com.zdy.application.learnandroid.mvp.login

import android.Manifest
import android.os.Build
import com.zdy.application.learnandroid.R
import com.zdy.application.common.base.mvp.BaseActivity
import com.zdy.application.common.util.WRITE_STORAGE_PERMISSION_CODE
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Login业务View层逻辑
 *
 * @author zhangdongyang
 * @date 2020/04/20
 */
class LoginActivity : BaseActivity<LoginPresenter>() {

    private val loginSuccessTask = {
        // 登陆成功的函数回调
        hideLoadingDialog()
        finish()
    }

    private val loginFailTask = {
        // 登陆失败的函数回调
        showTipDialog("注意", "登录失败")
        hideLoadingDialog()
    }

    private val passwordWrongTask = {
        // 密码错误的函数回调
        showTipDialog("注意", "密码错误")
        hideLoadingDialog()
    }

    override fun onBindPresenter(): LoginPresenter {
        return LoginPresenter(this)
    }

    override fun initData() {
        if (Build.VERSION.SDK_INT >= 23) {
            com.zdy.application.common.util.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_STORAGE_PERMISSION_CODE
            )
        }

        showLoadingDialog()
        mPresenter.autoLogin(loginSuccessTask, loginFailTask, passwordWrongTask)
    }

    override fun initView() {
        super.initView()
        // actionbar逻辑
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        // 注册按钮逻辑
        btn_register.setOnClickListener {
            mPresenter.registered()
            // todo 注册逻辑暂未完成
        }

        // 登录按钮逻辑
        btn_login.setOnClickListener {
            showLoadingDialog()
            mPresenter.login(
                input_account.text.toString(),
                input_password.text.toString(),
                loginSuccessTask,
                loginFailTask,
                passwordWrongTask
            )
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_login
    }

}
