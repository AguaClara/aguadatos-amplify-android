package com.example.aguadatosapp
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.ActiveTank
import com.amplifyframework.datastore.generated.model.CalibrationEntry
import com.amplifyframework.datastore.generated.model.ChemicalType
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
// ChlorineCalibrationConfirmEntryFragment.kt
class ChlorineCalibrationConfirmEntryFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate layout
        val view = inflater.inflate(R.layout.fragment_chlorine_calibration_confirm_entry, container, false)

        // handle logic for back button (X)
        view.findViewById<Button>(R.id.x_button).setOnClickListener {
            findNavController().navigate(R.id.action_chlorine_confirm_to_chlorine_page)
        }
        // handle logic for confirm entry button
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            //add time to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText

            val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            var plantId = ""
            var opId = ""
            if (sharedPreferences != null) {
                plantId = sharedPreferences.getString("plantID", null).toString()
                opId = sharedPreferences.getString("operatorID", null).toString()
            }
            val tankVolumes = viewModel.chlorineTankVolumes.value
            var activeTank = "1"
            var tankVol = 0.0
            if(tankVolumes?.get(1)!! > 0.0) {
                activeTank = "2"
                tankVol = tankVolumes[1]
            }
            viewModel.chlorineCalibrationData.value?.let { it1 ->
                createChlorineCalibrationEntry(Temporal.DateTime( Instant.now().toString()), it1[0], it1[1],
                                                it1[2], it1[3], it1[4].toInt(), it1[5], it1[6], activeTank,
                                                tankVol, plantId, opId)
            }

            //navigate to next page
            findNavController().navigate(R.id.action_chlorine_confirm_to_chlorine_view_submission)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val chemTypeView: TextView = view.findViewById(R.id.chem_type_text)

        //display chemical type set in configuration
        val chemTypeText = viewModel.chemType.value
        chemTypeView.text = getString(R.string.chem_type, chemTypeText)
        // Observe the data from ViewModel
        viewModel.chlorineCalibrationData.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                //Update all text views to contain the data numbers
                val sliderPosView: TextView = view.findViewById(R.id.slider_pos_info)
                sliderPosView.text = getString(R.string.slider_position_with_input, entry[0])
                val inflowRateView: TextView = view.findViewById(R.id.inflow_rate_info)
                inflowRateView.text = getString(R.string.inflow_rate_with_input, entry[1])
                val svInfoView: TextView = view.findViewById(R.id.sv_info)
                svInfoView.text = getString(R.string.start_volume_with_input, entry[2])
                val evInfoView: TextView = view.findViewById(R.id.ev_info)
                evInfoView.text = getString(R.string.end_volume_with_input, entry[3])
                val timeElapsedView: TextView = view.findViewById(R.id.time_elapsed_info)
                timeElapsedView.text = getString(R.string.time_elapsed_with_input, entry[4])
                val chemDoseView: TextView = view.findViewById(R.id.chem_dose_info)
                chemDoseView.text = getString(R.string.chlorine_dose_with_input, entry[5])
                val chemFlowRateView: TextView = view.findViewById(R.id.chem_flow_info)
                chemFlowRateView.text = getString(R.string.chlorine_flow_rate_with_input, entry[6])
            }
        }

        //observe tank volume data from ViewModel
        viewModel.chlorineTankVolumes.observe(viewLifecycleOwner) { entry ->
            // Update UI based on the received data
            if (entry != null) {
                var activeTank = 1
                var activeTankVol = entry[0]
                if (entry[1] > 0.0) {
                    activeTank = 2
                    activeTankVol = entry[1]
                }
                //Update all text views to contain the data numbers
                val activeTankView: TextView = view.findViewById(R.id.active_tank_info)
                activeTankView.text = getString(R.string.active_tank_with_input, activeTank)
                val activeTankVolView: TextView = view.findViewById(R.id.tank_vol_info)
                activeTankVolView.text = getString(R.string.active_tank_volume, activeTankVol)
            }
        }

        //add date to submission
        val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateText = LocalDate.now().format(dateFormatter)
        viewModel.date.value = dateText

        //display date
        val dateView: TextView = view.findViewById(R.id.date_text)
        dateView.text = getString(R.string.date,dateText)
    }

    // this function sends entry data to backend
    private fun createChlorineCalibrationEntry(createdAt: Temporal.DateTime, sliderPos: Double, inflowRate: Double,
                                               sV: Double, eV: Double, timeElapsed: Int, dose: Double, flowRate: Double,
                                               activeTank: String, tV: Double, plantId: String, operatorId: String) {
        // Create a calibrationEntry instance
        val newCalibrationEntry = CalibrationEntry.builder()
            .createdAt(createdAt) // Temporal.DateTime object
            .chemicalType(ChemicalType.CHLORINE) //ChemicalType object
            .sliderPosition(sliderPos) //Double value
            .inflowRate(inflowRate) //Double value
            .startVolume(sV) //Double value
            .endVolume(eV) //Double value
            .timeElapsed(timeElapsed) //Integer value
            .dose(dose) //Double value
            .flowRate(flowRate) //Double value
            .activeTank(if (activeTank == "1") ActiveTank.TANK_A else ActiveTank.TANK_B) //ActiveTank object
            .tankVolume(tV) //Double value
            .plant(Plant.justId(plantId)) // Reference Plant by ID
            .operator(Operator.justId(operatorId)) // Reference Operator by ID
            .build()

        // Save the CalibrationEntry to DataStore
        Amplify.DataStore.save(newCalibrationEntry, {
            // Success callback
            Log.d("msg","Successfully saved CalibrationEntry with ID: ${newCalibrationEntry.id}")
        },
            { error ->
                // Error callback
                Log.d("msg","Failed to save CalibrationEntry: ${error.message}")
            }
        )
    }
}