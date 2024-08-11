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
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
// CoagConfirmSubmissionFragment.kt
class ChangeDoseConfirmSubmissionFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate layout
        val view = inflater.inflate(R.layout.fragment_change_dose_confirm_entry, container, false)

        // handle logic for back button (X)
        view.findViewById<Button>(R.id.x_button).setOnClickListener {
            findNavController().navigate(R.id.action_change_dose_confirm_entry_to_coag_page)
        }
        // handle logic for confirm entry button
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            //TODO: @POST TEAM FA'24, this is where variables in ViewModel will be sent to backend
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

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        // Observe the change dose data from ViewModel
        viewModel.changeDoseData.observe(viewLifecycleOwner) { entry ->
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
        viewModel.tankVolumes.observe(viewLifecycleOwner) { entry ->
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

}