package com.zdy.application.common.base.mvvm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/2/20
 * Time: 7:48 PM
 */

abstract class BaseViewModel<M : BaseModel> : ViewModel() {
    private val uiJob = Job()
    private val ioJob = Job()
    protected val ioScope = CoroutineScope(Dispatchers.IO + ioJob)
    protected val uiScope = CoroutineScope(Dispatchers.Main + uiJob)
    protected val model: M by lazy { bindModel() }

    abstract fun bindModel(): M
    abstract fun initData()

    override fun onCleared() {
        super.onCleared()
        uiJob.cancel()
        ioJob.cancel()
    }
}