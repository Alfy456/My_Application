package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityCountBinding

class CountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCountBinding
    //Old way to cll the instance of viewModel
    private lateinit var viewModel: CountViewModel

    //New way to cll the instance of viewModel
    //private val viewModel: CountViewModel by viewModels()
    private lateinit var viewModelFactory: CountViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_count)

        viewModelFactory = CountViewModelFactory(20)
        viewModel = ViewModelProvider(this,viewModelFactory)[CountViewModel::class.java]
        binding.myViewModel = viewModel
        binding.lifecycleOwner = this


    }

}