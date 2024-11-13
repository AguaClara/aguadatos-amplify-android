package com.example.aguadatosapp

// Abstract parent class
abstract class Entry(
    val entryType: String
)
data class PlantFlowEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val inflowRate: Float,
    val chemicalType: String
) : Entry(entryType = "1")
data class RawWaterTurbidityEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val turbidityReadings: Double,
    val chemicalType: String
) : Entry(entryType = "2")

data class clarifiedWaterTurbidityEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val turbidityReadings: Double,
    val chemicalType: String
) : Entry(entryType = "3")

data class filteredWaterTurbidityEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val turbidityReadings: DoubleArray,
    val chemicalType: String
) : Entry(entryType = "4") {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as filteredWaterTurbidityEntry

        if (plantName != other.plantName) return false
        if (operatorName != other.operatorName) return false
        if (creationDateTime != other.creationDateTime) return false
        if (additionalNotes != other.additionalNotes) return false
        if (!turbidityReadings.contentEquals(other.turbidityReadings)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = plantName.hashCode()
        result = 31 * result + operatorName.hashCode()
        result = 31 * result + creationDateTime.hashCode()
        result = 31 * result + additionalNotes.hashCode()
        result = 31 * result + turbidityReadings.contentHashCode()
        return result
    }
}

data class CoagulantCalibrationEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val sliderPosition: Double,
    val inflowRate: Double,
    val startVolume: Double,
    val endVolume: Double,
    val timeElapsed: Int,
    val chemicalDose: Double,
    val chemicalFlowRate: Double,
    val activeTankVolume: Double,
    val chemicalType: String
) : Entry(entryType = "5")

data class CoagulantChangeDoseEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val chemicalFlowRate: Double,
    val chemicalDose: Double,
    val sliderPosition: Double,
    val targetChemicalDose: Double,
    val updatedSliderPosition: Double,
    val updatedChemicalFlowRate: Double,
    val chemicalType: String
) : Entry(entryType = "6")

data class ChlorineCalibrationEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val sliderPosition: Double,
    val inflowRate: Double,
    val startVolume: Double,
    val endVolume: Double,
    val timeElapsed: Int,
    val chemicalDose: Double,
    val chemicalFlowRate: Double,
    val activeTankVolume: Double,
    val chemicalType: String
) : Entry(entryType = "7")

data class ChlorineChangeDoseEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val chemicalFlowRate: Double,
    val chemicalDose: Double,
    val sliderPosition: Double,
    val targetChemicalDose: Double,
    val updatedSliderPosition: Double,
    val updatedChemicalFlowRate: Double,
    val chemicalType: String
) : Entry(entryType = "8")
data class FeedbackEntry(
    val plantName: String,
    val operatorName: String,
    val entryName: String,
    val creationDateTime: String,
    val additionalNotes: String,
    val operatorFeedback: String
) : Entry(entryType = "9")