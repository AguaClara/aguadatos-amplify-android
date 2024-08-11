package com.example.aguadatosapp

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.time.LocalDateTime
import java.time.Duration
import java.time.format.DateTimeFormatter
import java.util.Locale

// CoagFragment.kt
class CoagFragment : Fragment() {
    // var to store which embedded fragment is showing
    private var showingCalibrationFragment = true
    // record volume of each tank
    private var tank1vol = 0.0
    private var tank2vol = 0.0
    // message to show when tank will run out of coagulant
    private lateinit var tankRunOutMessageView: TextView

    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_coag_dosing, container, false)

        // handle logic for back button (<)
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_page_to_home)
        }
        // handle logic for submit button
        view.findViewById<Button>(R.id.submit_button).setOnClickListener {
            if(viewModel.accessAdjustDosage.value == true) {
                if(showingCalibrationFragment) {
                    // proceed to calibration data submission
                    findNavController().navigate(R.id.action_coag_page_to_coag_confirm_entry)
                }
                else if(viewModel.changeDoseFilled.value == true) {
                    // proceed to change dose data submission
                    findNavController().navigate(R.id.action_coag_page_to_change_dose_confirm_entry)
                }
                else {
                    //pop error message
                    Toast.makeText(context,"Please ensure all inputs are filled before submitting.",Toast.LENGTH_SHORT).show()
                }
            }
            else {
                //pop error message
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

    //helper function to determine if a string value is a decimal number
    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }

    //helper function to calculate when the active tank will run out of coagulant
    private fun calculateRunOutTime() {
        val coagFlowRate = viewModel.coagData.value?.get(6)
        val currentDateTime = LocalDateTime.now()
        var activeTankVolume = -1.0
        if(coagFlowRate != null && coagFlowRate > -1) {
            // only calculate if given flow rate . . .
            if(tank1vol > 0.0) {
                activeTankVolume = tank1vol
            }
            else if(tank2vol > 0.0) {
                activeTankVolume = tank2vol
            }
            if(activeTankVolume > -1.0) {
                // . . . and tank volume
                val secondsToRunOut = ((activeTankVolume * 1000) / coagFlowRate).toLong()
                val newDateTime = currentDateTime.plus(Duration.ofSeconds(secondsToRunOut))
                val formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd 'at' HH:mm:ss", Locale.ENGLISH)
                val formattedDateTime = newDateTime.format(formatter)
                viewModel.coagulantRunOutTime.value = formattedDateTime
                showRunOutMessage(formattedDateTime)
            }
        }
    }

    //helper function to display run out message
    private fun showRunOutMessage(dateTimeString: String) {
        tankRunOutMessageView.text = getString(R.string.coag_run_out_message,dateTimeString)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // set variables to access each necessary UI element
        val calibrationNavButton: androidx.appcompat.widget.AppCompatButton =
            view.findViewById(R.id.calibration_nav_button)
        val changeDoseNavButton: androidx.appcompat.widget.AppCompatButton =
            view.findViewById(R.id.change_dose_nav_button)
        val calibrationHorizLine: View = view.findViewById(R.id.horiz_line_calibration)
        val changeDoseHorizLine: View = view.findViewById(R.id.horiz_line_change_dose)
        val lightGrayColor = ContextCompat.getColor(requireContext(), R.color.light_gray)
        tankRunOutMessageView = view.findViewById(R.id.tank_run_out_message)
        val chemTypeView: TextView = view.findViewById(R.id.chemical_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)

        //if run out time has been calculated, display run out message
        if(viewModel.coagulantRunOutTime.value != null) {
            showRunOutMessage(viewModel.coagulantRunOutTime.value!!)
        }
        else {
            tankRunOutMessageView.text = ""
        }

        // handle logic for change dose button
        changeDoseNavButton.setOnClickListener {
            if (viewModel.accessAdjustDosage.value == true) {
                // only swap if on calibration page
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
                //pop error message
                Toast.makeText(
                    context,
                    "Please ensure all inputs are filled before changing the dosage.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        // handle logic for calibration button
        calibrationNavButton.setOnClickListener {
            // only swap if on change dose page
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
        // set variables to access each necessary UI element
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
        tankSwitch.setOnClickListener {
            if (tankSwitch.isChecked) {
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
                //ensure inputs are non-null decimals, if so read into ViewModel
                if(volumesEntry != null && isDouble(input1.text.toString()) && isDouble(input2.text.toString())) {
                    volumesEntry[0] = input1.text.toString().toDouble()
                    volumesEntry[1] = input2.text.toString().toDouble()
                    viewModel.tankVolumes.value = volumesEntry
                    tank1vol = volumesEntry[0]
                    tank2vol = volumesEntry[1]
                    calculateRunOutTime()
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        }

        //add text changed listeners to each input field
        input1.addTextChangedListener(textWatcher)
        input2.addTextChangedListener(textWatcher)

        //if coagData (calibration page inputs) are changed, try to calculate run out time
        viewModel.coagData.observe(viewLifecycleOwner) {
            calculateRunOutTime()
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentNavigation", "CoagFragment is now visible")
    }
}