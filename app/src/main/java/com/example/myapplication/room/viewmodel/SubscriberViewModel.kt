package com.example.myapplication.room.viewmodel

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.room.Event
import com.example.myapplication.room.db.Subscriber
import com.example.myapplication.room.db.SubscriberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.regex.Pattern

class SubscriberViewModel(private val repository: SubscriberRepository) : ViewModel() {

    val subscribers = repository.subscribers

    var inputName = MutableLiveData<String>()
   // var inputEmail = MutableLiveData<String>()
    var inputNum = MutableLiveData<String>()
    var inputAddress = MutableLiveData<String?>()

     var saveOrUpdateButtonText = MutableLiveData<String>()
     var clearAllOrDeleteButtonText = MutableLiveData<String>()

    private var isUpdateOrDelete = false
    private lateinit var subscriberToUpdateOrDelete : Subscriber

    private var statusMessage = MutableLiveData<Event<String>>()

     val message : LiveData<Event<String>>
        get() = statusMessage

    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    fun getSavedSubscribers() = liveData {
        repository.subscribers.collect{
            emit(it)
        }
    }
    fun saveOrUpdate() {

        if (inputName.value == null) {
            statusMessage.value = Event("Please enter subscriber's name")
       // } else if (inputEmail.value == null) {
            //statusMessage.value = Event("Please enter subscriber's email")
        } else if (inputNum.value == null) {
            statusMessage.value = Event("Please enter subscriber's number")
        } else if (inputAddress.value == null) {
            statusMessage.value = Event("Please enter subscriber's address")
       // } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value).matches()) {
          //  statusMessage.value = Event("Please enter a correct subscriber's email")
        } else{
            if (isUpdateOrDelete) {
                subscriberToUpdateOrDelete.name = inputName.value!!
              //  subscriberToUpdateOrDelete.email = inputEmail.value!!
                subscriberToUpdateOrDelete.phoneNumber = inputNum.value!!
                subscriberToUpdateOrDelete.address = inputAddress.value!!
                update(subscriberToUpdateOrDelete)
            } else {
                val name = inputName.value!!
                //val email = inputEmail.value!!
                val num = inputNum.value!!
                val address = inputAddress.value!!
                insert(Subscriber(0, name,num, address))
                inputName.value = ""
              //  inputEmail.value = ""
                inputNum.value = ""
                inputAddress.value = ""
            }
        }

    }

    fun clearOrDelete(){
        if (isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)

        }else{
            deleteAll()
        }
    }

    private fun insert(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val newRowInserted = repository.insertSubscriber(subscriber)
        withContext(Dispatchers.Main){
            if (newRowInserted > -1) {
                statusMessage.value = Event("Subscriber Inserted Successfully $newRowInserted")
            }else{
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    private fun update(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val rowUpdated = repository.updateSubscriber(subscriber)
        withContext(Dispatchers.Main){
            if (rowUpdated > 0){
            subscriberToUpdateOrDelete.name = ""
          //  subscriberToUpdateOrDelete.email = ""
            subscriberToUpdateOrDelete.phoneNumber = ""
            subscriberToUpdateOrDelete.address = ""
            isUpdateOrDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveOrUpdateButtonText.value = " Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$rowUpdated Rows updated Successfully")
            }else{
                statusMessage.value = Event("Error Occurred!")
            }
        }


    }

    private fun delete(subscriber: Subscriber) = viewModelScope.launch(Dispatchers.IO) {
        val rowDeleted = repository.deleteSubscriber(subscriber)
        withContext(Dispatchers.Main){
            if (rowDeleted > 0){
            inputName.value = ""
           // inputEmail.value = ""
            inputNum.value = ""
            inputAddress.value = ""
            isUpdateOrDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveOrUpdateButtonText.value = " Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("$rowDeleted Row deleted Successfully")
        }else{
                statusMessage.value = Event("Error Occurred!")
            }
        }

    }

    private fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        val allRowsDeleted = repository.deleteAll()
        withContext(Dispatchers.Main) {
            if (allRowsDeleted > 0) {
                inputName.value = ""
               // inputEmail.value = ""
                inputNum.value = ""
                inputAddress.value = ""
                statusMessage.value = Event("$allRowsDeleted Rows deleted Successfully")
            } else {
                statusMessage.value = Event("Error Occurred!")
            }
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
       // inputEmail.value = subscriber.email
        inputNum.value = subscriber.phoneNumber
        inputAddress.value = subscriber.address
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = " Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }


}