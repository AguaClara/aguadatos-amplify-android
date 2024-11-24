package com.example.aguadatosapp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.aguadatosapp.databinding.FragmentHomeBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var graph: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Initialize graph container
        val graphContainerContent: LinearLayout = root.findViewById(R.id.graphContainerContent)
        graph = LineChart(requireContext())
        graph.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            500
        )
        graphContainerContent.addView(graph)

        // Set up default graph
        setUpGraph("raw (L/min)", Color.RED)

        // Time scale buttons
        root.findViewById<Button>(R.id.btn12H).setOnClickListener { updateTimeScale("12H") }
        root.findViewById<Button>(R.id.btn24H).setOnClickListener { updateTimeScale("24H") }
        root.findViewById<Button>(R.id.btn7D).setOnClickListener { updateTimeScale("7D") }
        root.findViewById<Button>(R.id.btn1M).setOnClickListener { updateTimeScale("1M") }
        root.findViewById<Button>(R.id.btnYTD).setOnClickListener { updateTimeScale("YTD") }

        // Navigation buttons
        root.findViewById<Button>(R.id.plantFlowNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_plant_flow_page)
        }
        root.findViewById<Button>(R.id.rawWaterNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_raw_water_page)
        }
        root.findViewById<Button>(R.id.coagNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_coag_page)
        }
        root.findViewById<Button>(R.id.filteredTurbNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_filtered_water_page)
        }
        root.findViewById<Button>(R.id.clarifiedTurbNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_clarified_water_page)
        }
        root.findViewById<Button>(R.id.chlorineNavButton).setOnClickListener {
            findNavController().navigate(R.id.action_home_to_chlorine_page)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUpGraph(graphTitle: String, color: Int) {
        val dataSet = createDataSet(graphTitle, color)
        if (dataSet != null) {
            val lineData = LineData(dataSet)
            graph.data = lineData

            val xAxis: XAxis = graph.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)

            graph.invalidate()
        }
    }

    private fun createDataSet(graphTitle: String, color: Int): LineDataSet? {
        val filePath = "datalog_8-7-2024.csv"
        try {
            val assetManager = requireContext().assets
            val inputStream = assetManager.open(filePath)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val data = ArrayList<Entry>()

            val headerLine = reader.readLine()
            val headers = headerLine.split(",")
            val columnIndex = headers.indexOf(graphTitle)
            val dayFractionIndex = headers.indexOf("Day fraction since midnight on 8/7/2024")

            if (columnIndex == -1 || dayFractionIndex == -1) {
                Log.e("CSVReader", "Column '$graphTitle' or 'Day fraction' not found in CSV.")
                return null
            }

            reader.forEachLine { line ->
                val tokens = line.split(",")
                if (tokens.size > columnIndex) {
                    val dayFraction = tokens[dayFractionIndex].toFloatOrNull() ?: return@forEachLine
                    val value = tokens[columnIndex].toFloatOrNull() ?: return@forEachLine
                    data.add(Entry(dayFraction, value))
                }
            }

            if (data.isEmpty()) {
                Log.d("Graph", "No data was read from the CSV.")
                return null
            }

            return LineDataSet(data, graphTitle).apply {
                this.color = color
                setCircleColor(Color.BLACK)
                valueTextColor = Color.BLACK
            }
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Graph", "Error reading CSV: ${e.message}")
            return null
        }
    }

    private fun updateTimeScale(timeScale: String) {
        val maxDays = when (timeScale) {
            "12H" -> 0.5f
            "24H" -> 1f
            "7D" -> 7f
            "1M" -> 30f
            "YTD" -> 365f
            else -> 7f
        }
        graph.xAxis.axisMaximum = maxDays
        graph.invalidate()
    }
}