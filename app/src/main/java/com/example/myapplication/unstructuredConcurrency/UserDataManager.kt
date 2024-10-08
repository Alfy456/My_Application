package com.example.myapplication.unstructuredConcurrency

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager() {


    suspend fun getUserTotalCount(): Int{
        var count = 0
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
            count = 50
        }

        val deferred = CoroutineScope(Dispatchers.IO).async {
            delay(2000)
            return@async 70

        }

        return count + deferred.await()
    }
}