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
//FIXME: add string resources for concatenated strings
// CoagViewSubmissionFragment.kt
class CoagViewSubmissionFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //inflaate layout
        val view = inflater.inflate(R.layout.fragment_coag_dosage_submission, container, false)

        //listener for return home button
        view.findViewById<Button>(R.id.go_home_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_view_to_home)
        }

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // This view model contains the coagulant dosing data entry
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Observe the data from ViewModel
        viewModel.tempCoagData.observe(viewLifecycleOwner, Observer { entry ->
            // Update UI based on the received data
            if (entry != null) {
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
                chemDoseView.text = "Chemical Dose: "+entry[5]+" mg/L"
                val chemFlowRateView: TextView = view.findViewById(R.id.chem_flow_info)
                chemFlowRateView.text = "Chemical Flow Rate: "+entry[6]+" mL/s"
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