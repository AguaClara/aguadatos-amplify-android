package com.example.aguadatosapp

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.InflowEntry
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter


// PlantFlowConfirmEntryFragment.kt
class PlantFlowConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //inflate the view
        val view = inflater.inflate(R.layout.fragment_plant_flow_confirm_entry, container, false)
        val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

        //handle logic for X button
        view.findViewById<Button>(R.id.plant_flow_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_plant_flow_confirm_to_plant_flow_page)
        }
        //handle logic for confirm entry button
        view.findViewById<Button>(R.id.plant_flow_confirm_button).setOnClickListener {
            //add time to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText
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

        //add date to submission
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateText = LocalDate.now().format(dateFormatter)
        viewModel.date.value = dateText

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        dateView.text = getString(R.string.date,dateText)
    }

    // this function sends entry data to backend
    fun createInflowEntry(createdAt: Temporal.DateTime, plantId: String, operatorId: String, inflowRate: Float, notes: String?) {
        // Create an InflowEntry instance
        val newInflowEntry = InflowEntry.builder()
            .createdAt(createdAt)
            .plantId(plantId)
            .operatorId(operatorId)
            .inflowRate(inflowRate.toDouble())
            .notes(notes)
            .build()

        // Save the InflowEntry to DataStore
        Amplify.DataStore.save(newInflowEntry, {
                // Success callback
                println("Successfully saved InflowEntry with ID: ${newInflowEntry.id}")
            },
            { error ->
                // Error callback
                println("Failed to save InflowEntry: ${error.message}")
            }
        )
    }
}