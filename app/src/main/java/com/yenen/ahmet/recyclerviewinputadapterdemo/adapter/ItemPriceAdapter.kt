package com.yenen.ahmet.recyclerviewinputadapterdemo.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.recyclerview.widget.RecyclerView
import com.yenen.ahmet.recyclerviewinputadapterdemo.RecyclerViewHelper.RecyclerViewInputAdapter
import com.yenen.ahmet.recyclerviewinputadapterdemo.databinding.PriceitemLayoutBinding
import com.yenen.ahmet.recyclerviewinputadapterdemo.model.PriceModel

class ItemPriceAdapter
constructor(items: MutableList<PriceModel>, activity: Activity, viewToken: RecyclerView) :
    RecyclerViewInputAdapter<PriceModel, ItemPriceAdapter.ViewHolder>(items, activity, viewToken) {

    override fun onChange(position: Int, view: View, item: PriceModel) {
        val inputView:AppCompatEditText = view as AppCompatEditText
        val text:String = inputView.text.toString().trim()
        val quantity:Int = if (text.isEmpty()) 0 else text.toInt()
        item.itemTotalPrice = item.itemPrice * quantity
        item.itemQuantity = quantity
        setChanged(position, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PriceitemLayoutBinding.inflate(getInflater(parent), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            inputConfig(holder.binding.Input, position)
        }
    }

    private fun inputConfig(input: AppCompatEditText, position: Int) {
        input.setTag(position)
        input.setOnFocusChangeListener(focusChangeListener)
        input.addTextChangedListener(textWatcher)
    }

    inner class ViewHolder(val binding: PriceitemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PriceModel) {
            binding.viewadapter = item
            binding.executePendingBindings()
        }
    }

}