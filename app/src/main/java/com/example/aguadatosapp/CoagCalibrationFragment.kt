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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// CoagFragment.kt
class CoagCalibrationFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    //entry contains: slider position, inflow rate, start volume, end volume, time elapsed, chemical dose, and chemical flow rate
    private val entry = mutableListOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0)
    companion object {
        fun newInstance(): CoagCalibrationFragment {
            return CoagCalibrationFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coag_calibration, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // set variables to access each input element
        val sliderSeekbar: SeekBar = view.findViewById(R.id.slider_seek_bar)
        val waterInflowRate: EditText = view.findViewById(R.id.water_inflow_rate_input)
        val startVolume: EditText = view.findViewById(R.id.start_volume_input)
        val endVolume: EditText = view.findViewById(R.id.end_volume_input)
        val timeElapsed: EditText = view.findViewById(R.id.time_elapsed_input)
        //watch input elements to update entry data whenever an input is added
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //get user input, convert to double, and add to entry (for each input)
                val waterInflowRateText = waterInflowRate.text.toString()
                if (waterInflowRateText.isNotEmpty()) {
                    entry[1] = waterInflowRateText.toDouble()
                }

                val startVolumeText = startVolume.text.toString()
                if (startVolumeText.isNotEmpty()) {
                    entry[2] = startVolumeText.toDouble()
                }
                val endVolumeText = endVolume.text.toString()
                if (endVolumeText.isNotEmpty()) {
                    entry[3] = endVolumeText.toDouble()
                }

                val timeElapsedText = timeElapsed.text.toString()
                if (timeElapsedText.isNotEmpty()) {
                    entry[4] = timeElapsedText.toDouble()
                }
                //update viewModel to store new entry data
                viewModel.data.value = entry.toDoubleArray()
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
                viewModel.data.value = entry.toDoubleArray()
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