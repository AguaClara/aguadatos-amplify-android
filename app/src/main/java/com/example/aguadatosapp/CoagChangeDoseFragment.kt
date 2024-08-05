package com.example.aguadatosapp

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
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// CoagChangeDoseFragment.kt
class CoagChangeDoseFragment : Fragment() {
    companion object {
        fun newInstance(): CoagChangeDoseFragment {
            return CoagChangeDoseFragment()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coag_change_dose, container, false)

    }
    //helper function to determine if a string value is a double
    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }
    //helper function to determine if a string value is an int
    fun isInt(value: String): Boolean {
        return value.toIntOrNull() != null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This view model contains the coagulant dosing data entries
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val calibrationEntry: DoubleArray? = viewModel.coagData.value
        val changeDoseEntry: DoubleArray? = viewModel.changeDoseData.value
        var targetDose: Double = -1.0

        if(calibrationEntry != null && changeDoseEntry != null) {
            // set variables to access each necessary element
            val chemFlow: TextView = view.findViewById(R.id.chem_flow_display2)
            val chemDose: TextView = view.findViewById(R.id.chem_dose_display2)
            val inputSlider: SeekBar = view.findViewById(R.id.slider_seek_bar2)
            val targetChemDose: EditText = view.findViewById(R.id.target_chem_dose_input)
            val outputSlider: SeekBar = view.findViewById(R.id.slider_seek_bar3)
            val slider1Display: TextView = view.findViewById(R.id.slider_input)
            val slider2Display: TextView = view.findViewById(R.id.slider_input2)

            inputSlider.isEnabled = false
            outputSlider.isEnabled = false

            changeDoseEntry[0] = calibrationEntry[5]
            changeDoseEntry[1] = calibrationEntry[6]
            chemDose.text = String.format("%.${5}f", calibrationEntry[5])
            chemFlow.text = String.format("%.${5}f", calibrationEntry[6])
            inputSlider.progress = calibrationEntry[0].toInt()
            slider1Display.text = inputSlider.progress.toString()

//TODO: CHECK ON RUN OUT DATE, SEEMS SUS

            if(changeDoseEntry != null) {
                // set variables to access each necessary element
                val newSliderView: SeekBar = view.findViewById(R.id.slider_seek_bar3)
                val targetChemDoseView: EditText = view.findViewById(R.id.target_chem_dose_input)
                if(changeDoseEntry[4] >= 0.0 && changeDoseEntry[2] >= 0.0) {
                    newSliderView.progress = changeDoseEntry[4].toInt()
                    targetChemDoseView.setText(changeDoseEntry[1].toString())
                    viewModel.changeDoseFilled.value = true
                }
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
                    if(targetDose > 0.0 || targetDose == 0.0) {
                        viewModel.changeDoseFilled.value = true
                        changeDoseEntry[2] = targetDose
                        val newSliderPosition = targetDose * calibrationEntry[0] / calibrationEntry[5]
                        changeDoseEntry[3] = targetDose / 2
                        //FIXME: replace 2 with configuration chemical concentration
                        changeDoseEntry[4] = newSliderPosition
                        outputSlider.progress = newSliderPosition.toInt()
                        //update outputSlider's slider position display
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