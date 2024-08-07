package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// RawWaterConfirmEntryFragment.kt
class RawWaterConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_raw_water_confirm_entry, container, false)

        //listener for back button (X)
        view.findViewById<Button>(R.id.raw_water_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_raw_water_confirm_to_raw_water_page)
        }
        //listener for confirm entry button
        view.findViewById<Button>(R.id.raw_water_confirm_button).setOnClickListener {
            //FIXME: commit temporary variables to backend and clear values
            findNavController().navigate(R.id.action_raw_water_confirm_to_raw_water_view)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        // Observe the data from ViewModel
        viewModel.rawWaterData.observe(viewLifecycleOwner, Observer { turbidity ->
            // Update UI based on the received data
            if (turbidity != null) {
                //Update all text views to contain the data numbers
                val turbidityView: TextView = view.findViewById(R.id.turbidity_text)
                turbidityView.text = "Turbidity: "+turbidity+" NTU"
            }
        })
        viewModel.rawWaterNotes.observe(viewLifecycleOwner, Observer { notes ->
            // Update UI based on the received data
            if (notes != null) {
                //Update all text views to contain the data numbers
                val notesView: TextView = view.findViewById(R.id.raw_water_notes_text)
                notesView.text = notes
            }
        })

        //add date and time to submission
        //viewModel.date.value = SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        //viewModel.time.value = SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = "Date: "+dateVal.format(Date())
        }
    }

}