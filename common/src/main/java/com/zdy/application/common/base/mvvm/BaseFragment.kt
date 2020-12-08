package com.zdy.application.common.base.mvvm

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.zdy.application.common.R
import com.zdy.application.common.base.IView
import com.zdy.application.common.view.LoadingDialog

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/26/20
 * Time: 8:44 PM
 */
abstract class BaseFragment<VM : ViewModel, VDB : ViewDataBinding> : Fragment(), IView {

    lateinit var baseDialog: AlertDialog
    lateinit var loadingDialog: LoadingDialog
    lateinit var binding: VDB

    abstract fun bindView(): VDB

    protected open fun initView() {}
    protected open fun observeData() {}
    protected open fun doWork() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindView()
        initView()
        observeData()
        doWork()
        return binding.root
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