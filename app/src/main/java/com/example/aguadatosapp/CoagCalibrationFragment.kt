package com.example.aguadatosapp

import android.graphics.Color
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

// CoagCalibrationFragment.kt
class CoagCalibrationFragment : Fragment() {
    // This view model contains the coagulant dosing data entry
    private lateinit var viewModel: SharedViewModel
    // set variables to access each necessary element
    private var countdownTimer: CountDownTimer? = null
    private lateinit var minutesView: EditText
    private lateinit var secondsView: EditText
    private lateinit var startButton: Button
    private lateinit var resetButton: Button
    private lateinit var endVolume: EditText
    private lateinit var endVolumeText: TextView
    private lateinit var mlText: TextView
    private var timeElapsed = 0
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

    //start timer countdown
    private fun startCountdown(timeInSeconds: Long) {
        countdownTimer?.cancel()

        countdownTimer = object : CountDownTimer(timeInSeconds * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                val minutesLeft = secondsLeft / 60
                val modSecondsLeft = secondsLeft % 60
                minutesView.setText(minutesLeft.toString())
                secondsView.setText(String.format("%02d", modSecondsLeft))
            }

            override fun onFinish() {
                endVolume.isEnabled = true
                endVolumeText.setTextColor(Color.BLACK)
                mlText.setTextColor(Color.BLACK)
                resetTimer()
            }
        }.start()
    }

    //reset timer countdown
    private fun resetTimer() {
        minutesView.isEnabled = true
        secondsView.isEnabled = true
        startButton.isEnabled = true
        timeElapsed = 0
        minutesView.setText("")
        secondsView.setText("")
        startButton.background = ContextCompat.getDrawable(requireContext(),R.drawable.play_button)
        resetButton.background = ContextCompat.getDrawable(requireContext(),R.drawable.gray_reset_button)
        countdownTimer?.cancel()
        countdownTimer = null
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
            endVolume = view.findViewById(R.id.end_volume_input)
            if(entry[3] > 0.0 || entry[3] == 0.0)
                endVolume.setText(entry[3].toString())
            val chemDose: TextView = view.findViewById(R.id.chem_dose_display)
            val chemFlowRate: TextView = view.findViewById(R.id.chem_flow_display)
            //if accessChangeDose is true, display outputs
            if(viewModel.accessAdjustDosage.value == true) {
                chemDose.text = entry[5].toString()
                chemFlowRate.text = entry[6].toString()
            }

            //timer functionality

            // set variables to access each necessary element
            minutesView = view.findViewById(R.id.timer_minutes)
            secondsView = view.findViewById(R.id.timer_seconds)
            startButton = view.findViewById(R.id.play_button)
            resetButton = view.findViewById(R.id.reset_button)
            endVolumeText = view.findViewById(R.id.end_volume_text)
            mlText = view.findViewById(R.id.ev_ml_text)
            timeElapsed = 0

            //this ensures endVolume is enabled as long as timer has run once
            if(viewModel.accessAdjustDosage.value==true) {
                endVolumeText.setTextColor(Color.BLACK)
                mlText.setTextColor(Color.BLACK)
                endVolume.isEnabled = true
            }

            //handle start button logic
            startButton.setOnClickListener() {
                val minutesText = minutesView.text.toString()
                val secondsText = secondsView.text.toString()
                //ensure input is valid, calculate time elapsed in seconds
                if(minutesText.isNotEmpty() && secondsText.isNotEmpty()) {
                    timeElapsed += minutesText.toInt() * 60
                    timeElapsed += secondsText.toInt()
                }
                //ensure values have been entered
                if(timeElapsed > 0) {
                    entry[4] = timeElapsed.toDouble()
                    minutesView.isEnabled = false
                    secondsView.isEnabled = false
                    startButton.isEnabled = false
                    startCountdown(timeElapsed.toLong())
                    startButton.background = ContextCompat.getDrawable(requireContext(),R.drawable.gray_play_button)
                    resetButton.background = ContextCompat.getDrawable(requireContext(),R.drawable.reset_button)
                }
                //otherwise, pop a toast telling user to set timer
                else {
                    Toast.makeText(context,"Please set the timer before starting it.", Toast.LENGTH_SHORT).show()
                }
            }

            //handle reset button logic
            resetButton.setOnClickListener() {
                resetTimer()
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
                        chemDose.text = String.format("%.${6}f", entry[5])
                        chemFlowRate.text = String.format("%.${6}f", entry[6])
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

            // connect seekbar progress with slider position input
            val sliderInput: EditText = view.findViewById(R.id.slider_input)
            if(entry[0] > 0 || entry[0] == 0.0) {
                sliderInput.setText(entry[0].toInt().toString())
            }
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