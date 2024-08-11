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

// ClarifiedFragment.kt
class ClarifiedWaterFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_clarified_water_page, container, false)
        // Handle logic for back button
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_clarified_water_to_home)
        }
        // Handle logic for submit button
        view.findViewById<Button>(R.id.clarified_water_submit_button).setOnClickListener {
            findNavController().navigate(R.id.action_clarified_water_page_to_clarified_water_confirm_entry)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // set variables to access each input element
        val turbidity: EditText = view.findViewById(R.id.turbidity_input)
        val notesInput: EditText = view.findViewById(R.id.clarified_water_notes_input)
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //set starting value if data has already been entered
        if(viewModel.clarifiedWaterData.value != null) {
            turbidity.setText(viewModel.clarifiedWaterData.value.toString())
        }

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
                val turbidityText = turbidity.text.toString()
                if (turbidityText.isNotEmpty()) {
                    viewModel.clarifiedWaterData.value = turbidityText.toDouble()
                }

                val notesText = notesInput.text.toString()
                if (notesText.isNotEmpty()) {
                    viewModel.clarifiedWaterNotes.value = notesText
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        }
        //watch each user input
        turbidity.addTextChangedListener(textWatcher)
        notesInput.addTextChangedListener(textWatcher)
    }
}