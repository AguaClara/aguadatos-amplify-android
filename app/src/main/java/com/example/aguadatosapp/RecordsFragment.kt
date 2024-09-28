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

        //initialize view model and feedback input element
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

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
            feedback = "Improve the app please"
        )
    }
    private fun addEntry(container: LinearLayout) {
        // Inflate the input layout
        val inflater = LayoutInflater.from(context)
        val entryLayout = inflater.inflate(R.layout.layout_entry, container, false)

        // Set variables to access necessary UI elements
        val entryName = entryLayout.findViewById<TextView>(R.id.entry_title)
        val expandableContent = entryLayout.findViewById<TextView>(R.id.expandableContent)
        val timeStamp = entryLayout.findViewById<TextView>(R.id.timestamp)

        //set starting value if data has already been entered
        if(viewModel.filteredWaterData.value!![filterNumber-1] > -1.0) {
            turbidityInput.setText(viewModel.filteredWaterData.value!![filterNumber-1].toString())
        }

        // Add the inflated layout to the container
        container.addView(inputLayout)

        // Set a TextWatcher to observe changes in the turbidity input field
        turbidityInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                //get user input, convert to double, and add to entry (for each input)
                val turbidityValue = turbidityInput.text.toString()
                if (turbidityValue.isNotEmpty()) {
                    viewModel.filteredWaterData.value?.set(filterNumber-1, turbidityValue.toDouble())
                }
            }
        })
    }
}