package com.example.aguadatosapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

// SharedViewModel.kt
class SharedViewModel : ViewModel() {
    val data: MutableLiveData<DoubleArray> = MutableLiveData()
    val date: MutableLiveData<SimpleDateFormat> = MutableLiveData()
    val time: MutableLiveData<SimpleDateFormat> = MutableLiveData()
}
