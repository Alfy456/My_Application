package com.example.myapplication.viewmodelscopedemo

import androidx.lifecycle.ViewModel
import com.example.myapplication.viewmodelscopedemo.model.Users
import kotlinx.coroutines.delay

class ViewModelScopeRepository {

    suspend fun getUsers() : List<Users>{
        delay(8000)
        val users: List<Users> = listOf(
            Users(1,"Alfy"),
            Users(2,"Vinay"),
            Users(3,"Sherin"),
            Users(4,"Thommachan")
        )
        return users
    }
}