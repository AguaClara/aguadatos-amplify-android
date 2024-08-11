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

// CoagCalibrationViewEntryFragment.kt
class CoagCalibrationViewEntryFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout
        val view = inflater.inflate(R.layout.fragment_coag_calibration_view_entry, container, false)

        // handle logic for return home button
        view.findViewById<Button>(R.id.go_home_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_view_to_home)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This view model contains the coagulant dosing data entry
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        // Observe the data from ViewModel
        viewModel.coagData.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                //Update all text views to contain the data numbers
                val sliderPosView: TextView = view.findViewById(R.id.slider_pos_info)
                sliderPosView.text = getString(R.string.slider_position_with_input, entry[0])
                val inflowRateView: TextView = view.findViewById(R.id.inflow_rate_info)
                inflowRateView.text = getString(R.string.inflow_rate_with_input2, entry[1])
                val svInfoView: TextView = view.findViewById(R.id.sv_info)
                svInfoView.text = getString(R.string.start_volume_with_input, entry[2])
                val evInfoView: TextView = view.findViewById(R.id.ev_info)
                evInfoView.text = getString(R.string.end_volume_with_input, entry[3])
                val timeElapsedView: TextView = view.findViewById(R.id.time_elapsed_info)
                timeElapsedView.text = getString(R.string.time_elapsed_with_input, entry[4])
                val chemDoseView: TextView = view.findViewById(R.id.chem_dose_info)
                chemDoseView.text = getString(R.string.chemical_dose_with_input, entry[5])
                val chemFlowRateView: TextView = view.findViewById(R.id.chem_flow_info)
                chemFlowRateView.text = getString(R.string.chemical_flow_rate_with_input, entry[6])
            }
        }

        //observe tank volume data from ViewModel
        viewModel.tankVolumes.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                var activeTank = 1
                var activeTankVol = entry[0]
                if (entry[1] > 0.0) {
                    activeTank = 2
                    activeTankVol = entry[1]
                }
                //Update all text views to contain the data numbers
                val activeTankView: TextView = view.findViewById(R.id.active_tank_info)
                activeTankView.text = getString(R.string.active_tank_with_input, activeTank)
                val activeTankVolView: TextView = view.findViewById(R.id.tank_vol_info)
                activeTankVolView.text = getString(R.string.active_tank_volume, activeTankVol)
            }
        }

        //display date at the top
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = getString(R.string.date,dateVal)
        }
        //display submission date and time at the bottom
        val dateTimeView: TextView = view.findViewById(R.id.submit_date_time)
        val timeVal = viewModel.time.value
        if (timeVal != null && dateVal != null) {
            dateTimeView.text = getString(R.string.date_and_time,dateVal,timeVal)
        }
    }
}