package com.personal.zdy.learnandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity<P: BasePresenter> : AppCompatActivity() {

    lateinit var basePresenter: P

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
}