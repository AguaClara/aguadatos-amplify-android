package com.example.aguadatosapp

import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.Spanned
import android.text.TextWatcher
import android.text.style.SubscriptSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// PlantFlowFragment.kt
class PlantFlowFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    //entry contains: slider position, inflow rate, start volume, end volume, time elapsed, chemical dose, and chemical flow rate
    private val entry = mutableListOf(0.0)
    private val notes = mutableListOf("")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plant_flow_page, container, false)

        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_to_home)
        }
        view.findViewById<Button>(R.id.plant_flow_submit_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_page_to_plant_flow_confirm_entry)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // set variables to access each input element
        val waterInflowRate: EditText = view.findViewById(R.id.plant_inflow_rate_input)
        val notesInput: EditText = view.findViewById(R.id.plant_inflow_notes_input)
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)


        //watch input elements to update entry data whenever an input is added
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //get user input, convert to double, and add to entry (for each input)
                val waterInflowRateText = waterInflowRate.text.toString()
                if (waterInflowRateText.isNotEmpty()) {
                    entry[0] = waterInflowRateText.toDouble()
                }

                val notesText = notesInput.text.toString()
                if (notesText.isNotEmpty()) {
                    notes[0] = notesText
                }
                //update viewModel to store new entry data
                viewModel.plantFlowData.value = entry[0]
                viewModel.plantFlowNotes.value = notes[0]
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