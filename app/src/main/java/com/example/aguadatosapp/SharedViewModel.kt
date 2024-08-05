package com.example.aguadatosapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale
//todo, save current state of coagulant dosing always?
// SharedViewModel.kt
class SharedViewModel : ViewModel() {
    private val defaultArray = doubleArrayOf(50.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0)
    val coagData = MutableLiveData<DoubleArray>(defaultArray)
    val tempCoagData: MutableLiveData<DoubleArray> = MutableLiveData()

    private val changeDoseDefaultArray = doubleArrayOf(-1.0, -1.0, -1.0, -1.0, -1.0)
    val changeDoseData = MutableLiveData<DoubleArray>(changeDoseDefaultArray)
    val tempChangeDoseData: MutableLiveData<DoubleArray> = MutableLiveData()
    val changeDoseFilled = MutableLiveData<Boolean>().apply { value = false }

    private val defaultVolumesArray = doubleArrayOf(0.0, 0.0)
    val tankVolumes = MutableLiveData<DoubleArray>(defaultVolumesArray)

    val accessAdjustDosage = MutableLiveData<Boolean>().apply { value = false }
    val coagulantRunOutTime: MutableLiveData<String> = MutableLiveData()

    val rawWaterData: MutableLiveData<Double> = MutableLiveData()
    val rawWaterNotes: MutableLiveData<String> = MutableLiveData()

    val plantFlowData: MutableLiveData<Double> = MutableLiveData()
    val plantFlowNotes: MutableLiveData<String> = MutableLiveData()

    val date: MutableLiveData<String> = MutableLiveData()
    val time: MutableLiveData<String> = MutableLiveData()
}