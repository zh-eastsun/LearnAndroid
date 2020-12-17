package com.zdy.application.learnandroid.mvp.login

import android.Manifest
import android.os.Build
import android.view.View
import com.zdy.application.learnandroid.base.mvp.BaseActivity
import com.zdy.application.learnandroid.util.PreferenceUtils
import com.zdy.application.learnandroid.util.WRITE_STORAGE_PERMISSION_CODE
import com.zdy.application.learnandroid.databinding.ActivityLoginBinding

/**
 * Login业务View层逻辑
 *
 * @author zhangdongyang
 * @date 2020/04/20
 */
class LoginActivity : BaseActivity<LoginPresenter>() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    private val startLoginTask = {
        showLoadingDialog()
    }

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
            com.zdy.application.learnandroid.util.requestPermissions(
                this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                WRITE_STORAGE_PERMISSION_CODE
            )
        }
    }

    override fun doWork() {
        super.doWork()
        mPresenter.autoLogin(
            startLoginTask,
            loginSuccessTask,
            loginFailTask,
            passwordWrongTask
        )
    }

    override fun initView() {
        super.initView()
        // actionbar逻辑
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        // 注册按钮逻辑
        binding.btnRegister.setOnClickListener {
            mPresenter.registered()
            // todo 注册逻辑暂未完成
        }

        // 登录按钮逻辑
        binding.btnLogin.setOnClickListener {
            showLoadingDialog()
            mPresenter.login(
                binding.inputAccount.text.toString(),
                binding.inputPassword.text.toString(),
                loginSuccessTask,
                loginFailTask,
                passwordWrongTask
            )
        }

        // 如果上次有登陆过需要填充账号和密码输入框
        val lastUsername = PreferenceUtils.getString(this, PreferenceUtils.USERNAME_KEY)
        val lastPassword = PreferenceUtils.getString(this, PreferenceUtils.PASSWORD_KEY)
        if (!lastUsername.isNullOrEmpty() && !lastPassword.isNullOrEmpty()) {
            binding.inputAccount.setText(lastUsername)
            binding.inputPassword.setText(lastPassword)
        }
    }

    override fun initLayout(): View {
        return binding.root
    }

}
