package com.example.aguadatosapp

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.core.Amplify.DataStore
import com.amplifyframework.datastore.DataStoreException
import com.amplifyframework.datastore.generated.model.ClarifiedEntry
import com.amplifyframework.datastore.generated.model.FilteredEntry
import com.amplifyframework.datastore.generated.model.InflowEntry
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant
import com.amplifyframework.datastore.generated.model.RawEntry
import com.amplifyframework.datastore.generated.model.CalibrationEntry
import com.amplifyframework.datastore.generated.model.ChemicalType
import com.amplifyframework.datastore.generated.model.DoseEntry
import java.time.Instant
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// RecordsFragment.kt
class RecordsFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    private var queryDate = Instant.now().toString().split("T")[0]
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_records, container, false)

        //initialize view model and entry container
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val todaysDate = Instant.now().toString().split("T")[0]
        val dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy")
        val dateLabel = view.findViewById<TextView>(R.id.date_label)

        //handle logic for date back button
        val dateBackButton = view.findViewById<Button>(R.id.date_back_button)
        dateBackButton.setOnClickListener {
            queryDate = getYesterday(queryDate)
            dateLabel.text = queryDate
            makeQueries()
        }

        //handle logic for date forward button
        val dateForwardButton = view.findViewById<Button>(R.id.date_forward_button)
        dateForwardButton.setOnClickListener {
            if(queryDate != todaysDate) {
                queryDate = getTomorrow(queryDate)
                dateLabel.text = queryDate
                makeQueries()
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        makeQueries()
    }
    private fun getYesterday(dateString: String): String {
        // Step 1: get today
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)

        // Step 2: Subtract one day
        val previousDay = date.minusDays(1)

        // Step 3: Format the result back to a string
        val previousDayString = previousDay.format(formatter)

        return previousDayString
    }
    private fun getTomorrow(dateString: String): String {
        // Step 1: get today
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val date = LocalDate.parse(dateString, formatter)

        // Step 2: Subtract one day
        val nextDay = date.plusDays(1)

        // Step 3: Format the result back to a string
        val nextDayString = nextDay.format(formatter)

        return nextDayString
    }
    private fun makeQueries() {
        val container = view?.findViewById<LinearLayout>(R.id.entriesContainer)
        val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
        var myPlantId = ""
        var myOpId = ""
        var myPlantName = ""
        var myOpName = ""

        if (sharedPreferences != null) {
            myPlantId = sharedPreferences.getString("plantID", null).toString()
            myOpId = sharedPreferences.getString("operatorID", null).toString()
            myPlantName = sharedPreferences.getString("plantName", null).toString()
            myOpName = sharedPreferences.getString("operatorName", null).toString()
        }

        requireActivity().runOnUiThread {
            if (container != null) {
                container.removeAllViews()
            }
        }

        queryInflowEntries(
            date = queryDate,
            plantId = myPlantId,
            operatorId = myOpId,
            onSuccess = { entries ->
                for(i in entries) {
                    val unformattedDateTimeString = i.createdAt.toString().split("'")[1]
                    val plantFlowEntry = PlantFlowEntry(
                        entryTitle = getString(R.string.entry_plant_flow),
                        inflowRate = i.inflowRate.toFloat(),
                        additionalNotes = i.notes,
                        creationDateTime = unformattedDateTimeString.split("T")[1].split(".")[0]
                    )
                    if (container != null) {
                        addEntry(container, plantFlowEntry)
                    }
                }
                Log.d("msg", "Found ${entries.size} PlantFlowEntries")
            },
            onError = { error ->
                Log.d("msg","Query failed: ${error.message}")
            }
        )

        queryRawWaterEntries(
            date = queryDate,
            plantId = myPlantId,
            operatorId = myOpId,
            onSuccess = { entries ->
                for(i in entries) {
                    val unformattedDateTimeString = i.createdAt.toString().split("'")[1]
                    val rawWaterEntry = RawWaterTurbidityEntry(
                        entryTitle = getString(R.string.entry_raw_water),
                        turbidityReadings = i.turbidity,
                        additionalNotes = i.notes,
                        creationDateTime = unformattedDateTimeString.split("T")[1].split(".")[0]
                    )
                    if (container != null) {
                        addEntry(container, rawWaterEntry)
                    }
                }
                Log.d("msg", "Found ${entries.size} RawEntries")
            },
            onError = { error ->
                Log.d("msg","Query failed: ${error.message}")
            }
        )

        queryClarifiedWaterEntries(
            date = queryDate,
            plantId = myPlantId,
            operatorId = myOpId,
            onSuccess = { entries ->
                for(i in entries) {
                    val unformattedDateTimeString = i.createdAt.toString().split("'")[1]
                    val clarifiedWaterEntry = clarifiedWaterTurbidityEntry(
                        entryTitle = getString(R.string.entry_clarified_water),
                        turbidityReadings = i.turbidity,
                        additionalNotes = i.notes,
                        creationDateTime = unformattedDateTimeString.split("T")[1].split(".")[0]
                    )
                    if (container != null) {
                        addEntry(container, clarifiedWaterEntry)
                    }
                }
                Log.d("msg", "Found ${entries.size} clarifiedEntries")
            },
            onError = { error ->
                Log.d("msg","Query failed: ${error.message}")
            }
        )

        queryFilteredWaterEntries(
            date = queryDate,
            plantId = myPlantId,
            operatorId = myOpId,
            onSuccess = { entries ->
                for(i in entries) {
                    val unformattedDateTimeString = i.createdAt.toString().split("'")[1]
                    var filteredWaterEntry = FilteredWaterTurbidityEntry(
                        entryTitle = getString(R.string.entry_filtered_water),
                        turbidityReadings = i.turbidities.toDoubleArray(),
                        additionalNotes = i.notes,
                        creationDateTime = unformattedDateTimeString.split("T")[1].split(".")[0]
                    )
                    if (container != null) {
                        addEntry(container, filteredWaterEntry)
                    }
                }
                Log.d("msg", "Found ${entries.size} FilteredEntries")
            },
            onError = { error ->
                Log.d("msg","Query failed: ${error.message}")
            }
        )

        queryCalibrationEntries(
            date = queryDate,
            plantId = myPlantId,
            operatorId = myOpId,
            onSuccess = { entries ->
                for(i in entries) {
                    val unformattedDateTimeString = i.createdAt.toString().split("'")[1]
                    var doseType = ""
                    doseType = if(i.chemicalType == ChemicalType.COAGULANT) {
                        getString(R.string.coagulant)
                    } else {
                        getString(R.string.chlorine)
                    }
                    var calibrationEntry = CoagChlorCalibrationEntry(
                        entryTitle = getString(R.string.entry_calib,doseType),
                        creationDateTime = unformattedDateTimeString.split("T")[1].split(".")[0],
                        sliderPosition = i.sliderPosition,
                        inflowRate = i.inflowRate,
                        startVolume = i.startVolume,
                        endVolume = i.endVolume,
                        timeElapsed = i.timeElapsed,
                        chemicalDose = i.dose,
                        chemicalType = doseType,
                        chemicalFlowRate = i.flowRate,
                        activeTankVolume = i.tankVolume
                    )
                    if (container != null) {
                        addEntry(container, calibrationEntry)
                    }
                }
                Log.d("msg", "Found ${entries.size} CalibrationEntries")
            },
            onError = { error ->
                Log.d("msg","Query failed: ${error.message}")
            }
        )

        queryChangeDoseEntries(
            date = queryDate,
            plantId = myPlantId,
            operatorId = myOpId,
            onSuccess = { entries ->
                for(i in entries) {
                    val unformattedDateTimeString = i.createdAt.toString().split("'")[1]
                    var doseType = ""
                    doseType = if(i.chemicalType == ChemicalType.COAGULANT) {
                        getString(R.string.coagulant)
                    } else {
                        getString(R.string.chlorine)
                    }
                    val calibEntry = i.calibrationEntry
                    val doseEntry = ChangeDoseEntry(
                        entryTitle = getString(R.string.entry_change_dose,doseType),
                        creationDateTime = unformattedDateTimeString.split("T")[1].split(".")[0],
                        chemicalFlowRate = calibEntry.flowRate,
                        chemicalDose = calibEntry.dose,
                        sliderPosition = calibEntry.sliderPosition,
                        targetChemicalDose = i.targetDose,
                        chemicalType = doseType,
                        updatedSliderPosition = i.updatedSliderPosition,
                        updatedChemicalFlowRate = i.updatedFlowRate
                    )
                    if (container != null) {
                        addEntry(container, doseEntry)
                    }
                }
                Log.d("msg", "Found ${entries.size} DoseEntries")
            },
            onError = { error ->
                Log.d("msg","Query failed: ${error.message}")
            }
        )
    }
    private fun addEntry(container: LinearLayout, entry: Entry) {
        // Inflate the input layout
        val inflater = LayoutInflater.from(context)
        val entryLayout = inflater.inflate(R.layout.layout_entry, container, false)


        // Set variables to access necessary UI elements
        val entryName = entryLayout.findViewById<TextView>(R.id.entry_title)
        val expandableText = entryLayout.findViewById<TextView>(R.id.expandableText)
        val timeStamp = entryLayout.findViewById<TextView>(R.id.timestamp)
        //val editButton = entryLayout.findViewById<TextView>(R.id.edit_button)
        //editButton.setOnClickListener {
        //    showEditEntryDialog(entry)
        //}
        // Read data into front end display
        when (entry) {
            is PlantFlowEntry -> {
                entryName.text = entry.entryTitle
                expandableText.text = getString(R.string.inflow_rate_with_input,entry.inflowRate)
                timeStamp.text = entry.creationDateTime
            }
            is RawWaterTurbidityEntry -> {
                entryName.text = entry.entryTitle
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReadings)
                timeStamp.text = entry.creationDateTime
            }
            is FilteredWaterTurbidityEntry -> {
                entryName.text = entry.entryTitle
                expandableText.text = getString(R.string.turbidity_with_input,entry.turbidityReadings[0])
                timeStamp.text = entry.creationDateTime
            }
            is CoagChlorCalibrationEntry -> {
                entryName.text = entry.entryTitle
                expandableText.text = getString(R.string.chemical_dose_with_input,entry.chemicalDose)
                timeStamp.text = entry.creationDateTime
            }
            is ChangeDoseEntry -> {
                entryName.text = entry.entryTitle
                expandableText.text = getString(R.string.new_slider_pos,entry.sliderPosition)
                timeStamp.text = entry.creationDateTime
            }
            is FeedbackEntry -> {
                entryName.text = entry.entryTitle
                expandableText.text = getString(R.string.feedback_with_input,entry.operatorFeedback)
                timeStamp.text = entry.creationDateTime
            }
        }

        // handle expand/contract button logic
        var expanded = false
        expandableText.visibility = View.GONE
        val expandButton = entryLayout.findViewById<Button>(R.id.btnToggle)
        expandButton.setOnClickListener {
            if(expanded) {
                expanded = false
                expandableText.visibility = View.GONE
                expandButton.background = context?.let { it1 -> ContextCompat.getDrawable(it1,R.drawable.dropdown_toggle) }
            }
            else {
                expanded = true
                expandableText.visibility = View.VISIBLE
                expandButton.background = context?.let { it1 -> ContextCompat.getDrawable(it1,R.drawable.dropup_toggle) }
            }
        }
        // Add the inflated layout to the container
        requireActivity().runOnUiThread {
            container.addView(entryLayout)
        }
    }

    private fun showEditEntryDialog(entry: Entry) {
        val dialogView : View = when (entry) {
            is PlantFlowEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_plant_flow_entry, null)
            }
            is RawWaterTurbidityEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_turbidity_entry, null)
            }
            is FilteredWaterTurbidityEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_filtered_water_entry, null)
            }
            is CoagChlorCalibrationEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
            is ChangeDoseEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_change_dosage_entry, null)
            }
            is FeedbackEntry -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_feedback_entry, null)
            }
            else -> {
                LayoutInflater.from(requireContext()).inflate(R.layout.edit_calibration_entry, null)
            }
        }
        val closeButton = dialogView.findViewById<Button>(R.id.close_button)
        val deletedButton = dialogView.findViewById<Button>(R.id.delete_button)
        val saveButton = dialogView.findViewById<Button>(R.id.save_button)
        val titleText = dialogView.findViewById<TextView>(R.id.title)
        val timeText = dialogView.findViewById<TextView>(R.id.time_text)

        when (entry) {
            is PlantFlowEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
            }
            is RawWaterTurbidityEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
            }
            is clarifiedWaterTurbidityEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
            }
            is FilteredWaterTurbidityEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
            }
            is CoagChlorCalibrationEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
                dialogView.findViewById<TextView>(R.id.chemical_type).setText(entry.chemicalType)
                dialogView.findViewById<EditText>(R.id.slider_position).setText("${entry.sliderPosition}")
                dialogView.findViewById<EditText>(R.id.inflow_rate).setText("${entry.inflowRate}")
                dialogView.findViewById<EditText>(R.id.start_height).setText("${entry.startVolume}")
                dialogView.findViewById<EditText>(R.id.end_height).setText("${entry.endVolume}")
                dialogView.findViewById<EditText>(R.id.time_elapsed).setText("${entry.timeElapsed}")
                dialogView.findViewById<EditText>(R.id.chemical_dose).setText("${entry.chemicalDose}")
                dialogView.findViewById<EditText>(R.id.chemical_flow_rate).setText("${entry.chemicalFlowRate}")
                dialogView.findViewById<EditText>(R.id.active_tank_volume).setText("${entry.activeTankVolume}")
            }
            is ChangeDoseEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
            }
            is FeedbackEntry -> {
                titleText.text = entry.entryTitle
                timeText.text = entry.creationDateTime
                dialogView.findViewById<EditText>(R.id.edit_feedback).setText(entry.operatorFeedback)
            }
        }

        // Create and show the AlertDialog
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .create()

        closeButton.setOnClickListener {
            dialog.dismiss()  // Close the dialog
        }

        // FIXME
        deletedButton.setOnClickListener {
            dialog.dismiss()  // Delete entry
        }

        // FIXME
        saveButton.setOnClickListener {
            dialog.dismiss()  // Close the dialog after saving
        }

        // Show the dialog
        dialog.show()
    }

    //query inflow entries
    private fun queryInflowEntries(
        date: String, // Date format: "YYYY-MM-DD" or any consistent string format
        plantId: String,
        operatorId: String,
        onSuccess: (List<InflowEntry>) -> Unit,
        onError: (DataStoreException) -> Unit
    ) {
        // Create the predicate to filter based on the parameters
        val predicate = InflowEntry.PLANT.eq(Plant.justId(plantId))
            .and(InflowEntry.OPERATOR.eq(Operator.justId(operatorId)))
            .and(InflowEntry.CREATED_AT.contains(date))
        // Perform the query
        DataStore.query(
            InflowEntry::class.java,
            predicate,
            { results ->
                val entries = mutableListOf<InflowEntry>()
                while (results.hasNext()) {
                    val entry = results.next()
                    entries.add(entry)
                }
                Log.d("msg","successfully queried plant flow entries")
                onSuccess(entries)
            },
            { error ->
                Log.d("msg","error: $error")
                onError(error)
            }
        )
    }

    //query raw water entries
    private fun queryRawWaterEntries(
        date: String, // Date format: "YYYY-MM-DD" or any consistent string format
        plantId: String,
        operatorId: String,
        onSuccess: (List<RawEntry>) -> Unit,
        onError: (DataStoreException) -> Unit
    ) {
        // Create the predicate to filter based on the parameters
        val predicate = RawEntry.PLANT.eq(Plant.justId(plantId))
            .and(RawEntry.OPERATOR.eq(Operator.justId(operatorId)))
            .and(RawEntry.CREATED_AT.contains(date))
        // Perform the query
        DataStore.query(
            RawEntry::class.java,
            predicate,
            { results ->
                val entries = mutableListOf<RawEntry>()
                while (results.hasNext()) {
                    val entry = results.next()
                    entries.add(entry)
                }
                Log.d("msg","successfully queried raw water entries")
                onSuccess(entries)
            },
            { error ->
                Log.d("msg","error: $error")
                onError(error)
            }
        )
    }

    //query clarified water entries
    private fun queryClarifiedWaterEntries(
        date: String, // Date format: "YYYY-MM-DD" or any consistent string format
        plantId: String,
        operatorId: String,
        onSuccess: (List<ClarifiedEntry>) -> Unit,
        onError: (DataStoreException) -> Unit
    ) {
        // Create the predicate to filter based on the parameters
        val predicate = ClarifiedEntry.PLANT.eq(Plant.justId(plantId))
            .and(ClarifiedEntry.OPERATOR.eq(Operator.justId(operatorId)))
            .and(ClarifiedEntry.CREATED_AT.contains(date))
        // Perform the query
        DataStore.query(
            ClarifiedEntry::class.java,
            predicate,
            { results ->
                val entries = mutableListOf<ClarifiedEntry>()
                while (results.hasNext()) {
                    val entry = results.next()
                    entries.add(entry)
                }
                Log.d("msg","successfully queried clarified water entries")
                onSuccess(entries)
            },
            { error ->
                Log.d("msg","error: $error")
                onError(error)
            }
        )
    }

    //query filtered water entries
    private fun queryFilteredWaterEntries(
        date: String, // Date format: "YYYY-MM-DD" or any consistent string format
        plantId: String,
        operatorId: String,
        onSuccess: (List<FilteredEntry>) -> Unit,
        onError: (DataStoreException) -> Unit
    ) {
        // Create the predicate to filter based on the parameters
        val predicate = FilteredEntry.PLANT.eq(Plant.justId(plantId))
            .and(FilteredEntry.OPERATOR.eq(Operator.justId(operatorId)))
            .and(FilteredEntry.CREATED_AT.contains(date))
        // Perform the query
        DataStore.query(
            FilteredEntry::class.java,
            predicate,
            { results ->
                val entries = mutableListOf<FilteredEntry>()
                while (results.hasNext()) {
                    val entry = results.next()
                    entries.add(entry)
                }
                Log.d("msg","successfully queried filtered water entries")
                onSuccess(entries)
            },
            { error ->
                Log.d("msg","error: $error")
                onError(error)
            }
        )
    }

    //query calibration entries
    private fun queryCalibrationEntries(
        date: String, // Date format: "YYYY-MM-DD" or any consistent string format
        plantId: String,
        operatorId: String,
        onSuccess: (List<CalibrationEntry>) -> Unit,
        onError: (DataStoreException) -> Unit
    ) {
        // Create the predicate to filter based on the parameters
        val predicate = CalibrationEntry.PLANT.eq(Plant.justId(plantId))
            .and(CalibrationEntry.OPERATOR.eq(Operator.justId(operatorId)))
            .and(CalibrationEntry.CREATED_AT.contains(date))
        // Perform the query
        DataStore.query(
            CalibrationEntry::class.java,
            predicate,
            { results ->
                val entries = mutableListOf<CalibrationEntry>()
                while (results.hasNext()) {
                    val entry = results.next()
                    entries.add(entry)
                }
                Log.d("msg","successfully queried calibration entries")
                onSuccess(entries)
            },
            { error ->
                Log.d("msg","error: $error")
                onError(error)
            }
        )
    }

    //query change dose entries
    private fun queryChangeDoseEntries(
        date: String, // Date format: "YYYY-MM-DD" or any consistent string format
        plantId: String,
        operatorId: String,
        onSuccess: (List<DoseEntry>) -> Unit,
        onError: (DataStoreException) -> Unit
    ) {
        // Create the predicate to filter based on the parameters
        val predicate = DoseEntry.PLANT.eq(Plant.justId(plantId))
            .and(DoseEntry.OPERATOR.eq(Operator.justId(operatorId)))
            .and(DoseEntry.CREATED_AT.contains(date))
        // Perform the query
        DataStore.query(
            DoseEntry::class.java,
            predicate,
            { results ->
                val entries = mutableListOf<DoseEntry>()
                while (results.hasNext()) {
                    val entry = results.next()
                    entries.add(entry)
                }
                Log.d("msg","successfully queried dose water entries")
                onSuccess(entries)
            },
            { error ->
                Log.d("msg","error: $error")
                onError(error)
            }
        )
    }
}