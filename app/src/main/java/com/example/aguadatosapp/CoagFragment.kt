package com.example.aguadatosapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// CoagFragment.kt
class CoagFragment : Fragment() {
    //var to store which embedded fragment is showing
    private var showingCalibrationFragment = true
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coag_dosing, container, false)

        //listener for back button (<)
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_page_to_home)
        }
        //listener for submit button
        view.findViewById<Button>(R.id.submit_button).setOnClickListener {
            if(viewModel.accessAdjustDosage.value == true) {
                findNavController().navigate(R.id.action_coag_page_to_coag_confirm_entry)
            }
            else {
                Toast.makeText(context,"Please ensure all inputs are filled before submitting.",Toast.LENGTH_SHORT).show()
            }
        }

        // Load the initial fragment
        if (savedInstanceState == null) {
            childFragmentManager.commit {
                replace(R.id.fragmentContainer, CoagCalibrationFragment())
            }
        }
        return view
    }

    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        val calibrationNavButton: androidx.appcompat.widget.AppCompatButton =
            view.findViewById(R.id.calibration_nav_button)
        val changeDoseNavButton: androidx.appcompat.widget.AppCompatButton =
            view.findViewById(R.id.change_dose_nav_button)
        val calibrationHorizLine: View = view.findViewById(R.id.horiz_line_calibration)
        val changeDoseHorizLine: View = view.findViewById(R.id.horiz_line_change_dose)
        val lightGrayColor = ContextCompat.getColor(requireContext(), R.color.light_gray)

        //swap embedded fragment to change dose
        changeDoseNavButton.setOnClickListener {
            if (viewModel.accessAdjustDosage.value == true) {
                if (showingCalibrationFragment) {
                    //update button appearances
                    changeDoseNavButton.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.change_dose_primary_background
                    )
                    calibrationNavButton.background = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.change_dose_secondary_background
                    )
                    changeDoseHorizLine.setBackgroundColor(Color.TRANSPARENT)
                    calibrationHorizLine.setBackgroundColor(lightGrayColor)
                    //swap fragment
                    childFragmentManager.commit {
                        replace(R.id.fragmentContainer, CoagChangeDoseFragment())
                    }
                    //update boolean
                    showingCalibrationFragment = false
                }
            } else {
                Toast.makeText(
                    context,
                    "Please ensure all inputs are filled before changing the dosage.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        //swap embedded fragment to calibration
        calibrationNavButton.setOnClickListener {
            if (!showingCalibrationFragment) {
                //update button appearances
                calibrationNavButton.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.calib_primary_background)
                changeDoseNavButton.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.change_dose_secondary_background
                )
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

        //handle tank volumes
        val tankSwitch: SwitchCompat = view.findViewById(R.id.tank_switch)
        val text1: TextView = view.findViewById(R.id.vol_tank1_text)
        val input1: EditText = view.findViewById(R.id.vol_tank1_input)
        val unit1: TextView = view.findViewById(R.id.l1)
        val text2: TextView = view.findViewById(R.id.vol_tank2_text)
        val input2: EditText = view.findViewById(R.id.vol_tank2_input)
        val unit2: TextView = view.findViewById(R.id.l2)

        //active tank is 1 to begin with
        text1.setTextColor(Color.BLACK)
        unit1.setTextColor(Color.BLACK)
        input1.setTextColor(Color.BLACK)
        input1.isEnabled = true
        input1.setText("")
        text2.setTextColor(Color.GRAY)
        unit2.setTextColor(Color.GRAY)
        input2.setTextColor(Color.GRAY)
        input2.isEnabled = false
        input2.setText("0.0")

        //update if switch is changed
        tankSwitch.setOnClickListener() {
            if (tankSwitch.isChecked()) {
                //active tank is 2
                text2.setTextColor(Color.BLACK)
                unit2.setTextColor(Color.BLACK)
                input2.setTextColor(Color.BLACK)
                input2.isEnabled = true
                input2.setText("")
                text1.setTextColor(Color.GRAY)
                unit1.setTextColor(Color.GRAY)
                input1.setTextColor(Color.GRAY)
                input1.isEnabled = false
                input1.setText("0.0")
            } else {
                //active tank is 1
                text1.setTextColor(Color.BLACK)
                unit1.setTextColor(Color.BLACK)
                input1.setTextColor(Color.BLACK)
                input1.isEnabled = true
                input1.setText("")
                text2.setTextColor(Color.GRAY)
                unit2.setTextColor(Color.GRAY)
                input2.setTextColor(Color.GRAY)
                input2.isEnabled = false
                input2.setText("0.0")
            }
        }
        //get active tank volumes from EditTexts
        val volumesEntry = viewModel.tankVolumes.value
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(volumesEntry != null && isDouble(input1.text.toString()) && isDouble(input2.text.toString())) {
                    volumesEntry[0] = input1.text.toString().toDouble()
                    volumesEntry[1] = input2.text.toString().toDouble()
                    unit1.setText(volumesEntry[0].toString()+", "+volumesEntry[1].toString())
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        }

        //add text changed listeners to each input field
        input1.addTextChangedListener(textWatcher)
        input2.addTextChangedListener(textWatcher)
    }
}