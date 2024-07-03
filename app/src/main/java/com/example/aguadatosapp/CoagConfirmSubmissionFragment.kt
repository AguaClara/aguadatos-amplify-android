package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.databinding.FragmentHomeBinding

// CoagFragment.kt
class CoagConfirmSubmissionFragment : Fragment() {

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_coag_dosing_confirm_entry, container, false)

        view.findViewById<Button>(R.id.x_button).setOnClickListener {
            findNavController().navigate(R.id.action_coag_confirm_to_coag_page)
        }
        view.findViewById<Button>(R.id.confirm_button).setOnClickListener {
            //commit temporary variables to backend and clear values
            findNavController().navigate(R.id.action_coag_confirm_to_coag_view_submission)
        }

        return view
    }

}