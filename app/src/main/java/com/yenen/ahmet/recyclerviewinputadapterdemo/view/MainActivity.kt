package com.yenen.ahmet.recyclerviewinputadapterdemo.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.yenen.ahmet.recyclerviewinputadapterdemo.R
import com.yenen.ahmet.recyclerviewinputadapterdemo.databinding.ActivityMainBinding
import com.yenen.ahmet.recyclerviewinputadapterdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.init(binding, this)
        binding.viewModel = viewModel
        observeData()
    }

    private fun observeData() {
        viewModel.getData().observe(this, Observer { t ->
            if (t != null) {
                viewModel.getAdapter()?.setItems(t)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.hideKeybord()
    }


}
