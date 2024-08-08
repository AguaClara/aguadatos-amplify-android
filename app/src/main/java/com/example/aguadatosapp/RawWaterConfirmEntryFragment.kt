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

// RawWaterConfirmEntryFragment.kt
class RawWaterConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate layout for this fragment
        val view = inflater.inflate(R.layout.fragment_raw_water_confirm_entry, container, false)

        // Handle logic for X button
        view.findViewById<Button>(R.id.raw_water_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_raw_water_confirm_to_raw_water_page)
        }
        // Handle logic for confirm entry button
        view.findViewById<Button>(R.id.raw_water_confirm_button).setOnClickListener {
            //TODO: @POST TEAM FA'24, this is where data from the ViewModel will be sent to the backend
            findNavController().navigate(R.id.action_raw_water_confirm_to_raw_water_view)
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
        viewModel.rawWaterData.observe(viewLifecycleOwner) { turbidity ->
            // Update UI based on the received data
            if (turbidity != null) {
                //Update all text views to contain the data numbers
                val turbidityView: TextView = view.findViewById(R.id.turbidity_text)
                turbidityView.text = getString(R.string.turbidity_with_input,turbidity)
            }
        }
        viewModel.rawWaterNotes.observe(viewLifecycleOwner) { notes ->
            // Update UI based on the received data
            if (notes != null) {
                //Update all text views to contain the data numbers
                val notesView: TextView = view.findViewById(R.id.raw_water_notes_text)
                notesView.text = notes
            }
        }

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = getString(R.string.date, dateVal.format(Date()))
        }
    }

}