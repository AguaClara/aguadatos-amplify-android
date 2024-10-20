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
import androidx.core.content.ContextCompat
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
        if (activeGraphs.containsKey(graphTitle)) {
            // Remove the graph, title, and time scale if it already exists
            val graph = activeGraphs.remove(graphTitle)
            graphContainer.removeView(graph)
            val titleView = graphContainer.findViewWithTag<TextView>("title_$graphTitle")
            val buttonLayout = graphContainer.findViewWithTag<LinearLayout>("layout_$graphTitle")
            graphContainer.removeView(titleView)
            graphContainer.removeView(buttonLayout)
            button.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        } else {
            // Add the graph if it doesn't exist
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
            setUpGraph(graphTitle, newGraph)

            button.setBackgroundColor(ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark))
        }
    }

    // Function to load and display the graph data
    private fun setUpGraph(graphTitle: String, graph: LineChart) {
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
                return
            }

            // Read each line in the CSV and add to the data list
            reader.forEachLine { line ->
                val tokens = line.split(",")
                if (tokens.size > columnIndex) {
                    val dayFraction = tokens[dayFractionIndex].toFloatOrNull() ?: return@forEachLine
                    val value = tokens[columnIndex].toFloatOrNull() ?: return@forEachLine
                    Log.d("CSVReader", "Day Fraction: $dayFraction, Value: $value") // Log values
                    data.add(Entry(dayFraction, value))
                }
            }

            if (data.isEmpty()) {
                Log.d("Graph", "No data was read from the CSV.")
                return
            }

            // Create a LineDataSet and set it to the graph
            val dataSet = LineDataSet(data, "Graph Data for $graphTitle")
            dataSet.color = ContextCompat.getColor(requireContext(), android.R.color.holo_blue_dark)
            dataSet.setCircleColor(Color.BLACK)
            dataSet.valueTextColor = Color.BLACK

            val lineData = LineData(dataSet)
            graph.data = lineData

            // Configure X-Axis
            val xAxis: XAxis = graph.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)

            // Set proper layout params for graph
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500 // Adjust height as needed
            )
            layoutParams.setMargins(0, 16, 0, 16)
            graph.layoutParams = layoutParams

            // Create time scale buttons
            createTimeScaleButtons(graphTitle, graph)

            graph.invalidate()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Graph", "Error reading CSV: ${e.message}")
        }
    }

    // Function to create time scale buttons for each graph
    private fun createTimeScaleButtons(graphTitle: String, graph: LineChart) {
        val buttonLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            tag = "layout_$graphTitle"
        }

        val timeScales = listOf("24H", "7D", "1M", "YTD")
        timeScales.forEach { timeScale ->
            val button = Button(requireContext()).apply {
                text = timeScale
                setOnClickListener { updateTimeScale(timeScale, graph) }
            }
            buttonLayout.addView(button)
        }

        graphContainer.addView(buttonLayout)
    }

    // Function to update time scale based on button clicked
    private fun updateTimeScale(timeScale: String, graph: LineChart) {
        val maxDays = when (timeScale) {
            "24H" -> 1
            "7D" -> 7
            "1M" -> 30
            "YTD" -> 365
            else -> 7
        }
        graph.xAxis.axisMaximum = maxDays.toFloat()
        graph.invalidate()  // Redraw the graph with the updated time scale
    }
}