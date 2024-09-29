package com.example.aguadatosapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

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
        }
        return view
    }
}