package com.example.myapplication.coroutineDemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityCoroutineBinding
import com.example.myapplication.structuredConcurrency.UserDataManager2
import com.example.myapplication.unstructuredConcurrency.UserDataManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.annotations.Async
import kotlin.math.log

class CoroutineActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCoroutineBinding
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_coroutine)

        binding.apply {
            btnCount.setOnClickListener{
                txtCount.text = count++.toString()
            }
            btnUserDataDwn.setOnClickListener {
                CoroutineScope(Main).launch {
                    //Unstructured Concurrency Demo
                    binding.txtUserData.text = UserDataManager2().getUserTotalCount().toString()

                   // downloadUserData()

//                    Log.i("myTag","Execution Started")
//                    //Parallel Decomposition
//                    val stockValue1 = async(IO) {
//                        stock1() }
//                    val stockValue2 = async(IO) {
//                        stock2() }
//                    val total = stockValue1.await() + stockValue2.await()
//                    Log.i("myTag","$total")
//                   Toast.makeText(this@CoroutineActivity,"$total",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    //long running function in a class which shows which thread is running
    private suspend fun downloadUserData(){
        for (i in 1..200000){
            withContext(Dispatchers.Main){
                binding.txtUserData.text = "User Data $i ${Thread.currentThread().name}"
            }
        }

    }

    //Example to show Parallel Decomposition
    private suspend fun stock1(): Int{
        delay(10000)

        Log.i("myTag","Stock 1")
        return 29000
    }

    private suspend fun stock2(): Int{
        delay(20000)

        Log.i("myTag","Stock 2")
        return 30000
    }
}