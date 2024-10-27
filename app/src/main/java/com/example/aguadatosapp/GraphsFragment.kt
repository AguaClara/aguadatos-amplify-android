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
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class GraphsFragment : Fragment() {

    private lateinit var graphContainer: LinearLayout
    private val activeGraphs = mutableMapOf<String, LineChart>()
    private val overlayDataSets = mutableMapOf<String, LineDataSet>()
    private val overlayTitles = setOf("turbidityInfluent (NTU)", "turbidityEffluent (NTU)", "turbidityClarified (NTU)")

    // Map button IDs to colors
    private val buttonColors = mapOf(
        R.id.btnPF to Color.RED,
        R.id.btnRWT to Color.GREEN,
        R.id.btnCoD to Color.CYAN,
        R.id.btnFWT to Color.parseColor("#FFA500"), // Orange
        R.id.btnCWT to Color.parseColor("#00008B"), // Dark Blue
        R.id.btnChD to Color.parseColor("#800080")  // Purple
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_graphs, container, false)
        graphContainer = view.findViewById(R.id.graphContainer)

        // Buttons for selecting graph types
        val btnPF: Button = view.findViewById(R.id.btnPF)
        val btnRWT: Button = view.findViewById(R.id.btnRWT)
        val btnCoD: Button = view.findViewById(R.id.btnCoD)
        val btnFWT: Button = view.findViewById(R.id.btnFWT)
        val btnCWT: Button = view.findViewById(R.id.btnCWT)
        val btnChD: Button = view.findViewById(R.id.btnChD)

        // Set up click listeners for each graph button
        btnPF.setOnClickListener { toggleGraph("raw (L/min)", btnPF) }
        btnRWT.setOnClickListener { toggleGraph("turbidityInfluent (NTU)", btnRWT) }
        btnCoD.setOnClickListener { toggleGraph("Coagulant Dosage", btnCoD) }
        btnFWT.setOnClickListener { toggleGraph("turbidityEffluent (NTU)", btnFWT) }
        btnCWT.setOnClickListener { toggleGraph("turbidityClarified (NTU)", btnCWT) }
        btnChD.setOnClickListener { toggleGraph("Chlorine Dosage", btnChD) }

        return view
    }

    // Function to add or remove a graph when a button is clicked
    private fun toggleGraph(graphTitle: String, button: Button) {
        if (overlayTitles.contains(graphTitle)) {
            // Handle overlay graph toggling
            if (overlayDataSets.containsKey(graphTitle)) {
                // Remove the dataset if it already exists in the overlay
                overlayDataSets.remove(graphTitle)
                button.setBackgroundColor(Color.WHITE)
            } else {
                // Add the dataset if it's not in the overlay
                val dataSet = createDataSet(graphTitle, buttonColors[button.id] ?: Color.BLUE)
                if (dataSet != null) {
                    overlayDataSets[graphTitle] = dataSet
                    button.setBackgroundColor(buttonColors[button.id] ?: Color.BLUE)
                }
            }
            updateOverlayChart()
        } else {
            // Handle individual graphs
            if (activeGraphs.containsKey(graphTitle)) {
                removeGraph(graphTitle, button)
            } else {
                addGraph(graphTitle, button)
            }
        }
    }

    // Function to add an individual graph
    private fun addGraph(graphTitle: String, button: Button) {
        val newGraph = LineChart(requireContext())
        val titleView = TextView(requireContext()).apply {
            text = graphTitle
            textSize = 18f
            setTextColor(Color.BLACK)
            tag = "title_$graphTitle"
        }

        graphContainer.addView(titleView)
        graphContainer.addView(newGraph)
        activeGraphs[graphTitle] = newGraph
        setUpGraph(graphTitle, newGraph, buttonColors[button.id] ?: Color.BLUE)

        // Set button background color to its unique color when selected
        button.setBackgroundColor(buttonColors[button.id] ?: Color.BLUE)
    }

    // Function to remove an individual graph and reset button color
    private fun removeGraph(graphTitle: String, button: Button) {
        val graph = activeGraphs.remove(graphTitle)
        graph?.let { graphContainer.removeView(it) }

        val titleView = graphContainer.findViewWithTag<View>("title_$graphTitle")
        graphContainer.removeView(titleView)

        // Set button background color to white when deselected
        button.setBackgroundColor(Color.WHITE)
    }

    // Function to create a dataset for overlay graphs with specific color
    private fun createDataSet(graphTitle: String, color: Int): LineDataSet? {
        val filePath = "datalog_8-7-2024.csv"
        try {
            val assetManager = requireContext().assets
            val inputStream = assetManager.open(filePath)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val data = ArrayList<Entry>()
            Log.d("CSVReader", "Opened file: $filePath")

            // Read the header line to get column indices
            val headerLine = reader.readLine()
            val headers = headerLine.split(",")
            val columnIndex = headers.indexOf(graphTitle)
            val dayFractionIndex = headers.indexOf("Day fraction since midnight on 8/7/2024")

            // Check if both columns are found
            if (columnIndex == -1 || dayFractionIndex == -1) {
                Log.e("CSVReader", "Column '$graphTitle' or 'Day fraction' not found in CSV.")
                return null
            }

            // Read each line in the CSV and add to the data list
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

            // Create a LineDataSet with the specified color
            val dataSet = LineDataSet(data, graphTitle)
            dataSet.color = color
            dataSet.setCircleColor(Color.BLACK)
            dataSet.valueTextColor = Color.BLACK
            return dataSet
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Graph", "Error reading CSV: ${e.message}")
            return null
        }
    }

    // Function to update the overlay chart with all selected overlay datasets
    private fun updateOverlayChart() {
        // Remove overlay chart if no datasets are left
        if (overlayDataSets.isEmpty()) {
            val overlayChart = activeGraphs.remove("OverlayChart")
            overlayChart?.let { graphContainer.removeView(it) }
            return
        }

        // Get or create the overlay chart
        val overlayChart = activeGraphs.getOrPut("OverlayChart") {
            val chart = LineChart(requireContext())
            graphContainer.addView(chart)

            // Set layout parameters to prevent collapsing
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500 // Fixed height to prevent collapsing
            )
            layoutParams.setMargins(0, 16, 0, 16)
            chart.layoutParams = layoutParams

            chart
        }

        // Set overlay data
        val lineData = LineData()
        for (dataSet in overlayDataSets.values) {
            lineData.addDataSet(dataSet)
        }
        overlayChart.data = lineData
        overlayChart.invalidate()
    }

    // Set up individual non-overlay graphs with specific color
    private fun setUpGraph(graphTitle: String, graph: LineChart, color: Int) {
        val dataSet = createDataSet(graphTitle, color)
        if (dataSet != null) {
            val lineData = LineData(dataSet)
            graph.data = lineData

            // Configure X-Axis and prevent collapsing
            val xAxis: XAxis = graph.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            graph.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500 // Fixed height for each graph
            )
            graph.invalidate()
        }
    }
}