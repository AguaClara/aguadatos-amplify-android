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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import aws.smithy.kotlin.runtime.util.type

// RecordsFragment.kt
class RecordsFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // TODO: n right now is number of entries, needs to update dynamically based on input from backend
    private val n = 5
    private lateinit var dummyRawWaterEntry: TurbidityEntry
    private lateinit var dummyCoagulantCalibrationEntry: CalibrationEntry
    private lateinit var dummyFeedbackEntry: FeedbackEntry

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
        addEntry(container,dummyCoagulantCalibrationEntry)
        addEntry(container,dummyFeedbackEntry)

        return view
    }
    private fun createDummyData() {
        dummyRawWaterEntry = TurbidityEntry(
            id = "1",
            name = "Entry: Raw Water",
            turbidity = 2.0,
            notes = "Hello, I am a Note",
            time = "05:31:27",
            date = "09-27-24"
        )
        dummyCoagulantCalibrationEntry = CalibrationEntry(
            id = "2",
            name = "Entry: Coagulant Dosage Calibration",
            sliderPosition = 50.0,
            inflowRate = 5.0,
            startVolume = 3.5,
            endVolume = 2.5,
            timeElapsed = 37,
            chemDose = 2.0,
            chemFlowRate = 0.5,
            activeTankVolume = 20.0,
            time = "05:31:27",
            date = "09-27-24"
        )
        dummyFeedbackEntry = FeedbackEntry(
            id = "3",
            name = "Entry: Feedback",
            feedback = "Improve the app please",
            time = "05:31:27",
            date = "09-27-24"
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
                entryName.text = entry.name
                expandableText.text = getString(R.string.inflow_rate_with_input,entry.inflowRate)
                timeStamp.text = entry.time
            }
            is TurbidityEntry -> {
                entryName.text = entry.name
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidity)
                timeStamp.text = entry.time
            }
            is FilteredWaterEntry -> {
                entryName.text = entry.name
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityValues[0])
                timeStamp.text = entry.time
            }
            is CalibrationEntry -> {
                entryName.text = entry.name
                expandableText.text = getString(R.string.chemical_dose_with_input,entry.chemDose)
                timeStamp.text = entry.time
            }
            is ChangeDoseEntry -> {
                entryName.text = entry.name
                expandableText.text = getString(R.string.new_slider_pos,entry.newSliderPosition)
                timeStamp.text = entry.time
            }
            is FeedbackEntry -> { //FIXME: update feedback data submission to include time and date
                entryName.text = entry.name
                expandableText.text = getString(R.string.feedback_with_input,entry.feedback)
                timeStamp.text = entry.time
            }
        }

        // Add the inflated layout to the container
        container.addView(entryLayout)
    }

    private fun showEditEntryDialog(entry: Entry) {
        // Inflate the custom layout for the dialog
        val dialogView : View = when (entry) {
            is PlantFlowEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
            is TurbidityEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
            is FilteredWaterEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
            is CalibrationEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
            is ChangeDoseEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
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
                titleText.text = entry.name
                timeText.text = (entry.date + "   " + entry.time)
            }
            is TurbidityEntry -> {
                titleText.text = entry.name
                timeText.text = (entry.date + "   " + entry.time)
            }
            is FilteredWaterEntry -> {
                titleText.text = entry.name
                timeText.text = (entry.date + "   " + entry.time)
            }
            is CalibrationEntry -> {
                titleText.text = entry.name
                timeText.text = (entry.date + "   " + entry.time)
                val sliderPos = dialogView.findViewById<EditText>(R.id.edit_slider_position)
                val inflowRate = dialogView.findViewById<EditText>(R.id.edit_inflow_rate)
                val startHeight = dialogView.findViewById<EditText>(R.id.edit_start_height)
                val endHeight = dialogView.findViewById<EditText>(R.id.edit_end_height)
                val timeElapsed = dialogView.findViewById<EditText>(R.id.edit_time_elapsed)
                val chemicalDose = dialogView.findViewById<EditText>(R.id.edit_chemical_dose)
                val chemicalFlowRate = dialogView.findViewById<EditText>(R.id.edit_chemical_flow_rate)
                val chemicalTypeText = dialogView.findViewById<TextView>(R.id.chemical_type_text)
                chemicalTypeText.setText("Chemical Type: PAC ")
                sliderPos.setText("${entry.sliderPosition}")
                inflowRate.setText("${entry.inflowRate}")
                startHeight.setText("${entry.startVolume}")
                endHeight.setText("${entry.endVolume}")
                timeElapsed.setText("${entry.timeElapsed}")
                chemicalDose.setText("${entry.chemDose}")
                chemicalFlowRate.setText("${entry.chemFlowRate}")
            }
            is ChangeDoseEntry -> {
                titleText.text = entry.name
                timeText.text = (entry.date + "   " + entry.time)
            }
            is FeedbackEntry -> {
                titleText.text = entry.name
                timeText.text = (entry.date + "   " + entry.time)
                val feedbackText = dialogView.findViewById<EditText>(R.id.edit_feedback)

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