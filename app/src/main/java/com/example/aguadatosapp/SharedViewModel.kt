package com.example.aguadatosapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// SharedViewModel.kt
class SharedViewModel : ViewModel() {

    // this is the data for the calibration coagulant dosing data submission
    private val defaultArray = doubleArrayOf(50.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0)
    val coagCalibrationData = MutableLiveData(defaultArray)
    // track if endVolume has been updated, if so, try to calculate run out time
    val triggerCoagRunOutTimeCalculation: MutableLiveData<Boolean> = MutableLiveData()

    // this is the data for the change dose coagulant dosing data submission
    private val changeDoseDefaultArray = doubleArrayOf(-1.0, -1.0, -1.0, -1.0, -1.0)
    val coagChangeDoseData = MutableLiveData(changeDoseDefaultArray)
    val coagChangeDoseFilled = MutableLiveData<Boolean>().apply { value = false }

    // this is the data for the coagulant dosing tank volumes
    private val defaultVolumesArray = doubleArrayOf(0.0, 0.0)
    val coagTankVolumes = MutableLiveData(defaultVolumesArray)

    // this variable tracks whether or not the coagulant dosing calibration form is filled (and the change dose form is accessible)
    val coagAccessAdjustDosage = MutableLiveData<Boolean>().apply { value = false }
    // this variable holds the time in seconds before the coagulant in the active tank runs out
    val coagulantRunOutTime: MutableLiveData<String> = MutableLiveData()

    // this is the data for the calibration chlorine dosing data submission
    val chlorineCalibrationData = MutableLiveData(defaultArray)
    // track if endVolume has been updated, if so, try to calculate run out time
    val triggerChlorineRunOutTimeCalculation: MutableLiveData<Boolean> = MutableLiveData()

    // this is the data for the change dose chlorine dosing data submission
    val chlorineChangeDoseData = MutableLiveData(changeDoseDefaultArray)
    val chlorineChangeDoseFilled = MutableLiveData<Boolean>().apply { value = false }

    // this is the data for the chlorine dosing tank volumes
    val chlorineTankVolumes = MutableLiveData(defaultVolumesArray)

    // this variable tracks whether or not the chlorine dosing calibration form is filled (and the change dose form is accessible)
    val chlorineAccessAdjustDosage = MutableLiveData<Boolean>().apply { value = false }
    // this variable holds the time in seconds before the chlorine in the active tank runs out
    val chlorineRunOutTime: MutableLiveData<String> = MutableLiveData()

    // this is the data for the plant flow data submission
    val plantFlowData: MutableLiveData<Double> = MutableLiveData()
    val plantFlowNotes: MutableLiveData<String> = MutableLiveData()

    // this is the data for the raw water data submission
    val rawWaterData: MutableLiveData<Double> = MutableLiveData()
    val rawWaterNotes: MutableLiveData<String> = MutableLiveData()

    // this is the data for the filtered water data submission
    private val filteredWaterDefaultArray = doubleArrayOf(-1.0, -1.0, -1.0, -1.0, -1.0, -1.0)
    val filteredWaterData: MutableLiveData<DoubleArray> = MutableLiveData(filteredWaterDefaultArray)
    val filteredWaterNotes: MutableLiveData<String> = MutableLiveData()

    // this is the data for the clarified water turbidity
    val clarifiedWaterData: MutableLiveData<Double> = MutableLiveData()
    val clarifiedWaterNotes: MutableLiveData<String> = MutableLiveData()

    // this holds submission time and date
    val date: MutableLiveData<String> = MutableLiveData()
    val time: MutableLiveData<String> = MutableLiveData()

    // these are variables storing configuration settings
    val plantOperatorName: MutableLiveData<String> = MutableLiveData("")
    val plantName: MutableLiveData<String> = MutableLiveData("")
    val chemType: MutableLiveData<String> = MutableLiveData("PACl")
    val chemConcentration: MutableLiveData<Double> = MutableLiveData(1.6)
    val numFilters: MutableLiveData<Int> = MutableLiveData(1)

}