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
import java.util.Date
// CoagViewSubmissionFragment.kt
class ChangeDoseViewSubmissionFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflate layout
        val view = inflater.inflate(R.layout.fragment_change_dose_view_submission, container, false)

        //listener for return home button
        view.findViewById<Button>(R.id.go_home_button).setOnClickListener {
            findNavController().navigate(R.id.action_change_dose_view_entry_to_home)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This view model contains the coagulant dosing data entry
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        // Observe the data from ViewModel
        viewModel.tempChangeDoseData.observe(viewLifecycleOwner, Observer { entry ->
            // Update UI based on the received data
            if (entry != null) {
                //Update all text views to contain the data numbers
                val oldCoagDoseView: TextView = view.findViewById(R.id.old_coag_dose_info)
                oldCoagDoseView.text = "Coagulant Dosage: "+String.format("%.${6}f", entry[0])+" mg/L"
                val oldFlowRateView: TextView = view.findViewById(R.id.old_coag_flow_rate_info)
                oldFlowRateView.text = "Coagulant Flow Rate: "+String.format("%.${6}f", entry[1])+" mL/s"
                val targetCoagDoseView: TextView = view.findViewById(R.id.target_flow_rate_info)
                targetCoagDoseView.text = "Target Coagulant Dosage: "+entry[2]+" mL/s"
                val newCoagDoseView: TextView = view.findViewById(R.id.new_coag_flow_info)
                newCoagDoseView.text = "New Coagulant Flow Rate: "+String.format("%.${6}f", entry[3])+" mg/L"
                val newSliderPosView: TextView = view.findViewById(R.id.new_slider_pos_info)
                newSliderPosView.text = "New Slider Position: "+entry[4]+"%"
            }
        })

        //observe tank volume data from ViewModel
        viewModel.tankVolumes.observe(viewLifecycleOwner, Observer { entry ->
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
                activeTankView.text = "Active Tank: "+activeTank
                val activeTankVolView: TextView = view.findViewById(R.id.tank_vol_info)
                activeTankVolView.text = "Active Tank Volume: "+activeTankVol+" L"
            }
        })

        //display date at the top
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = "Date: "+dateVal
        }
        //display submission date and time at the bottom
        val dateTimeView: TextView = view.findViewById(R.id.submit_date_time)
        val timeVal = viewModel.time.value
        if (timeVal != null && dateVal != null) {
            dateTimeView.text = dateVal+" at "+timeVal
        }
    }
}