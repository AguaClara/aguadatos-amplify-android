package com.example.aguadatosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.R
import com.example.aguadatosapp.databinding.FragmentHomeBinding

class ConfigurationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_configuration, container, false)

        //listener for back button (<)
        view.findViewById<Button>(R.id.back_button).setOnClickListener {
            findNavController().navigate(R.id.action_config_page_to_home)
        }
        Log.d("FragmentNavigation", "ConfigurationFragment is created")

        return view
    }
    override fun onResume() {
        super.onResume()
        Log.d("FragmentNavigation", "ConfigurationFragment is now visible")
    }
}