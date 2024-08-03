package com.example.myapplication.dependencyinjection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.MainActivity
import com.example.myapplication.R
import javax.inject.Inject

class DaggerActivity : AppCompatActivity() {

    //field injection
    @Inject
    lateinit var smartPhone: SmartPhone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger)

        (application as MyApplication).smartPhoneComponent.inject(this)

        //Dagger2 Framework
        //regular
//        DaggerSmartPhoneComponent.create()
//            .inject(this)

        //if we pass a state through a module
//        DaggerSmartPhoneComponent.builder()
//            .memoryCardModule(MemoryCardModule(1000))
//            .build()
//            .inject(this)

        //field injection
       smartPhone.makeACall()




//        //Constructor Injection
//        val serviceProvider =  ServiceProvider()
//        smartPhone = SmartPhone(Battery(),SIMCard(serviceProvider), MemoryCard())
//        smartPhone.makeACall()


    }
}