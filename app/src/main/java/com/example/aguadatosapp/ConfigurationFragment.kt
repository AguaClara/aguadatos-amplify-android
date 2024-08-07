package com.example.aguadatosapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.R
import com.example.aguadatosapp.databinding.FragmentHomeBinding

class ConfigurationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_configuration, container, false)

        //listener for back button (<)
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_config_page_to_home)
        }
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
        val syncButton: Button = view.findViewById(R.id.sync_button)
        var switchChecked = false

        //set current values
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
                chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_off)
            }
            else {
                chemTypeSwitch.setImageResource(R.drawable.chem_type_switch_on)
            }
            switchChecked = !switchChecked
        }

        //listener for save button
        saveButton.setOnClickListener {
            viewModel.plantOperatorName.value = opNameInput.text.toString()
            viewModel.plantName.value = plantNameInput.text.toString()
            if(switchChecked) {
                viewModel.chemType.value = "Aluminum Sulfate"
            }
            else {
                viewModel.chemType.value = "PACl"
            }
            viewModel.chemConcentration.value = chemConcInput.text.toString().toDouble()
            viewModel.numFilters.value = numFiltersInput.text.toString().toInt()
            Toast.makeText(context,"Your changes have been saved.",Toast.LENGTH_SHORT).show()
        }

        syncButton.setOnClickListener() {
            //TODO: @POST TEAM FA'24, this is where data will be sent from local backend to AWS
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentNavigation", "ConfigurationFragment is now visible")
    }
}