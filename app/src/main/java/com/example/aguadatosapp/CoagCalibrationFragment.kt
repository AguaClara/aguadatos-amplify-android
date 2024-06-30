package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.aguadatosapp.R
import android.widget.Button
import androidx.navigation.fragment.findNavController

// CoagFragment.kt
class CoagCalibrationFragment : Fragment() {
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

}