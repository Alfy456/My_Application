package com.example.myapplication.structuredConcurrency

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager2() {

    var count = 0
    lateinit var deferred : Deferred<Int>
    suspend fun getUserTotalCount(): Int{

        coroutineScope {
            launch(Dispatchers.IO){
                delay(2000)
                count = 50
            }
        }
        deferred = coroutineScope {
            async(Dispatchers.IO){
                delay(2000)
                return@async 70
            }
        }
        return count + deferred.await()
    }
}