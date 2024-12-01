package com.example.aguadatosapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.amplifyframework.core.Amplify
import com.amplifyframework.core.model.temporal.Temporal
import com.amplifyframework.datastore.generated.model.FeedbackEntry
import com.amplifyframework.datastore.generated.model.Operator
import com.amplifyframework.datastore.generated.model.Plant
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

// FeedbackFragment.kt
class FeedbackFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_feedback, container, false)

        //initialize view model and feedback input element
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        val feedbackInput: EditText = view.findViewById(R.id.feedback_box)

        //set up logic for submit button
        view.findViewById<Button>(R.id.feedback_submit_button).setOnClickListener {
            val feedbackString = feedbackInput.text.toString()
            if(feedbackString != "") {
                viewModel.feedback.value = feedbackString
                feedbackInput.text.clear()
                Toast.makeText(context,"Thank you for your input!",Toast.LENGTH_SHORT).show()
            }
            // add time and date to submission
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")
            val timeText = LocalTime.now().format(timeFormatter)
            viewModel.time.value = timeText
            val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val dateText = LocalDate.now().format(dateFormatter)
            viewModel.date.value = dateText

            val sharedPreferences = context?.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
            var plantId = ""
            var opId = ""
            if (sharedPreferences != null) {
                plantId = sharedPreferences.getString("plantID", null).toString()
                opId = sharedPreferences.getString("operatorID", null).toString()
            }
            createFeedbackEntry(Temporal.DateTime( Instant.now().toString()), plantId, opId, viewModel.feedback.value)

        }
        return view
    }

    // this function sends entry data to backend
    private fun createFeedbackEntry(createdAt: Temporal.DateTime, plantId: String, operatorId: String, feedback: String?) {
        // Create a FeedbackEntry instance
        val newFeedbackEntry = FeedbackEntry.builder()
            .createdAt(createdAt) // Temporal.DateTime object
            .feedback(feedback) // String value
            .plant(Plant.justId(plantId)) // Reference Plant by ID
            .operator(Operator.justId(operatorId)) // Reference Operator by ID
            .build()

        // Save the FeedbackEntry to DataStore
        Amplify.DataStore.save(newFeedbackEntry, {
            // Success callback
            Log.d("msg","Successfully saved FeedbackEntry with ID: ${newFeedbackEntry.id}")
        },
            { error ->
                // Error callback
                Log.d("msg","Failed to save FeedbackEntry: ${error.message}")
            }
        )
    }
}