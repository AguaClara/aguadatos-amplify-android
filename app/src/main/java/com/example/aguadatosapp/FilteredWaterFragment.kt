package com.example.aguadatosapp

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

// FilteredFragment.kt
class FilteredWaterFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_filtered_water, container, false)
        // Handle logic for back button
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_filtered_water_to_home)
        }
        // Handle logic for submit button
        view.findViewById<Button>(R.id.filtered_water_submit_button).setOnClickListener {
            findNavController().navigate(R.id.action_filtered_water_page_to_filtered_water_confirm_entry)
        }

        return view
    }
    private fun addInputLayout(container: LinearLayout, filterNumber: Int) {
        // Inflate the input layout
        val inflater = LayoutInflater.from(context)
        val inputLayout = inflater.inflate(R.layout.layout_filtered_turbidity_input_field, container, false)

        // Optionally, you can manipulate or access views here if needed
        val turbidityTextTemp = inputLayout.findViewById<TextView>(R.id.turbidity_text)
        val turbidityInput = inputLayout.findViewById<EditText>(R.id.turbidity_input)
        turbidityTextTemp.text = getString(R.string.filter_number_turbidity_text,filterNumber)

        //set starting value if data has already been entered
        val filterTurbidityData: DoubleArray? = viewModel.filteredWaterData.value
        if(viewModel.filteredWaterData.value!![filterNumber-1] > -1.0) {
            turbidityInput.setText(viewModel.filteredWaterData.value!![filterNumber-1].toString())
        } //TODO: update confirm and view entry pages to display dynamic fields

        // Add the inflated layout to the container
        container.addView(inputLayout)

        // Set a TextWatcher to observe changes in the EditText
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
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // set variables to access each input element
        //val turbidity: EditText = view.findViewById(R.id.turbidity_input)
        val notesInput: EditText = view.findViewById(R.id.filtered_water_notes_input)
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //if number of filters is greater than zero, add extra inputs
        //TODO: update above comment
        val numFilters = viewModel.numFilters.value
        val container = view.findViewById<LinearLayout>(R.id.filtered_input_container)
        if (numFilters != null) {
            for(i in 1..numFilters) {
                addInputLayout(container, i)
            }
        }

        //set starting value if data has already been entered
        //if(viewModel.filteredWaterData.value!![0] > -1.0) {
        //    turbidity.setText(viewModel.filteredWaterData.value!![0].toString())
        //}

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)
        //TODO: make max numFilters 6 in configuration
        //watch input elements to update entry data whenever an input is added
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val notesText = notesInput.text.toString()
                if (notesText.isNotEmpty()) {
                    viewModel.filteredWaterNotes.value = notesText
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        }
        //watch each user input
        //turbidity.addTextChangedListener(textWatcher)
        notesInput.addTextChangedListener(textWatcher)
    }
}