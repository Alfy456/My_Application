package com.example.myapplication.viewmodelscopedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R

class ViewModelActivity : AppCompatActivity() {

    private lateinit var viewModel : ViewModelScopeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)

        viewModel = ViewModelProvider(this)[ViewModelScopeViewModel::class.java]

        viewModel.getUserData()
        viewModel.totalUsers.observe(this, Observer {myList ->
            myList!!.forEach {
                Log.i("RESULT",it.name)
            }

        })
    }
}