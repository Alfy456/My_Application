package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.LayoutDataBindingBinding

class DataBindingClass : AppCompatActivity() {
    private lateinit var binding: LayoutDataBindingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.layout_data_binding)
        binding.student = getStudent()
        binding.btnStartStop.setOnClickListener{
            displayProgressbar()

        }
    }

    private fun getStudent() : Student{
        return Student(0,"Alfy","alfy524@gmail.com")
    }
    private fun displayProgressbar(){
        binding.apply {
            if (progressCircular.visibility == View.GONE){
                progressCircular.visibility = View.VISIBLE
                btnStartStop.text = getString(R.string.stop)
            }else{
                progressCircular.visibility = View.GONE
                btnStartStop.text = getString(R.string.start)
            }
        }
    }
}