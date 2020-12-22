package com.zdy.application.learnandroid.base.mvvm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kunminx.architecture.ui.callback.UnPeekLiveData
import com.zdy.application.learnandroid.net.ExceptionHandle
import com.zdy.application.learnandroid.net.IBaseResponse
import com.zdy.application.learnandroid.net.ResponseThrowable
import kotlinx.coroutines.*

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 12/2/20
 * Time: 7:48 PM
 */

abstract class BaseViewModel : ViewModel() {

    val uiEvent: UIChange by lazy { UIChange() }

    // 需要在UI线程中做的操作
    fun launchUI(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch { block() }

    /**
     * 不过滤请求结果
     * @param block          协程执行体
     * @param error          失败回调
     * @param complete       完成回调（无论成功失败都会调用）
     * @param isShowLoading  是否显示加载
     */
    fun launchGo(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit = {
            uiEvent.toastEvent.postValue("${it.code}: ${it.errMsg}")
        },
        complete: suspend CoroutineScope.() -> Unit = {},
        isShowLoading: Boolean = true
    ) {
        if (isShowLoading) uiEvent.showLoading.postValue(null)
        launchUI {
            handleException(
                withContext(Dispatchers.IO) { block },
                { error(it) },
                {
                    uiEvent.hideLoading.postValue(null)
                    complete()
                }
            )
        }
    }

    /**
     * 过滤请求结果，其他全抛异常
     * @param block          协程执行体
     * @param success        成功回调
     * @param error          失败回调
     * @param complete       完成回调
     * @param isShowLoading  是否显示加载
     */
    fun <T> launchOnlyResult(
        block: suspend CoroutineScope.() -> IBaseResponse<T>,
        success: (T) -> Unit,
        error: (ResponseThrowable) -> Unit = {
            uiEvent.toastEvent.postValue("${it.code}: ${it.errMsg}")
        },
        complete: () -> Unit = {},
        isShowLoading: Boolean = true
    ) {
        if (isShowLoading) uiEvent.showLoading.postValue(null)
        launchUI {
            handleException(
                {
                    withContext(Dispatchers.IO) {
                        block().let {
                            if (it.isSuccess()) {
                                Log.d("HttpLogInfo", "${it.code()}: ${it.msg()}  ${it.data()}")
                                it.data()
                            } else {
                                throw ResponseThrowable(it.code().toString(), it.msg())
                            }
                        }
                    }.also { success(it) }
                },
                {
                    error(it)
                },
                {
                    uiEvent.hideLoading.postValue(null)
                    complete()
                }
            )
        }
    }

    /**
     * 统一异常处理
     * @param block     协程请求体
     * @param error     请求失败回调
     * @param complete  请求完成回调
     */
    private suspend fun handleException(
        block: suspend CoroutineScope.() -> Unit,
        error: suspend CoroutineScope.(ResponseThrowable) -> Unit,
        complete: suspend CoroutineScope.() -> Unit
    ) {
        coroutineScope {
            try {
                block()
            } catch (e: Throwable) {
                error(ExceptionHandle.handleException(e))
            } finally {
                complete()
            }
        }
    }

    inner class UIChange {
        val showLoading by lazy {
            UnPeekLiveData.Builder<String>().setAllowNullValue(true).create()
        }
        val hideLoading by lazy {
            UnPeekLiveData.Builder<String>().setAllowNullValue(true).create()
        }
        val toastEvent by lazy {
            UnPeekLiveData.Builder<String>().setAllowNullValue(true).create()
        }
    }

}