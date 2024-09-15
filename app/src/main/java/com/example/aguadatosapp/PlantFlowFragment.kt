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
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// PlantFlowFragment.kt
class PlantFlowFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plant_flow, container, false)

        //set up logic for back button
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_to_home)
        }

        //set up logic for submit button
        view.findViewById<Button>(R.id.plant_flow_submit_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_page_to_plant_flow_confirm_entry)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //initialize view model
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // set variables to access each necessary UI elements
        val waterInflowRate: EditText = view.findViewById(R.id.plant_inflow_rate_input)
        val notesInput: EditText = view.findViewById(R.id.plant_inflow_notes_input)
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //set starting value if data has already been entered
        if(viewModel.plantFlowData.value != null) {
            waterInflowRate.setText(viewModel.plantFlowData.value.toString())
        }

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)
        //TODO: reformat notes boxes

        //watch input elements to update entry data whenever an input is added
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //get user input, convert to double, and add to entry (for each input)
                val waterInflowRateText = waterInflowRate.text.toString()
                if (waterInflowRateText.isNotEmpty()) {
                    viewModel.plantFlowData.value = waterInflowRateText.toDouble()
                }
                //send data to view model
                val notesText = notesInput.text.toString()
                if (notesText.isNotEmpty()) {
                    viewModel.plantFlowNotes.value = notesText
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        }
        //watch each user input
        waterInflowRate.addTextChangedListener(textWatcher)
        notesInput.addTextChangedListener(textWatcher)
    }
}