package com.example.aguadatosapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// CoagFragment.kt
class CoagCalibrationFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    companion object {
        fun newInstance(): CoagCalibrationFragment {
            return CoagCalibrationFragment()
        }
    }

    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        viewModel.accessAdjustDosage.value = false
        return inflater.inflate(R.layout.fragment_coag_calibration, container, false)

    }
    //FIXME: truncate output values to 2 decimal places
    //FIXME: add input to change seekbar position manually
    //FIXME: only number keyboard
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val entry: DoubleArray? = viewModel.coagData.value
        if(entry != null) {
            //update accessChangeDose
            viewModel.accessAdjustDosage.value = true
            for(i in 1..4) {
                if (entry[i] < 0.0) {
                    viewModel.accessAdjustDosage.value = false
                }
            }
            // set variables to access each input element
            val sliderSeekbar: SeekBar = view.findViewById(R.id.slider_seek_bar)
            if(entry[0] > 0.0 || entry[0] == 0.0) {
                sliderSeekbar.progress = entry[0].toInt()
            }
            val waterInflowRate: EditText = view.findViewById(R.id.water_inflow_rate_input)
            if(entry[1] > 0.0 || entry[1] == 0.0)
                waterInflowRate.setText(""+entry[1])
            val startVolume: EditText = view.findViewById(R.id.start_volume_input)
            if(entry[2] > 0.0 || entry[2] == 0.0)
                startVolume.setText(""+entry[2])
            val endVolume: EditText = view.findViewById(R.id.end_volume_input)
            if(entry[3] > 0.0 || entry[3] == 0.0)
                endVolume.setText(""+entry[3])
            val timeElapsed: EditText = view.findViewById(R.id.time_elapsed_input)
            if(entry[4] > 0.0 || entry[4] == 0.0)
                timeElapsed.setText(""+entry[4])
            val chemDose: TextView = view.findViewById(R.id.chem_dose_display)
            val chemFlowRate: TextView = view.findViewById(R.id.chem_flow_display)
            //if accessChangeDose is true, display outputs
            if(viewModel.accessAdjustDosage.value == true) {
                chemDose.text = ""+entry[5]
                chemFlowRate.text = ""+entry[6]
            }
            //watch input elements to update entry data whenever an input is added
            val textWatcher = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //get user input, convert to double, and add to entry (for each input)
                    val waterInflowRateText = waterInflowRate.text.toString()
                    if (waterInflowRateText.isNotEmpty() && isDouble(waterInflowRateText)) {
                        entry[1] = waterInflowRateText.toDouble()
                    }

                    val startVolumeText = startVolume.text.toString()
                    if (startVolumeText.isNotEmpty() && isDouble(startVolumeText)) {
                        entry[2] = startVolumeText.toDouble()
                    }
                    val endVolumeText = endVolume.text.toString()
                    if (endVolumeText.isNotEmpty() && isDouble(endVolumeText)) {
                        entry[3] = endVolumeText.toDouble()
                    }

                    val timeElapsedText = timeElapsed.text.toString()
                    if (timeElapsedText.isNotEmpty() && isDouble(timeElapsedText)) {
                        entry[4] = timeElapsedText.toDouble()
                    }
                    //check if accessAdjustDosage has changed
                    viewModel.accessAdjustDosage.value = true
                    for(i in 1..4) {
                        if (entry[i] < 0.0) {
                            viewModel.accessAdjustDosage.value = false
                        }
                    }
                    if (viewModel.accessAdjustDosage.value == true) {
                        entry[6] = (entry[2] - entry[3]) / entry[4]
                        entry[5] = entry[6] * 2
                        chemDose.text = ""+entry[5]
                        chemFlowRate.text = ""+entry[6]
                        //FIXME: change 2 to configuration chemical concentration once it is implemented
                    }

                    //update viewModel to store new entry data
                    viewModel.coagData.value = entry
                }

                override fun afterTextChanged(s: Editable?) {
                    // Do nothing
                }
            }
            //watch each user input
            waterInflowRate.addTextChangedListener(textWatcher)
            startVolume.addTextChangedListener(textWatcher)
            endVolume.addTextChangedListener(textWatcher)
            timeElapsed.addTextChangedListener(textWatcher)

            //watch to see if seekbar is updated
            sliderSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    // Update the first element of the array to store seekbar progress
                    entry[0] = progress.toDouble()
                    //update viewModel
                    viewModel.coagData.value = entry
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