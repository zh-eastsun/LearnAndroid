package com.zdy.application.learnandroid.base.mvp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import java.lang.ref.WeakReference

open class BasePresenter() : IPresenter {

    // 主线程协程
    private val uiJob = Job()
    val uiScope = CoroutineScope(Dispatchers.Main + uiJob)

    // IO线程协程
    private val ioJob = Job()
    val ioScope = CoroutineScope(Dispatchers.IO + ioJob)

    private var viewReference: WeakReference<IView>? = null
    open var view: IView? = null

    override fun attachView(view: IView) {
        viewReference = WeakReference(view)
        this.view = viewReference!!.get()
    }

    override fun detachView() {
        // 取消IO协程，释放资源
        ioJob.cancel()
        viewReference!!.clear()
        this.view = null
    }

}