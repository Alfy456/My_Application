package com.example.myapplication.room.db

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class SubscriberRepository(private val dao: SubscriberDAO) {

    val subscribers= dao.getAllSubscribers()


    suspend fun insertSubscriber(subscriber: Subscriber): Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun updateSubscriber(subscriber: Subscriber): Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun deleteSubscriber(subscriber: Subscriber): Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll(): Int{
        return dao.deleteAllSubscribers()
    }
}