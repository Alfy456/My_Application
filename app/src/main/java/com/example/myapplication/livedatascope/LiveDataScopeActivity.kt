package com.example.myapplication.livedatascope

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class LiveDataScopeActivity : AppCompatActivity() {

    private lateinit var viewModel : LiveDataSopeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        viewModel = ViewModelProvider(this)[LiveDataSopeViewModel::class.java]


        viewModel.user.observe(this, Observer {myList ->
            myList!!.forEach {
                Log.i("RESULTLiveData",it.name)
            }

        })
    }
}