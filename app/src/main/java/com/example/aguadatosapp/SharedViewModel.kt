package com.example.aguadatosapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// SharedViewModel.kt
class SharedViewModel : ViewModel() {

    // this is the data for the calibration coagulant dosing data submission
    // array includes: slider position, inflow rate, start volume, end volume, time elapsed, chem dose, chem flow rate
  
    private val defaultArray1 = doubleArrayOf(50.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0)
    val coagCalibrationData = MutableLiveData(defaultArray1)
    // track if endVolume has been updated, if so, try to calculate run out time
    val triggerCoagRunOutTimeCalculation: MutableLiveData<Boolean> = MutableLiveData()

    // this is the data for the change dose coagulant dosing data submission
    private val changeDoseDefaultArray1 = doubleArrayOf(-1.0, -1.0, -1.0, -1.0, -1.0)
    val coagChangeDoseData = MutableLiveData(changeDoseDefaultArray1)
    val coagChangeDoseFilled = MutableLiveData<Boolean>().apply { value = false }

    // this is the data for the coagulant dosing tank volumes
    private val defaultVolumesArray1 = doubleArrayOf(0.0, 0.0)
    val coagTankVolumes = MutableLiveData(defaultVolumesArray1)

    // this variable tracks whether or not the coagulant dosing calibration form is filled (and the change dose form is accessible)
    val coagAccessAdjustDosage = MutableLiveData<Boolean>().apply { value = false }
    // this variable holds the time in seconds before the coagulant in the active tank runs out
    val coagulantRunOutTime: MutableLiveData<String> = MutableLiveData()

    // this is the data for the calibration chlorine dosing data submission
    val defaultArray2 = doubleArrayOf(50.0, -1.0, -1.0, -1.0, -1.0, -1.0, -1.0)
    val chlorineCalibrationData = MutableLiveData(defaultArray2)
    // track if endVolume has been updated, if so, try to calculate run out time
    val triggerChlorineRunOutTimeCalculation: MutableLiveData<Boolean> = MutableLiveData()

    // this is the data for the change dose chlorine dosing data submission
    private val changeDoseDefaultArray2 = doubleArrayOf(-1.0, -1.0, -1.0, -1.0, -1.0)
    val chlorineChangeDoseData = MutableLiveData(changeDoseDefaultArray2)
    val chlorineChangeDoseFilled = MutableLiveData<Boolean>().apply { value = false }

    // this is the data for the chlorine dosing tank volumes
    private val defaultVolumesArray2 = doubleArrayOf(0.0, 0.0)
    val chlorineTankVolumes = MutableLiveData(defaultVolumesArray2)

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

    // this stores plant operator feedback submissions
    val feedback: MutableLiveData<String> = MutableLiveData("")
}

//TODO: figure out why notes input is all on one line- make this vertically scrollable too?
// Why is filtered confirm entry view getting all messed up?