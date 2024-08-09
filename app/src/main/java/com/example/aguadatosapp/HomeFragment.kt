package com.example.aguadatosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // inflate view
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // handle logic for "PLANT FLOW" button
        root.findViewById<Button>(R.id.plantFlowNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_plant_flow_page)
        }

        // handle logic for "RAW WATER TURBIDITY" button
        root.findViewById<Button>(R.id.rawWaterNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_raw_water_page)
        }

        // handle logic for "COAGULANT DOSING" button
        root.findViewById<Button>(R.id.coagNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_coag_page)
        }

        // handle logic for "FILTERED TURBIDITY" button
//        root.findViewById<Button>(R.id.filteredTurbNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_filtered_turbidity_page)
//        }

        // handle logic for "CLARIFIED TURBIDITY" button
//        root.findViewById<Button>(R.id.clarifiedTurbNavButton).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_clarified_turbidity_page)
//        }

        // handle logic for "CHLORINE DOSAGE" button
        root.findViewById<Button>(R.id.chlorineNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_config_page_TEMP)
            //FIXME: change to chlorine once nav bar is fixed
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        Log.d("FragmentNavigation", "HomeFragment is now visible")
    }
}