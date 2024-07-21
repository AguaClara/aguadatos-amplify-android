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

// CoagCalibrationFragment.kt
class CoagCalibrationFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    companion object {
        fun newInstance(): CoagCalibrationFragment {
            return CoagCalibrationFragment()
        }
    }

    //helper method to check if a string is a double
    fun isDouble(value: String): Boolean {
        return value.toDoubleOrNull() != null
    }

    //helper method to check if a string is an int
    fun isInt(value: String): Boolean {
        return value.toIntOrNull() != null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel.accessAdjustDosage.value = false
        return inflater.inflate(R.layout.fragment_coag_calibration, container, false)

    }
    //FIXME: only number keyboard
    //FIXME: update all displays to only show a certain number of decimal places?
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val entry: DoubleArray? = viewModel.coagData.value
        if(entry != null) {
            //update accessChangeDose
            viewModel.accessAdjustDosage.value = true
            for(i in 1..4) {
                if (entry[i] < 0.0) {
                    viewModel.accessAdjustDosage.value = false
                }
            }
            // set variables to access each necessary element
            val sliderSeekbar: SeekBar = view.findViewById(R.id.slider_seek_bar)
            if(entry[0] > 0.0 || entry[0] == 0.0) {
                sliderSeekbar.progress = entry[0].toInt()
            }
            val waterInflowRate: EditText = view.findViewById(R.id.water_inflow_rate_input)
            if(entry[1] > 0.0 || entry[1] == 0.0)
                waterInflowRate.setText(entry[1].toString())
            val startVolume: EditText = view.findViewById(R.id.start_volume_input)
            if(entry[2] > 0.0 || entry[2] == 0.0)
                startVolume.setText(entry[2].toString())
            val endVolume: EditText = view.findViewById(R.id.end_volume_input)
            if(entry[3] > 0.0 || entry[3] == 0.0)
                endVolume.setText(entry[3].toString())
            val timeElapsed: EditText = view.findViewById(R.id.time_elapsed_input)
            if(entry[4] > 0.0 || entry[4] == 0.0)
                timeElapsed.setText(entry[4].toString())
            val chemDose: TextView = view.findViewById(R.id.chem_dose_display)
            val chemFlowRate: TextView = view.findViewById(R.id.chem_flow_display)
            //if accessChangeDose is true, display outputs
            if(viewModel.accessAdjustDosage.value == true) {
                chemDose.text = entry[5].toString()
                chemFlowRate.text = entry[6].toString()
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
                        chemDose.text = entry[5].toString()
                        chemFlowRate.text = entry[6].toString()
                        //TODO: change 2 to configuration chemical concentration once it is implemented
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

            // connect seekbar progress with slider position input
            val sliderInput: EditText = view.findViewById(R.id.slider_input)
            // boolean to see which element (input box or seekbar) is being updated by user
            var inputChanged = false

            //add text watcher to watch slider input text
            val textWatcher2 = object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    //do nothing
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    //inputChanged is true because user is editing sliderInput
                    inputChanged = true
                    val sliderPositionText = sliderInput.text.toString()
                    if(sliderPositionText.isNotEmpty() && isInt(sliderPositionText)) {
                        //update seekbar progress
                        sliderSeekbar.progress = sliderPositionText.toInt()
                    }
                    //user has finished editing sliderInput, so inputChanged is false
                    inputChanged = false
                }

                override fun afterTextChanged(p0: Editable?) {
                    //do nothing
                }
            }

            //watch slider input
            sliderInput.addTextChangedListener(textWatcher2)

            //watch to see if seekbar is updated
            sliderSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    //update slider input if seekbar is updated by user
                    if(!inputChanged) {
                        sliderInput.setText(progress.toInt().toString())
                    }
                    // update the first element of the data entry to store slider position
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