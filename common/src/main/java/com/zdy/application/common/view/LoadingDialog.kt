package com.zdy.application.common.view

import android.app.Dialog
import android.content.Context
import com.zdy.application.common.R

class LoadingDialog(context: Context) : Dialog(context, R.style.Loading) {
    init {
        setContentView(R.layout.loading_view_layout)
    }
}