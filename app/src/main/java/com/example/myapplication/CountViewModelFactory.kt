package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CountViewModelFactory(private val startingValue :Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountViewModel::class.java)){
            return CountViewModel(startingValue) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}