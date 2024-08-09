package com.example.aguadatosapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

// CoagChangeDoseFragment.kt
class CoagChangeDoseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coag_change_dose, container, false)

    }

    // helper function to determine if a string value is a double
    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        // read view model data into arrays
        val calibrationEntry: DoubleArray? = viewModel.coagData.value
        val changeDoseEntry: DoubleArray? = viewModel.changeDoseData.value
        var targetDose: Double = -1.0

        if(calibrationEntry != null && changeDoseEntry != null) {
            // set variables to access each necessary element
            val targetChemDose: EditText = view.findViewById(R.id.target_chem_dose_input)
            val outputSlider: SeekBar = view.findViewById(R.id.slider_seek_bar3)
            val slider2Display: TextView = view.findViewById(R.id.slider_input2)
            val newCoagFlowDisplay: TextView = view.findViewById(R.id.new_coag_flow_display)

            // output slider is just for display, so user cannot change it
            outputSlider.isEnabled = false

            // read chemical dose and flow rate into changeDoseEntry
            changeDoseEntry[0] = calibrationEntry[5]
            changeDoseEntry[1] = calibrationEntry[6]

            // if there is already date, reflect it in the UI
            if(changeDoseEntry[4] >= 0.0 && changeDoseEntry[2] >= 0.0) {
                newCoagFlowDisplay.text = changeDoseEntry[3].toString()
                outputSlider.progress = changeDoseEntry[4].toInt()
                targetChemDose.setText(changeDoseEntry[1].toString())
                viewModel.changeDoseFilled.value = true
            }

            //watch input elements to update entry data whenever an input is added
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //get user input, convert to double, and add to entry (for each input)
                    val targetChemDoseText = targetChemDose.text.toString()
                    if (targetChemDoseText.isNotEmpty() && isDouble(targetChemDoseText)) {
                        targetDose = targetChemDoseText.toDouble()
                    }
                    // if targetDose is valid, use it to calculate and display output
                    if(targetDose > 0.0 || targetDose == 0.0) {
                        viewModel.changeDoseFilled.value = true
                        changeDoseEntry[2] = targetDose
                        val newSliderPosition = targetDose * calibrationEntry[0] / calibrationEntry[5]
                        changeDoseEntry[3] = targetDose / (viewModel.chemConcentration.value!!)
                        newCoagFlowDisplay.text = changeDoseEntry[3].toString()
                        changeDoseEntry[4] = newSliderPosition
                        outputSlider.progress = newSliderPosition.toInt()
                        slider2Display.text = outputSlider.progress.toString()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // Do nothing
                }
            }

            //watch each user input
            targetChemDose.addTextChangedListener(textWatcher)
        }
    }

}