package com.zdy.application.common.base.mvvm

import androidx.lifecycle.ViewModel

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/2/20
 * Time: 7:48 PM
 */

abstract class BaseViewModel<T : BaseModel> : ViewModel() {

    private var model: T

    abstract fun bindModel(): T

    init {
        model = bindModel()
    }
}