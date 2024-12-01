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
import com.amplifyframework.datastore.generated.model.ChemicalType
import com.amplifyframework.datastore.generated.model.DoseEntry
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
// CoagChangeDoseConfirmEntryFragment.kt
class CoagChangeDoseConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate layout
        val view = inflater.inflate(R.layout.fragment_coag_change_dose_confirm_entry, container, false)

        // handle logic for back button (X)
        view.findViewById<Button>(R.id.x_button).setOnClickListener {
            findNavController().navigate(R.id.action_change_dose_confirm_entry_to_coag_page)
        }
        // handle logic for confirm entry button
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
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
            viewModel.coagChangeDoseData.value?.let { it1 ->
                createCoagChangeDoseEntry(Temporal.DateTime( Instant.now().toString()),
                    it1[2], it1[3], it1[4], plantId, opId)
            }
            //TODO: need to link to calibration entry?
            //navigate to next page
            findNavController().navigate(R.id.action_change_dose_confirm_entry_to_view_entry)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        // Observe the change dose data from ViewModel
        viewModel.coagChangeDoseData.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                //Update all text views to contain the data numbers
                val oldCoagDoseView: TextView = view.findViewById(R.id.old_coag_dose_info)
                oldCoagDoseView.text = getString(R.string.chemical_dose_with_input,entry[0])
                val oldFlowRateView: TextView = view.findViewById(R.id.old_coag_flow_rate_info)
                oldFlowRateView.text = getString(R.string.chemical_flow_rate_with_input,entry[1])
                val targetCoagDoseView: TextView = view.findViewById(R.id.target_flow_rate_info)
                targetCoagDoseView.text = getString(R.string.target_coag_dose,entry[2])
                val newCoagFlowView: TextView = view.findViewById(R.id.new_coag_flow_info)
                newCoagFlowView.text = getString(R.string.new_coag_flow_rate,entry[3])
                val newSliderPosView: TextView = view.findViewById(R.id.new_slider_pos_info)
                newSliderPosView.text = getString(R.string.new_slider_pos,entry[4])
            }
        }

        //observe tank volume data from ViewModel
        viewModel.coagTankVolumes.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                var activeTank = 1
                var activeTankVol = entry[0]
                if(entry[1] > 0.0) {
                    activeTank = 2
                    activeTankVol = entry[1]
                }
                //Update all text views to contain the data numbers
                val activeTankView: TextView = view.findViewById(R.id.active_tank_info)
                activeTankView.text = getString(R.string.active_tank_with_input,activeTank)
                val activeTankVolView: TextView = view.findViewById(R.id.tank_vol_info)
                activeTankVolView.text = getString(R.string.active_tank_volume,activeTankVol)
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
    private fun createCoagChangeDoseEntry(createdAt: Temporal.DateTime, targetDose: Double, sliderPos: Double,
                                           flowRate: Double, plantId: String, operatorId: String) {
        // Create a doseEntry instance
        val newDoseEntry = DoseEntry.builder()
            .createdAt(createdAt) // Temporal.DateTime object
            .chemicalType(ChemicalType.COAGULANT) //ChemicalType object
            .targetDose(targetDose) // Double value
            .updatedSliderPosition(sliderPos) // Double value
            .updatedFlowRate(flowRate) // Double value
            .plant(Plant.justId(plantId)) // Reference Plant by ID
            .operator(Operator.justId(operatorId)) // Reference Operator by ID
            .build()

        // Save the DoseEntry to DataStore
        Amplify.DataStore.save(newDoseEntry, {
            // Success callback
            Log.d("msg","Successfully saved DoseEntry with ID: ${newDoseEntry.id}")
        },
            { error ->
                // Error callback
                Log.d("msg","Failed to save DoseEntry: ${error.message}")
            }
        )
    }
}