package com.example.myapplication.room.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SubscriberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubscriber(subscriber: Subscriber) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("Delete from subscriber_db")
    suspend fun deleteAllSubscribers() : Int

    @Query("select * from subscriber_db")
    fun getAllSubscribers(): Flow<List<Subscriber>>
}