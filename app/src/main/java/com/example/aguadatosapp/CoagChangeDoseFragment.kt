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

// CoagFragment.kt
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

    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // This view model contains the coagulant dosing data entry
        val viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val entry: DoubleArray? = viewModel.coagData.value
        var targetDose: Double = -1.0

        if(entry != null) {
            //access layout elements we need
            val chemFlow: TextView = view.findViewById(R.id.chem_flow_display2)
            val chemDose: TextView = view.findViewById(R.id.chem_dose_display2)
            val inputSlider: SeekBar = view.findViewById(R.id.slider_seek_bar2)
            val targetChemDose: EditText = view.findViewById(R.id.target_chem_dose_input)
            val outputSlider: SeekBar = view.findViewById(R.id.slider_seek_bar3)

            inputSlider.isEnabled = false
            outputSlider.isEnabled = false

            chemDose.text = ""+entry[5]
            chemFlow.text = ""+entry[6]
            inputSlider.progress = entry[0].toInt()


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
                        val newSliderPosition = targetDose * entry[0] / entry[5]
                        outputSlider.progress = newSliderPosition.toInt()
                    }
                }

                override fun afterTextChanged(s: Editable?) {
                    // Do nothing
                }
            }
            //FIXME: generally, implement try-catch logic for inputs. only attempt to convert to a number if it is a number
            //watch each user input
            targetChemDose.addTextChangedListener(textWatcher)

            //watch to see if seekbar is updated
            inputSlider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // Update the first element of the array to store seekbar progress
                    entry[0] = progress.toDouble()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    // Do nothing
                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {
                    // Do nothing
                }
            })
        }
    }

}