package com.example.aguadatosapp

// Abstract parent class
abstract class Entry(
    val entryType: String  // Common properties can go here
)
data class PlantFlowEntry(
    val id: String,
    val name: String,
    val inflowRate: Double,
    val notes: String,
    val time: String,
    val date: String
) : Entry(entryType = "1")
data class TurbidityEntry(
    val id: String,
    val name: String,
    val turbidity: Double,
    val notes: String,
    val time: String,
    val date: String
) : Entry(entryType = "2")
data class FilteredWaterEntry(
    val id: String,
    val name: String,
    val turbidityValues: List<Double>,
    val notes: String,
    val time: String,
    val date: String
) : Entry(entryType = "3")
data class CalibrationEntry(
    val id: String,
    val name: String,
    val sliderPosition: Double,
    val inflowRate: Double,
    val startVolume: Double,
    val endVolume: Double,
    val timeElapsed: Int,
    val chemDose: Double,
    val chemFlowRate: Double,
    val activeTankVolume: Double,
    val time: String,
    val date: String
) : Entry(entryType = "4")
data class ChangeDoseEntry(
    val id: String,
    val name: String,
    val chemFlowRate: Double,
    val chemDose: Double,
    val oldSliderPosition: Double,
    val targetChemDose: Double,
    val newSliderPosition: Double,
    val activeTankVolume: Double,
    val time: String,
    val date: String
) : Entry(entryType = "5")
data class FeedbackEntry(
    val id: String,
    val name: String,
    val feedback: String
) : Entry(entryType = "6")
