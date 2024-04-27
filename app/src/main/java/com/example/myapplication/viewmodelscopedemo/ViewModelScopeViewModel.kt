package com.example.myapplication.viewmodelscopedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.viewmodelscopedemo.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ViewModelScopeViewModel : ViewModel() {
    private var userRepository = ViewModelScopeRepository()
    private var users : MutableLiveData<List<Users>?> = MutableLiveData()
    val totalUsers : LiveData<List<Users>?>
        get() = users

    fun getUserData(){
        viewModelScope.launch {
            var result : List<Users>? = null
            withContext(Dispatchers.IO){
               result = userRepository.getUsers()
            }
            users.value = result
        }
    }
}