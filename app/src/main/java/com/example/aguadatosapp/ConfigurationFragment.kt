package com.example.aguadatosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class ConfigurationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_configuration, container, false)

        Log.d("FragmentNavigation", "ConfigurationFragment is created")

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        //set variables to access necessary UI elements
        val opNameInput: EditText = view.findViewById(R.id.operator_name_input)
        val plantNameInput: EditText = view.findViewById(R.id.plant_name_input)
        val chemTypeSwitch: ImageView = view.findViewById(R.id.chem_type_switch)
        val chemConcInput: EditText = view.findViewById(R.id.chem_conc_input)
        val numFiltersInput: EditText = view.findViewById(R.id.num_filters_input)
        val saveButton: Button = view.findViewById(R.id.save_button)
        var switchChecked = false

        //set up UI using current configuration
        opNameInput.setText(viewModel.plantOperatorName.value)
        plantNameInput.setText(viewModel.plantName.value)
        chemConcInput.setText(viewModel.chemConcentration.value.toString())
        numFiltersInput.setText(viewModel.numFilters.value.toString())
        if(viewModel.chemType.value == "PACl") {
            chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_off)
            switchChecked = false
        }
        else if(viewModel.chemType.value == "Aluminum Sulfate") {
            chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_on)
            switchChecked = true
        }

        //listener for chemical type switch
        chemTypeSwitch.setOnClickListener {
            if(switchChecked) {
                //flip switch off
                chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_off)
            }
            else {
                //flip switch on
                chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_on)
            }
            switchChecked = !switchChecked
        }

        // handle logic for save button
        saveButton.setOnClickListener {
            // update configuration data in viewModel
            viewModel.plantOperatorName.value = opNameInput.text.toString()
            viewModel.plantName.value = plantNameInput.text.toString()
            if(switchChecked) {
                viewModel.chemType.value = "Aluminum Sulfate"
            }
            else {
                viewModel.chemType.value = "PACl"
            }
            viewModel.chemConcentration.value = chemConcInput.text.toString().toDouble()
            //only accept filter input if it is valid (0<n<=6)
            val numFiltersValue = numFiltersInput.text.toString().toInt()
            if(numFiltersValue in 1..6) {
                viewModel.numFilters.value = numFiltersInput.text.toString().toInt()
            }
            else {
                //otherwise give error message explaining what's wrong
                Toast.makeText(context,"Please enter a valid number of filters (between 1 and 6).",Toast.LENGTH_SHORT).show()
            }
            // pop a toast to let user know changes are saved
            Toast.makeText(context,"Your changes have been saved.",Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentNavigation", "ConfigurationFragment is now visible")
    }
}