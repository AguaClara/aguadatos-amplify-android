package com.example.aguadatosapp

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.ClarifiedEntry
import com.amplifyframework.datastore.generated.model.FilteredEntry
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// FilteredWaterConfirmEntryFragment.kt
class FilteredWaterConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filtered_water_confirm_entry, container, false)

        // Handle logic for X button
        view.findViewById<Button>(R.id.filtered_water_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_filtered_water_confirm_to_filtered_water_page)
        }
        // Handle logic for confirm entry button
        view.findViewById<Button>(R.id.filtered_water_confirm_button).setOnClickListener {
            //add time to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText

            val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            var plantId = ""
            var opId = ""
            if (sharedPreferences != null) {
                plantId = sharedPreferences.getString("plantID", null).toString()
                opId = sharedPreferences.getString("operatorID", null).toString()
            }
            viewModel.filteredWaterData.value?.let { filteredWaterData ->
                val doubleList = filteredWaterData.map { it.toDouble() } // Convert list of Float to list of Double
                createFilteredWaterEntry(
                    Temporal.DateTime(Instant.now().toString()),
                    plantId,
                    opId,
                    doubleList,
                    viewModel.clarifiedWaterNotes.value
                )
            }

            findNavController().navigate(R.id.action_filtered_water_confirm_to_filtered_water_view)
        }

        return view
    }

    private fun addInputLayout(view: View, container: LinearLayout, filterNumber: Int) {
        // Inflate the input layout
        val inflater = LayoutInflater.from(context)
        val inputLayout = inflater.inflate(R.layout.layout_filtered_turbidity_view_field, container, false)

        // Set variables to access necessary UI elements
        val turbidityView = inputLayout.findViewById<TextView>(R.id.turbidity_text)
        turbidityView.text = getString(R.string.filter_number_turbidity_text,filterNumber)

        // Add the inflated layout to the container
        container.addView(inputLayout)

        // Observe the data from ViewModel to display on this page
        viewModel.filteredWaterData.observe(viewLifecycleOwner) { turbidity ->
            // Update UI based on the received data
            if (turbidity != null) {
                //Update all text views to contain the data numbers
                turbidityView.text = getString(R.string.turbidity_with_input,turbidity[filterNumber-1])
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        //add a display for the turbidity of each filter in use
        val numFilters = viewModel.numFilters.value
        val container = view.findViewById<LinearLayout>(R.id.filtered_view_container)
        if (numFilters != null) {
            for(i in 1..numFilters) {
                if(viewModel.filteredWaterData.value?.get(i-1)!! > -1) {
                    addInputLayout(view, container, i)
                }
            }
        }

        viewModel.filteredWaterNotes.observe(viewLifecycleOwner) { notes ->
            // Update UI based on the received data
            if (notes != null) {
                //Update all text views to contain the data numbers
                val notesView: TextView = view.findViewById(R.id.filtered_water_notes_text)
                notesView.text = notes

            }
        }

        //add date to submission
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateText = LocalDate.now().format(dateFormatter)
        viewModel.date.value = dateText

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        dateView.text = getString(R.string.date,dateText)
    }

    // this function sends entry data to backend
    private fun createFilteredWaterEntry(createdAt: Temporal.DateTime, plantId: String, operatorId: String, turbidityValues: List<Double>, notes: String?) {
        // Create a FilteredWaterEntry instance
        val newFilteredWaterEntry = FilteredEntry.builder()
            .createdAt(createdAt) // Temporal.DateTime object
            .turbidities(turbidityValues) // List<Double> value
            .notes(notes) // Optional, can be null
            .plant(Plant.justId(plantId)) // Reference Plant by ID
            .operator(Operator.justId(operatorId)) // Reference Operator by ID
            .build()

        // Save the FilteredWaterEntry to DataStore
        Amplify.DataStore.save(newFilteredWaterEntry, {
            // Success callback
            Log.d("msg","Successfully saved FilteredWaterEntry with ID: ${newFilteredWaterEntry.id}")
        },
            { error ->
                // Error callback
                Log.d("msg","Failed to save FilteredWaterEntry: ${error.message}")
            }
        )
    }
}