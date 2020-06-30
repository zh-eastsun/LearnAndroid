package com.personal.zdy.learnandroid.view

import android.app.Dialog
import android.content.Context
import com.personal.zdy.learnandroid.R

class LoadingDialog(context: Context) : Dialog(context, R.style.Loading) {
    init {
        setContentView(R.layout.loading_view_layout)
    }
}