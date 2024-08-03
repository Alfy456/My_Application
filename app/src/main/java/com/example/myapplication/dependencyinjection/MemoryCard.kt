package com.example.myapplication.dependencyinjection

import android.util.Log
import javax.inject.Inject

class MemoryCard {

    init {
        Log.i("MYTAG","Memory Card Constructed")
    }

    fun getAvailableSpace(){
        Log.i("MYTAG", "Memory Space Available")
    }
}