package com.zdy.application.common.base.mvvm

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.zdy.application.common.R
import com.zdy.application.common.base.IView
import com.zdy.application.common.view.LoadingDialog

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/26/20
 * Time: 8:44 PM
 */
abstract class BaseFragment : Fragment(), IView {

    lateinit var baseDialog: AlertDialog
    lateinit var loadingDialog: LoadingDialog

    override fun hideTipDialog() {
        if (baseDialog.isShowing) baseDialog.dismiss()
    }

    override fun hideLoadingDialog() {
        if (loadingDialog.isShowing) loadingDialog.dismiss()
    }

    override fun showLoadingDialog() {
        loadingDialog = LoadingDialog(activity as Context)
        loadingDialog.show()
    }

    override fun showTipDialog(tip: String, title: String) {
        baseDialog = AlertDialog.Builder(activity as Context)
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