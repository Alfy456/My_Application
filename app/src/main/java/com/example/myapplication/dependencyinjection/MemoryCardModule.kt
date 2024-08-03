package com.example.myapplication.dependencyinjection

import android.util.Log
import dagger.Module
import dagger.Provides


@Module
class MemoryCardModule(val memorySize: Int) {

    @Provides
    fun providesMemoryCard(): MemoryCard{
        Log.i("MYTAG", "Menorysize is $memorySize")
        return MemoryCard()
    }


}