package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityTwoWayBindingBinding

class TwoWayBindActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTwoWayBindingBinding
    private lateinit var viewModel: TwoWayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_two_way_binding)
        viewModel = ViewModelProvider(this)[TwoWayViewModel::class.java]
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this
    }
}