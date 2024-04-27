package com.example.myapplication.livedatascope

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.myapplication.livedatascope.model.Users
import kotlinx.coroutines.Dispatchers

class LiveDataSopeViewModel : ViewModel() {

    private val userRepository = LiveDataScopeRepository()

    var user = liveData(Dispatchers.IO) {
        val result = userRepository.getUsers()
        emit(result)
    }

}