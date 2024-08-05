package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
// CoagConfirmSubmissionFragment.kt
class ChangeDoseConfirmSubmissionFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_change_dose_confirm_entry, container, false)

        //listener for back button (X)
        view.findViewById<Button>(R.id.x_button).setOnClickListener {
            findNavController().navigate(R.id.action_change_dose_confirm_entry_to_coag_page)
        }
        //listener for confirm entry button
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            //TODO: @POST TEAM FA'24, this is where variables in ViewModel will be sent to backend
            //clear entry values
            val entry = viewModel.changeDoseData.value
            if(entry != null) {
                viewModel.tempChangeDoseData.value = entry.clone()
                for(i in 0..4) {
                    entry[i] = -1.0
                }
            }
            //add time to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText

            //navigate to next page
            findNavController().navigate(R.id.action_change_dose_confirm_entry_to_view_entry)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This view model contains the coagulant dosing data entry
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Observe the change dose data from ViewModel
        viewModel.changeDoseData.observe(viewLifecycleOwner, Observer { entry ->
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

        //add date to submission
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateText = LocalDate.now().format(dateFormatter)
        viewModel.date.value = dateText

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        dateView.text = "Date: "+dateText
    }

}