package com.example.myapplication.dependencyinjection

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SmartPhone @Inject constructor(val battery: Battery,val simCard: SIMCard,val memoryCard: MemoryCard) {

    init {
        battery.getPower()
        simCard.getConnection()
        memoryCard.getAvailableSpace()
        Log.i("MYTAG", "SmartPhone Constructed")
    }

    fun makeACall(){
        Log.i("MYTAG", "Calling....")
    }
}