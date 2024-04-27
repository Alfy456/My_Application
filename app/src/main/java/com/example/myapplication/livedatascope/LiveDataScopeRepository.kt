package com.example.myapplication.livedatascope

import com.example.myapplication.viewmodelscopedemo.model.Users
import kotlinx.coroutines.delay

class LiveDataScopeRepository() {

    suspend fun getUsers() : List<Users>{
        delay(5000)
        val users : List<Users> = listOf(
            Users(1,"Alfy"),
            Users(2,"Vinay"),
            Users(3,"Sherin"),
            Users(4,"Thommachan")
        )
        return users
    }
}