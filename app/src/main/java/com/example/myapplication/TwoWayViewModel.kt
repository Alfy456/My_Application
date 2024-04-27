package com.example.myapplication

import android.hardware.camera2.CameraExtensionSession.StillCaptureLatency
import androidx.databinding.InverseMethod
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TwoWayViewModel : ViewModel() {
    val userName = MutableLiveData<String>()

    init {
        userName.value = "Alfy"
    }

}