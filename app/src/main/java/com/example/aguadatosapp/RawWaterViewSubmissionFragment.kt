package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.Date

// RawWaterViewSubmissionFragment.kt
class RawWaterViewSubmissionFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_raw_water_view_submission, container, false)

        //listener for return home button
        view.findViewById<Button>(R.id.raw_water_return_home).setOnClickListener {
            findNavController().navigate(R.id.action_raw_water_view_to_home)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val temp = viewModel.rawWaterData.value
        Toast.makeText(context,"$temp",Toast.LENGTH_SHORT).show()
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
            dateTimeView.text = getString(R.string.date_and_time,dateVal.format(Date()),timeVal.format(Date()))
        }
    }
}