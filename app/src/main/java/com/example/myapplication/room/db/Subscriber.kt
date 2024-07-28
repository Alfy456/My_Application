package com.example.myapplication.room.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscriber_db")
data class Subscriber(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subscriber_id")
    val id: Int,

    @ColumnInfo(name = "subscriber_firstname")
    var name: String,

//    @ColumnInfo(name = "subscriber_email")
//    var email: String,

    @ColumnInfo(name = "subscriber_number", defaultValue = "No Number")
    var phoneNumber: String,

    @ColumnInfo(name = "subscriber_address")
    var address: String?
)
