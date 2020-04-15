package com.personal.zdy.learnandroid.base

import java.lang.ref.WeakReference

open class BasePresenter : IPresenter{

    private var viewReference: WeakReference<IView>? = null
    open var view: IView? = null

    override fun attachView(view: IView){
        viewReference = WeakReference(view)
        this.view = viewReference!!.get()
    }

    override fun detachView(){
        viewReference!!.clear()
        this.view = null
    }

}