package com.example.aguadatosapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.ClarifiedEntry
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// ClarifiedWaterConfirmEntryFragment.kt
class ClarifiedWaterConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate layout for this fragment
        val view = inflater.inflate(R.layout.fragment_clarified_water_confirm_entry, container, false)

        // Handle logic for X button
        view.findViewById<Button>(R.id.clarified_water_x_button).setOnClickListener {
            findNavController().navigate(R.id.action_clarified_water_confirm_to_clarified_water_page)
        }
        // Handle logic for confirm entry button
        view.findViewById<Button>(R.id.clarified_water_confirm_button).setOnClickListener {
            //add time to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText

            val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            var plantId = ""
            var opId = ""
            if (sharedPreferences != null) {
                plantId = sharedPreferences.getString("plantID", null).toString()
                opId = sharedPreferences.getString("operatorID", null).toString()
            }
            viewModel.clarifiedWaterData.value?.let { it1 -> createClarifiedWaterEntry(Temporal.DateTime(Instant.now().toString()), plantId, opId, it1.toFloat(), viewModel.clarifiedWaterNotes.value) }

            findNavController().navigate(R.id.action_clarified_water_confirm_to_clarified_water_view)
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
        viewModel.clarifiedWaterData.observe(viewLifecycleOwner) { turbidity ->
            // Update UI based on the received data
            if (turbidity != null) {
                //Update all text views to contain the data numbers
                val turbidityView: TextView = view.findViewById(R.id.turbidity_text)
                turbidityView.text = getString(R.string.turbidity_with_input,turbidity)
            }
        }
        viewModel.clarifiedWaterNotes.observe(viewLifecycleOwner) { notes ->
            // Update UI based on the received data
            if (notes != null) {
                //Update all text views to contain the data numbers
                val notesView: TextView = view.findViewById(R.id.clarified_water_notes_text)
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
    private fun createClarifiedWaterEntry(createdAt: Temporal.DateTime, plantId: String, operatorId: String, turbidity: Float, notes: String?) {
        // Create a clarifiedWaterEntry instance
        val newClarifiedWaterEntry = ClarifiedEntry.builder()
            .createdAt(createdAt) // Temporal.DateTime object
            .turbidity(turbidity.toDouble()) // Double value
            .notes(notes) // Optional, can be null
            .plant(Plant.justId(plantId)) // Reference Plant by ID
            .operator(Operator.justId(operatorId)) // Reference Operator by ID
            .build()

        // Save the ClarifiedWaterEntry to DataStore
        Amplify.DataStore.save(newClarifiedWaterEntry, {
            // Success callback
            Log.d("msg","Successfully saved ClarifiedWaterEntry with ID: ${newClarifiedWaterEntry.id}")
        },
            { error ->
                // Error callback
                Log.d("msg","Failed to save ClarifiedWaterEntry: ${error.message}")
            }
        )
    }
}