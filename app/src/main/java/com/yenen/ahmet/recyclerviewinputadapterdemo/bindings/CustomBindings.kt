package com.yenen.ahmet.recyclerviewinputadapterdemo.bindings

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

class CustomBindings{
    companion object {

        @BindingAdapter("setAdapter")
        @JvmStatic
        fun bindRecyclerViewAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
            view.setHasFixedSize(true)
            view.adapter = adapter
        }

    }
}