package com.example.aguadatosapp

// Abstract parent class
abstract class Entry(
    val entryType: String
)
data class PlantFlowEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val inflowRate: Float
) : Entry(entryType = "1")
data class RawWaterTurbidityEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val turbidityReadings: Double
) : Entry(entryType = "2")

data class clarifiedWaterTurbidityEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val turbidityReadings: Double
) : Entry(entryType = "3")

data class FilteredWaterTurbidityEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val turbidityReadings: DoubleArray
) : Entry(entryType = "4") {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FilteredWaterTurbidityEntry

        if (entryTitle != other.entryTitle) return false
        if (creationDateTime != other.creationDateTime) return false
        if (additionalNotes != other.additionalNotes) return false
        if (!turbidityReadings.contentEquals(other.turbidityReadings)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = entryTitle.hashCode()
        result = 31 * result + creationDateTime.hashCode()
        result = 31 * result + additionalNotes.hashCode()
        result = 31 * result + turbidityReadings.contentHashCode()
        return result
    }
}

data class CoagChlorCalibrationEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val sliderPosition: Double,
    val inflowRate: Double,
    val startVolume: Double,
    val endVolume: Double,
    val timeElapsed: Int,
    val chemicalDose: Double,
    val chemicalType: String,
    val chemicalFlowRate: Double,
    val activeTankVolume: Double
) : Entry(entryType = "5")

data class ChangeDoseEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val chemicalFlowRate: Double,
    val chemicalDose: Double,
    val sliderPosition: Double,
    val targetChemicalDose: Double,
    val chemicalType: String,
    val updatedSliderPosition: Double,
    val updatedChemicalFlowRate: Double
) : Entry(entryType = "6")
data class FeedbackEntry(
    val entryTitle: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val operatorFeedback: String
) : Entry(entryType = "7")