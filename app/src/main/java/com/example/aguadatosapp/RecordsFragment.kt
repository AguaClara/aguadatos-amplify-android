package com.example.aguadatosapp

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
}