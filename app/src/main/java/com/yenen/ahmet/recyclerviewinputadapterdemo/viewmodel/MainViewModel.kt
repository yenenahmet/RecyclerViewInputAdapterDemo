package com.yenen.ahmet.recyclerviewinputadapterdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yenen.ahmet.recyclerviewinputadapterdemo.adapter.ItemPriceAdapter
import com.yenen.ahmet.recyclerviewinputadapterdemo.base.BaseViewModel
import com.yenen.ahmet.recyclerviewinputadapterdemo.databinding.ActivityMainBinding
import com.yenen.ahmet.recyclerviewinputadapterdemo.model.PriceModel
import com.yenen.ahmet.recyclerviewinputadapterdemo.view.MainActivity

class MainViewModel : BaseViewModel() {

    private var adapter: ItemPriceAdapter? = null
    private var items: MutableLiveData<List<PriceModel>>? = null


    fun init(binding: ActivityMainBinding, activity: MainActivity) {
        adapter = ItemPriceAdapter(mutableListOf(), activity, binding.recyclerView)
        setViewDataBinding(binding)
        binding.setLifecycleOwner(activity)
    }

    // Data Fun //
    fun getData(): LiveData<List<PriceModel>> {
        if (items == null) {
            items = MutableLiveData()
            loadData()
        }
        return items as LiveData<List<PriceModel>>
    }

    private fun loadData() {
        items?.value = getItems()
    }

    private fun getItems(): MutableList<PriceModel> {
        val list = mutableListOf<PriceModel>()
        val item1 = PriceModel("Ürün1", 10.0, 0, 0.0)
        list.add(item1)
        val item2 = PriceModel("Ürün2", 5.0, 0, 0.0)
        list.add(item2)
        val item3 = PriceModel("Ürün3", 21.0, 0, 0.0)
        list.add(item3)
        val item4 = PriceModel("Ürün4", 12.0, 0, 0.0)
        list.add(item4)
        val item5 = PriceModel("Ürün5", 7.0, 0, 0.0)
        list.add(item5)
        val item6 = PriceModel("Ürün6", 8.0, 0, 0.0)
        list.add(item6)
        val item7 = PriceModel("Ürün7", 9.0, 0, 0.0)
        list.add(item7)
        val item8 = PriceModel("Ürün8", 11.0, 0, 0.0)
        list.add(item8)
        val item9 = PriceModel("Ürün9", 17.0, 0, 0.0)
        list.add(item9)
        val item10 = PriceModel("Ürün10", 20.0, 0, 0.0)
        list.add(item10)
        return list
    }
    // Data Fun //

    // Class Fun //
    fun getAdapter(): ItemPriceAdapter? {
        return adapter
    }

    fun hideKeybord() {
        adapter?.hideKeybord()
    }

    override fun onCleared() {
        super.onCleared()
        adapter?.unBind()
        adapter = null
    }
    // Class Fun //
}