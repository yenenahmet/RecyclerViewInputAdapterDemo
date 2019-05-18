package com.yenen.ahmet.recyclerviewinputadapterdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.yenen.ahmet.recyclerviewinputadapterdemo.adapter.ItemPriceAdapter
import com.yenen.ahmet.recyclerviewinputadapterdemo.model.PriceModel

class MainActivity : AppCompatActivity() {
    private var adapter :ItemPriceAdapter? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        adapter = ItemPriceAdapter(getItems(),this,recyclerView)
        recyclerView.adapter =adapter

    }

    override fun onResume() {
        super.onResume()
        adapter?.hideKeybord()
    }

    private fun getItems(): MutableList<PriceModel> {
        val list = mutableListOf<PriceModel>()
        val item1 = PriceModel("Ürün1", 10.0, 0, 0.0)
        list.add(item1)
        val item2 = PriceModel("Ürün2",5.0,0,0.0)
        list.add(item2)
        val item3 = PriceModel("Ürün3",21.0,0,0.0)
        list.add(item3)
        val item4 =PriceModel("Ürün4",12.0,0,0.0)
        list.add(item4)
        val item5 =PriceModel("Ürün5",7.0,0,0.0)
        list.add(item5)
        val item6 =PriceModel("Ürün6",8.0,0,0.0)
        list.add(item6)
        val item7 =PriceModel("Ürün7",9.0,0,0.0)
        list.add(item7)
        val item8 =PriceModel("Ürün8",11.0,0,0.0)
        list.add(item8)
        val item9 =PriceModel("Ürün9",17.0,0,0.0)
        list.add(item9)
        val item10 =PriceModel("Ürün10",20.0,0,0.0)
        list.add(item10)
        return list
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter?.unBind()
        adapter =null
    }
}
