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
import com.example.aguadatosapp.databinding.FragmentHomeBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// CoagFragment.kt
class CoagConfirmSubmissionFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    // This view model contains the mutable entry array
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
            //FIXME: commit temporary variables to backend and clear values
            findNavController().navigate(R.id.action_coag_confirm_to_coag_view_submission)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //view model stores mutable entry array
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Observe the data from ViewModel
        viewModel.data.observe(viewLifecycleOwner, Observer { entry ->
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
            }
        })

        //add date and time to submission
        viewModel.date.value = SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
        viewModel.time.value = SimpleDateFormat("HH:mm:ss", java.util.Locale.getDefault())

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        val dateVal = viewModel.date.value
        if (dateVal != null) {
            dateView.text = "Date: "+dateVal.format(Date())
        }
    }

}