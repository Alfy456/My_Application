package com.example.myapplication.dependencyinjection

import dagger.Component
import javax.inject.Singleton

@Singleton

//for adding modules
@Component(modules = [MemoryCardModule::class,BatteryModule::class])
//@Component
interface SmartPhoneComponent{

    fun inject(activity: DaggerActivity)
}

