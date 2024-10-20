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
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.record_edit_entry, null)

        // Initialize views inside the dialog
        val closeButton = dialogView.findViewById<Button>(R.id.close_button)
        val deletedButton = dialogView.findViewById<Button>(R.id.delete_button)
        val saveButton = dialogView.findViewById<Button>(R.id.save_button)
        val titleTextView = dialogView.findViewById<TextView>(R.id.title)
        val timeTextView = dialogView.findViewById<TextView>(R.id.time_text)
        // ChangeDoseEntry
        val sliderPosText = dialogView.findViewById<TextView>(R.id.slider_position_text)
        val inflowRateText = dialogView.findViewById<TextView>(R.id.inflow_rate_text)
        val startHeightText = dialogView.findViewById<TextView>(R.id.start_height_text)
        val endHeightText = dialogView.findViewById<TextView>(R.id.end_height_text)
        val timeElapsedText = dialogView.findViewById<TextView>(R.id.time_elapsed_text)
        val chemicalDoseText = dialogView.findViewById<TextView>(R.id.chemical_dose_text)
        val chemicalFlowRateText = dialogView.findViewById<TextView>(R.id.chemical_flow_rate_text)
        val chemicalTypeTextView = dialogView.findViewById<TextView>(R.id.chemical_type_text)
        val calibrationTextView = dialogView.findViewById<TextView>(R.id.calibration_text)
        // FeedbackEntry
        val feedbackText = dialogView.findViewById<TextView>(R.id.feedback_text)


        when (entry) {
            is CalibrationEntry -> {
                titleTextView.text = entry.name
                timeTextView.text = (entry.date + "   " + entry.time)
                chemicalTypeTextView.text = "Chemical Type: PAC "
                sliderPosText.text = ("• Slider Position: ${entry.sliderPosition} %")
                inflowRateText.text = ("• Inflow Rate: ${entry.inflowRate} mL/s")
                startHeightText.text = ("• Start Height: ${entry.startVolume} mL")
                endHeightText.text = ("• End Height: ${entry.endVolume} mL")
                timeElapsedText.text = ("• Time Elapsed: ${entry.timeElapsed} s")
                chemicalDoseText.text = ("• Chemical Dose: ${entry.chemDose} mg/L")
                chemicalFlowRateText.text = ("• Chemical Flow Rate: ${entry.chemFlowRate} mL/s")
                feedbackText.visibility = View.GONE
            }
            is TurbidityEntry -> {
                titleTextView.text = entry.name
                timeTextView.text = (entry.date + "\t" + entry.time)
                chemicalTypeTextView.visibility = View.GONE
                calibrationTextView.visibility = View.GONE
                chemicalTypeTextView.visibility = View.GONE
                sliderPosText.visibility = View.GONE
                inflowRateText.visibility = View.GONE
                startHeightText.visibility = View.GONE
                endHeightText.visibility = View.GONE
                timeElapsedText.visibility = View.GONE
                chemicalDoseText.visibility = View.GONE
                chemicalFlowRateText.visibility = View.GONE
                feedbackText.visibility = View.GONE
            }
            is FeedbackEntry -> {
                titleTextView.text = entry.name
                timeTextView.text = (entry.date + "\t" + entry.time)
                feedbackText.text = "Feedback: ${entry.feedback}"
                chemicalTypeTextView.visibility = View.GONE
                calibrationTextView.visibility = View.GONE
                chemicalTypeTextView.visibility = View.GONE
                sliderPosText.visibility = View.GONE
                inflowRateText.visibility = View.GONE
                startHeightText.visibility = View.GONE
                endHeightText.visibility = View.GONE
                timeElapsedText.visibility = View.GONE
                chemicalDoseText.visibility = View.GONE
                chemicalFlowRateText.visibility = View.GONE
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