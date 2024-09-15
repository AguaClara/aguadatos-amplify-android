package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.Date

// FilteredWaterViewEntryFragment.kt
class FilteredWaterViewEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_filtered_water_view_entry, container, false)

        //listener for return home button
        view.findViewById<Button>(R.id.filtered_water_return_home).setOnClickListener {
            findNavController().navigate(R.id.action_filtered_water_view_to_home)
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
        //display date at the top
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = getString(R.string.date,dateVal.format(Date()))
        }
        //display submission date and time at the bottom
        val dateTimeView: TextView = view.findViewById(R.id.submit_date_time)
        val timeVal = viewModel.time.value
        if (timeVal != null && dateVal != null) {
            dateTimeView.text = getString(R.string.date_and_time,dateVal,timeVal)
        }
    }
}