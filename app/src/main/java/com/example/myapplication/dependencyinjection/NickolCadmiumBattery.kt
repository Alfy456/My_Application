package com.example.myapplication.dependencyinjection

import android.util.Log
import javax.inject.Inject

class NickolCadmiumBattery @Inject constructor() : Battery {

    override fun getPower() {
        Log.i("MYTAG","Nickel Power Activated")
    }

}