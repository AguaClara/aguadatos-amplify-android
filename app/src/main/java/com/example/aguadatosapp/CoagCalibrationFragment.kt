package com.example.aguadatosapp

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import java.util.Locale

// CoagCalibrationFragment.kt
class CoagCalibrationFragment : Fragment() {
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
    // temporary variable used in timer functionality
    private var timeElapsed = 0

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
                // update timer display every second
                val secondsLeft = millisUntilFinished / 1000
                val minutesLeft = secondsLeft / 60
                val modSecondsLeft = secondsLeft % 60
                minutesView.setText(minutesLeft.toString())
                secondsView.setText(String.format(Locale.US,"%02d", modSecondsLeft))
                minutesView.setText(String.format(Locale.US,"%02d",minutesLeft))
            }

            override fun onFinish() {
                //TODO: Ensure sound is working properly
                //Initialize MediaPlayer, play sound
                val mediaPlayer = MediaPlayer.create(context, R.raw.beep)
                mediaPlayer.start()
                // allow user to access endVolume field
                endVolume.isEnabled = true
                endVolume.setTextColor(Color.BLACK)
                endVolumeText.setTextColor(Color.BLACK)
                mlText.setTextColor(Color.BLACK)
                // reset timer, timer display
                resetTimer()
            }
        }.start()
    }

    //reset timer countdown
    private fun resetTimer() {
        minutesView.isEnabled = true
        secondsView.isEnabled = true
        startButton.isEnabled = true
        //timeElapsed = 0
        val mins = timeElapsed / 60
        val secs = timeElapsed % 60
        minutesView.setText(String.format(Locale.US,"%02d", mins))
        secondsView.setText(String.format(Locale.US,"%02d",secs))
        startButton.background = ContextCompat.getDrawable(requireContext(),R.drawable.play_button)
        resetButton.background = ContextCompat.getDrawable(requireContext(),R.drawable.gray_reset_button)
        countdownTimer?.cancel()
        countdownTimer = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModel.coagAccessAdjustDosage.value = false
        viewModel.coagChangeDoseFilled.value = false

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coag_calibration, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val entry = viewModel.coagCalibrationData.value

        if(entry != null) {
            //update accessChangeDose
            viewModel.coagAccessAdjustDosage.value = true
            for(i in 1..4) {
                if (entry[i] < 0.0) {
                    viewModel.coagAccessAdjustDosage.value = false
                }
            }
            // set variables to access each necessary element
            // if data is already entered, display on UI
            val sliderSeekbar: SeekBar = view.findViewById(R.id.slider_seek_bar)
            if(entry[0] >= 0.0) {
                sliderSeekbar.progress = entry[0].toInt()
            }
            val waterInflowRate: EditText = view.findViewById(R.id.water_inflow_rate_input)
            if(entry[1] >= 0.0)
                waterInflowRate.setText(entry[1].toString())
            val startVolume: EditText = view.findViewById(R.id.start_volume_input)
            if(entry[2] >= 0.0)
                startVolume.setText(entry[2].toString())
            endVolume = view.findViewById(R.id.end_volume_input)
            if(entry[3] >= 0.0) {
                endVolume.setText(entry[3].toString())
            }
            val chemDose: TextView = view.findViewById(R.id.chem_dose_display)
            val chemFlowRate: TextView = view.findViewById(R.id.chem_flow_display)
            minutesView = view.findViewById(R.id.timer_minutes)
            secondsView = view.findViewById(R.id.timer_seconds)

            //if accessChangeDose is true, display outputs
            if(viewModel.coagAccessAdjustDosage.value == true) {
                chemDose.text = String.format("%.${6}f", entry[5])
                chemFlowRate.text = String.format("%.${6}f", entry[6])
                timeElapsed = entry[4].toInt()
                val mins = timeElapsed / 60
                val secs = timeElapsed % 60
                minutesView.setText(String.format(Locale.US,"%02d", mins))
                secondsView.setText(String.format(Locale.US,"%02d",secs))
            }

            //timer functionality
            // set variables to access each necessary element
            startButton = view.findViewById(R.id.play_button)
            resetButton = view.findViewById(R.id.reset_button)
            endVolumeText = view.findViewById(R.id.end_volume_text)
            mlText = view.findViewById(R.id.ev_ml_text)
            timeElapsed = 0

            //this ensures endVolume is enabled as long as timer has run once
            if(viewModel.coagAccessAdjustDosage.value==true) {
                endVolumeText.setTextColor(Color.BLACK)
                mlText.setTextColor(Color.BLACK)
                endVolume.isEnabled = true
            }
            //handle start button logic
            startButton.setOnClickListener {
                val minutesText = minutesView.text.toString()
                val secondsText = secondsView.text.toString()
                //ensure input is valid, calculate time elapsed in seconds
                timeElapsed = 0
                if(minutesText.isNotEmpty()) {
                    timeElapsed += (minutesText.toInt() * 60)
                }
                if(secondsText.isNotEmpty()) {
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
            resetButton.setOnClickListener {
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
                        val endVol = endVolumeText.toDouble()
                        if(endVol < entry[2]) {
                            entry[3] = endVolumeText.toDouble()
                        }
                        else {
                            Toast.makeText(context,"End volume must be less than start volume.",Toast.LENGTH_SHORT).show()
                        }
                    }

                    //check if accessAdjustDosage has changed
                    viewModel.coagAccessAdjustDosage.value = true
                    for(i in 1..4) {
                        if (entry[i] < 0.0) {
                            viewModel.coagAccessAdjustDosage.value = false
                        }
                    }
                    // if calibration form is filled, calculate and display outputs
                    if (viewModel.coagAccessAdjustDosage.value == true) {
                        entry[6] = (entry[2] - entry[3]) / entry[4]
                        entry[5] = entry[6] * viewModel.chemConcentration.value!!
                        chemDose.text = String.format("%.${6}f", entry[5])
                        chemFlowRate.text = String.format("%.${6}f", entry[6])
                    }
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
            // if seekbar data is already entered, display on UI
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
                        sliderInput.setText(progress.toString())
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