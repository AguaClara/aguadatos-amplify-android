package com.example.aguadatosapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

// SharedViewModel.kt
class SharedViewModel : ViewModel() {
    val coagData: MutableLiveData<DoubleArray> = MutableLiveData()
    val rawWaterData: MutableLiveData<Double> = MutableLiveData()
    val rawWaterNotes: MutableLiveData<String> = MutableLiveData()
    val plantFlowData: MutableLiveData<Double> = MutableLiveData()
    val plantFlowNotes: MutableLiveData<String> = MutableLiveData()
    val date: MutableLiveData<SimpleDateFormat> = MutableLiveData()
    val time: MutableLiveData<SimpleDateFormat> = MutableLiveData()
}
