package com.personal.zdy.learnandroid.mvp.login

import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.base.BaseActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * Login业务View层逻辑
 *
 * @author zhangdongyang
 * @date 2020/04/20
 */
class LoginActivity : BaseActivity<LoginPresenter>() {
    override fun onBindPresenter(): LoginPresenter {
        return LoginPresenter()
    }

    override fun initData() {

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
        }

        // 登录按钮逻辑
        btn_login.setOnClickListener {
            mPresenter.login()
        }
    }

    override fun initLayout(): Int {
        return R.layout.activity_login
    }

}
