package com.example.aguadatosapp

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import aws.smithy.kotlin.runtime.util.type

// RecordsFragment.kt
class RecordsFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // TODO: n right now is number of entries, needs to update dynamically based on input from backend
    private val n = 9
    private lateinit var dummyCoagulantCalibrationEntry: CoagulantCalibrationEntry
    private lateinit var dummyFeedbackEntry: FeedbackEntry
    private lateinit var dummyPlantFlowEntry: PlantFlowEntry
    private lateinit var dummyRawWaterEntry: RawWaterTurbidityEntry
    private lateinit var dummyclarifiedWaterTurbidityEntry: clarifiedWaterTurbidityEntry
    private lateinit var dummyfilteredWaterTurbidityEntry: filteredWaterTurbidityEntry

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
        addEntry(container,dummyRawWaterEntry)
        addEntry(container,dummyfilteredWaterTurbidityEntry)
        addEntry(container,dummyCoagulantCalibrationEntry)
        addEntry(container,dummyFeedbackEntry)
        addEntry(container,dummyPlantFlowEntry)
        addEntry(container,dummyCoagulantCalibrationEntry)
        addEntry(container,dummyFeedbackEntry)

        return view
    }
    private fun createDummyData() {
        dummyRawWaterEntry = RawWaterTurbidityEntry(
            entryName = "Raw Water Turbidity",
            plantName = "Plant",
            operatorName = "Operator",
            turbidityReadings = 2.0,
            additionalNotes = "Hello, I am a Note",
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyclarifiedWaterTurbidityEntry = clarifiedWaterTurbidityEntry(
            entryName = "Clarified Water Turbidity",
            plantName = "Plant",
            operatorName = "Operator",
            turbidityReadings = 2.0,
            additionalNotes = "Hello, I am a Note",
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyfilteredWaterTurbidityEntry = filteredWaterTurbidityEntry(
            entryName = "Filtered Water Turbidity",
            plantName = "Plant",
            operatorName = "Operator",
            turbidityReadings = doubleArrayOf(2.0),
            additionalNotes = "Hello, I am a Note",
            creationDateTime = "09-27-24 05:31:27",
            chemicalType = "PAC"
        )
        dummyCoagulantCalibrationEntry = CoagulantCalibrationEntry(
            entryName = "Coagulant Dosage Calibration",
            plantName = "Plant",
            operatorName = "Operator",
            additionalNotes = "Hello, I am a Note",
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
            showEditEntryDialog(entry)
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
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReadings)
                timeStamp.text = entry.creationDateTime
            }
            is clarifiedWaterTurbidityEntry -> {
                entryName.text = entry.entryName
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReadings)
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

    private fun showEditEntryDialog(entry: Entry) {
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
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_raw_water_turbidity_entry,null)
            }
            is CoagulantCalibrationEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
            is CoagulantChangeDoseEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_change_dosage_entry, null)
            }
            is FeedbackEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_feedback_entry, null)
            }
            else -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
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
                dialogView.findViewById<EditText>(R.id.edit_turbidity).setText("${entry.turbidityReadings}")
            }
            is clarifiedWaterTurbidityEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.edit_turbidity).setText("${entry.turbidityReadings}")
            }
            is filteredWaterTurbidityEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.edit_turbidity).setText("${entry.turbidityReadings[0]}")
            }
            is CoagulantCalibrationEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.edit_notes).setText(entry.additionalNotes)
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
            }
            is ChlorineCalibrationEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
            }
            is ChlorineChangeDoseEntry -> {
                titleText.text = entry.entryName
                timeText.text = entry.creationDateTime
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
            dialog.dismiss()  // Close the dialog
        }

        // FIXME
        deletedButton.setOnClickListener {
            dialog.dismiss()  // Delete entry
        }

        // FIXME
        saveButton.setOnClickListener {
            dialog.dismiss()  // Close the dialog after saving
        }

        // Show the dialog
        dialog.show()
    }
}