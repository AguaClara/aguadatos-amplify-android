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

// PlantFlowConfirmEntryFragment.kt
class PlantFlowConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_plant_flow_confirm_entry, container, false)

        //listener for back button (X)
        view.findViewById<Button>(R.id.plant_flow_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_confirm_to_plant_flow_page)
        }
        //listener for confirm entry button
        view.findViewById<Button>(R.id.plant_flow_confirm_button).setOnClickListener {
            //FIXME: commit temporary variables to backend and clear values
            findNavController().navigate(R.id.action_plant_flow_confirm_to_plant_flow_view)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Observe the data from ViewModel
        viewModel.plantFlowData.observe(viewLifecycleOwner, Observer { inflowRate ->
            // Update UI based on the received data
            if (inflowRate != null) {
                //Update all text views to contain the data numbers
                val inflowRateView: TextView = view.findViewById(R.id.inflow_rate_text)
                inflowRateView.text = "Inflow Rate: "+inflowRate+" L/s"
            }
        })
        viewModel.plantFlowNotes.observe(viewLifecycleOwner, Observer { notes ->
            // Update UI based on the received data
            if (notes != null) {
                //Update all text views to contain the data numbers
                val notesView: TextView = view.findViewById(R.id.plant_flow_notes_text)
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