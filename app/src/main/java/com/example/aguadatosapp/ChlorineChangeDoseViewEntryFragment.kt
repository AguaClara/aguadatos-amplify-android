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

// ChlorineCalibrationViewEntryFragment.kt
class ChlorineChangeDoseViewEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflate layout
        val view = inflater.inflate(R.layout.fragment_chlorine_change_dose_view_entry, container, false)

        // handle logic for return home button
        view.findViewById<Button>(R.id.go_home_button).setOnClickListener {
            findNavController().navigate(R.id.action_change_dose_view_entry_to_home)
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

        // Observe the data from ViewModel
        viewModel.chlorineChangeDoseData.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                //Update all text views to contain the data numbers
                val oldChlorineDoseView: TextView = view.findViewById(R.id.old_chlorine_dose_info)
                oldChlorineDoseView.text = getString(R.string.chlorine_dose_with_input,entry[0])
                val oldFlowRateView: TextView = view.findViewById(R.id.old_chlorine_flow_rate_info)
                oldFlowRateView.text = getString(R.string.chlorine_flow_rate_with_input,entry[1])
                val targetChlorineDoseView: TextView = view.findViewById(R.id.target_flow_rate_info)
                targetChlorineDoseView.text = getString(R.string.target_chlorine_dose_with_input,entry[2])
                val newChlorineDoseView: TextView = view.findViewById(R.id.new_chlorine_flow_info)
                newChlorineDoseView.text = getString(R.string.new_chlorine_flow_rate_with_input,entry[3])
                val newSliderPosView: TextView = view.findViewById(R.id.new_slider_pos_info)
                newSliderPosView.text = getString(R.string.new_slider_pos,entry[4])
            }
        }

        //observe tank volume data from ViewModel
        viewModel.chlorineTankVolumes.observe(viewLifecycleOwner) { entry ->
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