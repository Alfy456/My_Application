package com.example.myapplication.dependencyinjection

import android.app.Application

class MyApplication : Application()  {
    lateinit var smartPhoneComponent: SmartPhoneComponent

    override fun onCreate() {
        smartPhoneComponent = initDagger()
        super.onCreate()

    }

    private fun initDagger(): SmartPhoneComponent = DaggerSmartPhoneComponent.builder()
        .memoryCardModule(MemoryCardModule(1000))
        .build()

}