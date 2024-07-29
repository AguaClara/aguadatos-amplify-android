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
class CoagConfirmSubmissionFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coag_dosing_confirm_entry, container, false)

        //listener for back button (X)
        view.findViewById<Button>(R.id.x_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_confirm_to_coag_page)
        }
        //listener for confirm entry button
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            //TODO: @POST TEAM FA'24, this is where variables in ViewModel will be sent to backend
            //clear entry values
            val entry = viewModel.coagData.value
            if(entry != null) {
                viewModel.tempCoagData.value = entry.clone()
                entry[0] = 50.0
                for(i in 1..6) {
                    entry[i] = -1.0
                }
            }
            //add time to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText

            //navigate to next page
            findNavController().navigate(R.id.action_coag_confirm_to_coag_view_submission)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This view model contains the coagulant dosing data entry
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Observe the data from ViewModel
        viewModel.coagData.observe(viewLifecycleOwner, Observer { entry ->
            // Update UI based on the received data
            if (entry != null) {
                //format chemical dose and chemical flow rate to 6 decimal places
                val chemDoseText = String.format("%.${6}f", entry[5])
                val chemFlowRateText = String.format("%.${6}f", entry[6])
                //Update all text views to contain the data numbers
                val sliderPosView: TextView = view.findViewById(R.id.slider_pos_info)
                sliderPosView.text = "Slider Position: "+entry[0]+"%"
                val inflowRateView: TextView = view.findViewById(R.id.inflow_rate_info)
                inflowRateView.text = "Inflow Rate: "+entry[1]+" mL/s"
                val svInfoView: TextView = view.findViewById(R.id.sv_info)
                svInfoView.text = "Start Volume: "+entry[2]+" mL"
                val evInfoView: TextView = view.findViewById(R.id.ev_info)
                evInfoView.text = "End Volume: "+entry[3]+" mL"
                val timeElapsedView: TextView = view.findViewById(R.id.time_elapsed_info)
                timeElapsedView.text = "Time Elapsed: "+entry[4]+" s"
                val chemDoseView: TextView = view.findViewById(R.id.chem_dose_info)
                chemDoseView.text = "Chemical Dose: "+chemDoseText+" mg/L"
                val chemFlowRateView: TextView = view.findViewById(R.id.chem_flow_info)
                chemFlowRateView.text = "Chemical Flow Rate: "+chemFlowRateText+" mL/s"
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