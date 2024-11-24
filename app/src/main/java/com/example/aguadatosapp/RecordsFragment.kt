package com.example.aguadatosapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider

// RecordsFragment.kt
class RecordsFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // TODO: n right now is number of entries, needs to update dynamically based on input from backend
    private val n = 9
    private lateinit var dummyCoagulantCalibrationEntry: CoagulantCalibrationEntry
    private lateinit var dummyCoagulantChangeDoseEntry: CoagulantChangeDoseEntry
    private lateinit var dummyChlorineCalibrationEntry: ChlorineCalibrationEntry
    private lateinit var dummyChlorineChangeDoseEntry: ChlorineChangeDoseEntry
    private lateinit var dummyFeedbackEntry: FeedbackEntry
    private lateinit var dummyPlantFlowEntry: PlantFlowEntry
    private lateinit var dummyRawWaterEntry: RawWaterTurbidityEntry
    private lateinit var dummyclarifiedWaterTurbidityEntry: clarifiedWaterTurbidityEntry
    private lateinit var dummyfilteredWaterTurbidityEntry: filteredWaterTurbidityEntry
    private var isPopupOpen = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_records, container, false)

        //initialize view model and entry container
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val container = view.findViewById<LinearLayout>(R.id.entriesContainer)

        createDummyData()
        addEntry(container,dummyCoagulantCalibrationEntry)
        addEntry(container,dummyCoagulantChangeDoseEntry)
        addEntry(container,dummyChlorineCalibrationEntry)
        addEntry(container,dummyChlorineChangeDoseEntry)
        addEntry(container,dummyclarifiedWaterTurbidityEntry)
        addEntry(container,dummyRawWaterEntry)
        addEntry(container,dummyfilteredWaterTurbidityEntry)
        addEntry(container,dummyPlantFlowEntry)
        addEntry(container,dummyFeedbackEntry)

        return view
    }
    private fun createDummyData() {
        dummyCoagulantCalibrationEntry = CoagulantCalibrationEntry(
            entryName = "Coagulant Dosage Calibration",
            plantName = "Plant",
            operatorName = "Operator",
            sliderPosition = 50.0,
            inflowRate = 5.0,
            startVolume = 3.5,
            endVolume = 2.5,
            timeElapsed = 37,
            chemicalDose = 2.0,
            chemicalFlowRate = 0.5,
            activeTankVolume = 20.0,
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyCoagulantChangeDoseEntry = CoagulantChangeDoseEntry(
            entryName = "Coagulant Change Dosage",
            plantName = "Plant",
            operatorName = "Operator",
            sliderPosition = 50.0,
            updatedSliderPosition = 37.0,
            chemicalDose = 3.5,
            targetChemicalDose = 2.5,
            chemicalFlowRate = 3.5,
            updatedChemicalFlowRate = 3.0,
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyChlorineCalibrationEntry = ChlorineCalibrationEntry(
            entryName = "Chlorine Dosage Calibration",
            plantName = "Plant",
            operatorName = "Operator",
            sliderPosition = 50.0,
            inflowRate = 5.0,
            startVolume = 3.5,
            endVolume = 2.5,
            timeElapsed = 37,
            chemicalDose = 2.0,
            chemicalFlowRate = 0.5,
            activeTankVolume = 20.0,
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyChlorineChangeDoseEntry = ChlorineChangeDoseEntry(
            entryName = "Chlorine Change Dosage",
            plantName = "Plant",
            operatorName = "Operator",
            sliderPosition = 50.0,
            updatedSliderPosition = 37.0,
            chemicalDose = 3.5,
            targetChemicalDose = 2.5,
            chemicalFlowRate = 3.5,
            updatedChemicalFlowRate = 3.0,
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyRawWaterEntry = RawWaterTurbidityEntry(
            entryName = "Raw Water Turbidity",
            plantName = "Plant",
            operatorName = "Operator",
            turbidityReading = 2.0,
            additionalNotes = "Hello, I am a Note",
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyclarifiedWaterTurbidityEntry = clarifiedWaterTurbidityEntry(
            entryName = "Clarified Water Turbidity",
            plantName = "Plant",
            operatorName = "Operator",
            turbidityReading = 2.0,
            additionalNotes = "Hello, I am a Note",
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyfilteredWaterTurbidityEntry = filteredWaterTurbidityEntry(
            entryName = "Filtered Water Turbidity",
            plantName = "Plant",
            operatorName = "Operator",
            turbidityReadings = doubleArrayOf(0.0,1.0,2.0,3.0,4.0,5.0),
            additionalNotes = "Hello, I am a Note",
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyFeedbackEntry = FeedbackEntry(
            entryName = "Feedback",
            plantName = "Plant",
            operatorName = "Operator",
            additionalNotes = "Hello, I am a Note",
            operatorFeedback = "Improve the app please",
            creationDateTime = "09-27-24 05:31:27"
        )
        dummyPlantFlowEntry = PlantFlowEntry(
            entryName = "Plant Flow",
            plantName = "Plant",
            operatorName = "Operator",
            creationDateTime = "09-27-24 05:31:27",
            additionalNotes = "Hello, I am a Note",
            inflowRate = 1.0f,
            chemicalType = "PAC"
        )
    }
    private fun addEntry(container: LinearLayout, entry: Entry) {
        // Inflate the input layout
        val inflater = LayoutInflater.from(context)
        val entryLayout = inflater.inflate(R.layout.layout_entry, container, false)


        // Set variables to access necessary UI elements
        val entryName = entryLayout.findViewById<TextView>(R.id.entry_title)
        val expandableText = entryLayout.findViewById<TextView>(R.id.expandableText)
        val timeStamp = entryLayout.findViewById<TextView>(R.id.timestamp)
        val editButton = entryLayout.findViewById<TextView>(R.id.edit_button)
        editButton.setOnClickListener {
            if (!isPopupOpen) {
                showEditEntryDialog(container, entryLayout, entry)
            }
            isPopupOpen = true
        }

        // Read data into front end display
        when (entry) {
            is PlantFlowEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.inflow_rate_with_input,entry.inflowRate)
                timeStamp.text = entry.creationDateTime
            }
            is RawWaterTurbidityEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReading)
                timeStamp.text = entry.creationDateTime
            }
            is clarifiedWaterTurbidityEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReading)
                timeStamp.text = entry.creationDateTime
            }
            is filteredWaterTurbidityEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReadings[0])
                timeStamp.text = entry.creationDateTime
            }
            is CoagulantCalibrationEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.chemical_dose_with_input,entry.chemicalDose)
                timeStamp.text = entry.creationDateTime
            }
            is CoagulantChangeDoseEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.new_slider_pos,entry.sliderPosition)
                timeStamp.text = entry.creationDateTime
            }

            is ChlorineCalibrationEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.chemical_dose_with_input,entry.chemicalDose)
                timeStamp.text = entry.creationDateTime
            }
            is ChlorineChangeDoseEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.new_slider_pos,entry.sliderPosition)
                timeStamp.text = entry.creationDateTime
            }
            is FeedbackEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.feedback_with_input,entry.operatorFeedback)
                timeStamp.text = entry.creationDateTime
            }
        }

        // handle expand/contract button logic
        var expanded = false
        expandableText.visibility = View.GONE
        val expandButton = entryLayout.findViewById<Button>(R.id.btnToggle)
        expandButton.setOnClickListener {
            if(expanded) {
                expanded = false
                expandableText.visibility = View.GONE
                expandButton.background = context?.let { it1 -> ContextCompat.getDrawable(it1,R.drawable.dropdown_toggle) }
            }
            else {
                expanded = true
                expandableText.visibility = View.VISIBLE
                expandButton.background = context?.let { it1 -> ContextCompat.getDrawable(it1,R.drawable.dropup_toggle) }
            }
        }

        // Add the inflated layout to the container
        container.addView(entryLayout)
    }

    private fun showEditEntryDialog(container: LinearLayout, entryLayout : View, entry: Entry) {
        val dialogView : View = when (entry) {
            is PlantFlowEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_plant_flow_entry, null)
            }
            is RawWaterTurbidityEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_raw_water_turbidity_entry, null)
            }
            is clarifiedWaterTurbidityEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_clarified_water_turbidity_entry,null)
            }
            is filteredWaterTurbidityEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_filtered_water_turbidity_entry,null)
            }
            is CoagulantCalibrationEntry-> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_coagulant_calibration_entry, null)
            }
            is ChlorineCalibrationEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_chlorine_calibration_entry, null)
            }
            is CoagulantChangeDoseEntry-> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_coagulant_change_dosage_entry, null)
            }
            is ChlorineChangeDoseEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_chlorine_change_dosage_entry, null)
            }
            is FeedbackEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_feedback_entry, null)
            }
            else -> {
                throw IllegalArgumentException("Unexpected entry type $entry")
            }
        }
        val closeButton = dialogView.findViewById<Button>(R.id.close_button)
        val deletedButton = dialogView.findViewById<Button>(R.id.delete_button)
        val saveButton = dialogView.findViewById<Button>(R.id.save_button)
        val titleText = dialogView.findViewById<TextView>(R.id.title)
        val timeText = dialogView.findViewById<TextView>(R.id.time_text)

        when (entry) {
            is PlantFlowEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.edit_inflow_rate).setText("${entry.inflowRate}")
            }
            is RawWaterTurbidityEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.edit_turbidity).setText("${entry.turbidityReading}")
            }
            is clarifiedWaterTurbidityEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.edit_turbidity).setText("${entry.turbidityReading}")
            }
            is filteredWaterTurbidityEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                val editTextViews = intArrayOf(R.id.edit_reading0,R.id.edit_reading1,
                    R.id.edit_reading2,R.id.edit_reading3,R.id.edit_reading4,R.id.edit_reading5)
                for (i in 0 until 6) {
                    val editTextView = dialogView.findViewById<EditText>(editTextViews[i])
                    if (i < entry.turbidityReadings.size) {
                        editTextView.setText("${entry.turbidityReadings[i]}")
                    } else {
//                        editTextView.visibility = View.INVISIBLE
                    }
                }
            }
            is CoagulantCalibrationEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.slider_position).setText("${entry.sliderPosition}")
                dialogView.findViewById<EditText>(R.id.inflow_rate).setText("${entry.inflowRate}")
                dialogView.findViewById<EditText>(R.id.start_height).setText("${entry.startVolume}")
                dialogView.findViewById<EditText>(R.id.end_height).setText("${entry.endVolume}")
                dialogView.findViewById<EditText>(R.id.time_elapsed).setText("${entry.timeElapsed}")
                dialogView.findViewById<EditText>(R.id.chemical_dose).setText("${entry.chemicalDose}")
                dialogView.findViewById<EditText>(R.id.chemical_flow_rate).setText("${entry.chemicalFlowRate}")
                dialogView.findViewById<EditText>(R.id.active_tank_volume).setText("${entry.activeTankVolume}")
            }
            is ChlorineCalibrationEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.slider_position).setText("${entry.sliderPosition}")
                dialogView.findViewById<EditText>(R.id.inflow_rate).setText("${entry.inflowRate}")
                dialogView.findViewById<EditText>(R.id.start_height).setText("${entry.startVolume}")
                dialogView.findViewById<EditText>(R.id.end_height).setText("${entry.endVolume}")
                dialogView.findViewById<EditText>(R.id.time_elapsed).setText("${entry.timeElapsed}")
                dialogView.findViewById<EditText>(R.id.chemical_dose).setText("${entry.chemicalDose}")
                dialogView.findViewById<EditText>(R.id.chemical_flow_rate).setText("${entry.chemicalFlowRate}")
                dialogView.findViewById<EditText>(R.id.active_tank_volume).setText("${entry.activeTankVolume}")
            }
            is CoagulantChangeDoseEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.slider_position).setText("${entry.sliderPosition}")
                dialogView.findViewById<EditText>(R.id.updated_slider_position).setText("${entry.updatedSliderPosition}")
                dialogView.findViewById<EditText>(R.id.chemical_dose).setText("${entry.chemicalDose}")
                dialogView.findViewById<EditText>(R.id.target_chemical_dose).setText("${entry.targetChemicalDose}")
                dialogView.findViewById<EditText>(R.id.chemical_flow_rate).setText("${entry.chemicalFlowRate}")
                dialogView.findViewById<EditText>(R.id.updated_chemical_flow_rate).setText("${entry.updatedChemicalFlowRate}")
            }
            is ChlorineChangeDoseEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.slider_position).setText("${entry.sliderPosition}")
                dialogView.findViewById<EditText>(R.id.updated_slider_position).setText("${entry.updatedSliderPosition}")
                dialogView.findViewById<EditText>(R.id.chemical_dose).setText("${entry.chemicalDose}")
                dialogView.findViewById<EditText>(R.id.target_chemical_dose).setText("${entry.targetChemicalDose}")
                dialogView.findViewById<EditText>(R.id.chemical_flow_rate).setText("${entry.chemicalFlowRate}")
                dialogView.findViewById<EditText>(R.id.updated_chemical_flow_rate).setText("${entry.updatedChemicalFlowRate}")
            }
            is FeedbackEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_feedback).setText(entry.operatorFeedback)
            }
        }

        // Create and show the AlertDialog
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        closeButton.setOnClickListener {
            isPopupOpen = false
            dialog.dismiss()
        }

        deletedButton.setOnClickListener {
            container.removeView(entryLayout)
            // TODO: Delete entry
            isPopupOpen = false
            dialog.dismiss()
        }

        saveButton.setOnClickListener {
            // TODO: Save entry
            isPopupOpen = false
            dialog.dismiss()
        }

        // Show the dialog
        dialog.dismiss()
        dialog.show()
    }
}