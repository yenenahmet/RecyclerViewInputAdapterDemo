package com.yenen.ahmet.recyclerviewinputadapterdemo.base

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

open class BaseViewModel :ViewModel(){

    private var dataViewBinding: ViewDataBinding? = null

    protected fun setViewDataBinding(viewBinding: ViewDataBinding) {
        dataViewBinding = viewBinding
    }

    override fun onCleared() {
        super.onCleared()
        dataViewBinding?.unbind()
        dataViewBinding = null
    }
}