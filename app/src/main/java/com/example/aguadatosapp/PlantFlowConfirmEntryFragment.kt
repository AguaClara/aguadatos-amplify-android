package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.Date

// PlantFlowConfirmEntryFragment.kt
class PlantFlowConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflate the view
        val view = inflater.inflate(R.layout.fragment_plant_flow_confirm_entry, container, false)

        //handle logic for X button
        view.findViewById<Button>(R.id.plant_flow_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_confirm_to_plant_flow_page)
        }
        //handle logic for confirm entry button
        view.findViewById<Button>(R.id.plant_flow_confirm_button).setOnClickListener {
            //TODO: @POST TEAM FA'24, this is where variables in viewModel will be sent to backend
            findNavController().navigate(R.id.action_plant_flow_confirm_to_plant_flow_view)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        // Observe the data from ViewModel
        viewModel.plantFlowData.observe(viewLifecycleOwner) { inflowRate ->
            // Update UI based on the received data
            if (inflowRate != null) {
                //Update all text views to contain the data numbers
                val inflowRateView: TextView = view.findViewById(R.id.inflow_rate_text)
                inflowRateView.text = getString(R.string.inflow_rate_with_input, inflowRate)
            }
        }
        viewModel.plantFlowNotes.observe(viewLifecycleOwner) { notes ->
            // Update UI based on the received data
            if (notes != null) {
                //Update all text views to contain the data numbers
                val notesView: TextView = view.findViewById(R.id.plant_flow_notes_text)
                notesView.text = notes
            }
        }

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = getString(R.string.date,dateVal.format(Date()))
        }
    }

}