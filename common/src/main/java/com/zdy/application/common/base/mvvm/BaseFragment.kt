package com.zdy.application.common.base.mvvm

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.zdy.application.common.R
import com.zdy.application.common.base.IView
import com.zdy.application.common.view.LoadingDialog
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/26/20
 * Time: 8:44 PM
 */
abstract class BaseFragment : Fragment(), IView {

    lateinit var baseDialog: AlertDialog
    lateinit var loadingDialog: LoadingDialog

    abstract fun bindView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View

    protected open fun initView() {}
    protected open fun observeData() {}
    protected open fun doWork() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initView()
        observeData()
        doWork()
        return bindView(inflater, container, savedInstanceState)
    }

    override fun hideTipDialog() {
        if (baseDialog.isShowing) baseDialog.dismiss()
    }

    override fun hideLoadingDialog() {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
    }

    override fun showLoadingDialog() {
        loadingDialog = LoadingDialog(context!!)
        loadingDialog.show()
    }

    override fun showTipDialog(tip: String, title: String) {
        baseDialog = AlertDialog.Builder(context!!)
            .setCancelable(true)
            .setTitle(title)
            .setMessage(tip)
            .setPositiveButton(
                R.string.positive_button_text
            ) { _: DialogInterface, _: Int ->
                hideTipDialog()
            }
            .create()
        baseDialog.show()
    }
}