package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.R
import com.example.aguadatosapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val homeViewModel =
        //    ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textHome
        //homeViewModel.text.observe(viewLifecycleOwner) {
        //    textView.text = it
        //}

//        root.findViewById<Button>(R.id.plantFlowNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_plant_flow_page)
//        }
//        root.findViewById<Button>(R.id.rawWaterNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_raw_water_page)
//        }
        root.findViewById<Button>(R.id.coagNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_coag_page)
        }
//        root.findViewById<Button>(R.id.filteredTurbNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_filtered_turbidity_page)
//        }
//        root.findViewById<Button>(R.id.clarifiedTurbNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_clarified_turbidity_page)
//        }
//        root.findViewById<Button>(R.id.feedbackNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_feedback_page)
//        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}