package com.example.aguadatosapp

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController

// CoagFragment.kt
class CoagFragment : Fragment() {
    //var to store which embedded fragment is showing
    private var showingCalibrationFragment = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coag_dosing, container, false)

        //listener for back button (<)
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigateUp()
        }
        //listener for submit button
        view.findViewById<Button>(R.id.submit_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_page_to_coag_confirm_entry)
        }

        // Load the initial fragment
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                replace(R.id.fragmentContainer, CoagCalibrationFragment())
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calibrationNavButton: androidx.appcompat.widget.AppCompatButton = view.findViewById(R.id.calibration_nav_button)
        val changeDoseNavButton: androidx.appcompat.widget.AppCompatButton = view.findViewById(R.id.change_dose_nav_button)
        val calibrationHorizLine: View = view.findViewById(R.id.horiz_line_calibration)
        val changeDoseHorizLine: View = view.findViewById(R.id.horiz_line_change_dose)
        val lightGrayColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

        //swap embedded fragment to change dose
        changeDoseNavButton.setOnClickListener {
            if(showingCalibrationFragment) {
                //update button appearances
                changeDoseNavButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.change_dose_primary_background)
                calibrationNavButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.change_dose_secondary_background)
                changeDoseHorizLine.setBackgroundColor(Color.TRANSPARENT)
                calibrationHorizLine.setBackgroundColor(lightGrayColor)
                //swap fragment
                childFragmentManager.commit {
                    replace(R.id.fragmentContainer, CoagChangeDoseFragment())
                }
                //update boolean
                showingCalibrationFragment = false
            }
        }

        //swap embedded fragment to calibration
        calibrationNavButton.setOnClickListener {
            if(!showingCalibrationFragment) {
                //update button appearances
                calibrationNavButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.calib_primary_background)
                changeDoseNavButton.background = ContextCompat.getDrawable(requireContext(), R.drawable.change_dose_secondary_background)
                calibrationHorizLine.setBackgroundColor(Color.TRANSPARENT)
                changeDoseHorizLine.setBackgroundColor(lightGrayColor)
                //swap fragment
                childFragmentManager.commit {
                    replace(R.id.fragmentContainer, CoagCalibrationFragment())
                }
                //update boolean
                showingCalibrationFragment = true
            }
        }
    }
}