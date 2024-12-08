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
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class GraphsFragment : Fragment() {

    private lateinit var graphContainerContent: LinearLayout
    private val activeGraphs = mutableMapOf<String, Triple<TextView, LineChart, LinearLayout>>()
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
        graphContainerContent = view.findViewById(R.id.graphContainerContent)

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

        // Set text color of all buttons to black
        listOf(btnPF, btnRWT, btnCoD, btnFWT, btnCWT, btnChD).forEach { button ->
            button.setTextColor(Color.BLACK)
        }

        return view
    }

    // Function to add or remove a graph when a button is clicked
    private fun toggleGraph(graphTitle: String, button: Button) {
        if (overlayTitles.contains(graphTitle)) {
            // Handle overlay charts separately
            if (overlayDataSets.containsKey(graphTitle)) {
                // Remove the specific overlay dataset
                overlayDataSets.remove(graphTitle)
                button.setBackgroundColor(Color.WHITE)
                updateOverlayChart()
            } else {
                // Add the dataset to the overlay chart
                val dataSet = createDataSet(graphTitle, buttonColors[button.id] ?: Color.BLUE)
                if (dataSet != null) {
                    overlayDataSets[graphTitle] = dataSet
                    button.setBackgroundColor(buttonColors[button.id] ?: Color.BLUE)
                    updateOverlayChart()
                }
            }
        } else {
            // Handle individual graphs separately
            if (activeGraphs.containsKey(graphTitle)) {
                // Remove the graph, title, and time scale buttons if it already exists
                val (titleView, graph, buttonLayout) = activeGraphs.remove(graphTitle)!!
                graphContainerContent.removeView(titleView)
                graphContainerContent.removeView(graph)
                graphContainerContent.removeView(buttonLayout)
                button.setBackgroundColor(Color.WHITE)
            } else {
                addGraph(graphTitle, button)
            }
        }
    }

    // Function to add an individual graph with its time scale buttons and title
    private fun addGraph(graphTitle: String, button: Button) {
        val titleView = TextView(requireContext()).apply {
            text = graphTitle
            textSize = 18f
            setTextColor(Color.BLACK)
            tag = "title_$graphTitle"
        }

        val newGraph = LineChart(requireContext())
        val buttonLayout = createTimeScaleButtons(newGraph)

        graphContainerContent.addView(titleView)
        graphContainerContent.addView(newGraph)
        graphContainerContent.addView(buttonLayout)

        activeGraphs[graphTitle] = Triple(titleView, newGraph, buttonLayout)

        setUpGraph(graphTitle, newGraph, buttonColors[button.id] ?: Color.BLUE)
        button.setBackgroundColor(buttonColors[button.id] ?: Color.BLUE)
    }

    // Function to create time scale buttons for each graph
    private fun createTimeScaleButtons(graph: LineChart): LinearLayout {
        val buttonLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(0, 8, 0, 8)
        }

        val timeScales = listOf("12H", "24H", "7D", "1M", "YTD")
        timeScales.forEach { timeScale ->
            val button = Button(requireContext()).apply {
                text = timeScale
                setOnClickListener { updateTimeScale(timeScale, graph) }
                setBackgroundColor(Color.WHITE)
                setTextColor(Color.BLACK)
            }
            buttonLayout.addView(button)
        }
        return buttonLayout
    }

    // Function to create a dataset for overlay graphs with specific color from JSON
    private fun createDataSet(graphTitle: String, color: Int): LineDataSet? {
        val filePath = "datalog_8-7-2024.json"
        try {
            val assetManager = requireContext().assets
            val inputStream = assetManager.open(filePath)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonString = reader.readText()
            reader.close()

            val data = ArrayList<Entry>()
            Log.d("JSONReader", "Opened file: $filePath")

            val jsonArray = JSONArray(jsonString)

            val dayFractionKey = "Day fraction since midnight on 8/7/2024"

            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                if (jsonObject.has(graphTitle) && jsonObject.has(dayFractionKey)) {
                    val dayFraction = jsonObject.getDouble(dayFractionKey).toFloat()
                    val value = jsonObject.getDouble(graphTitle).toFloat()
                    data.add(Entry(dayFraction, value))
                }
            }

            if (data.isEmpty()) {
                Log.d("Graph", "No data was read from the JSON.")
                return null
            }

            val dataSet = LineDataSet(data, graphTitle)
            dataSet.color = color
            dataSet.setCircleColor(Color.BLACK)
            dataSet.valueTextColor = Color.BLACK
            return dataSet
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("Graph", "Error reading JSON: ${e.message}")
            return null
        } catch (e: JSONException) {
            e.printStackTrace()
            Log.e("Graph", "Error parsing JSON: ${e.message}")
            return null
        }
    }

    // Function to update the overlay chart with all selected overlay datasets
    private fun updateOverlayChart() {
        if (overlayDataSets.isEmpty()) {
            activeGraphs["OverlayChart"]?.let { (titleView, chart, buttonLayout) ->
                graphContainerContent.removeView(titleView)
                graphContainerContent.removeView(chart)
                graphContainerContent.removeView(buttonLayout)
            }
            activeGraphs.remove("OverlayChart")
            return
        }

        val overlayChartTriple = activeGraphs.getOrPut("OverlayChart") {
            val titleView = TextView(requireContext()).apply {
                text = "Overlay Chart"
                textSize = 18f
                setTextColor(Color.BLACK)
            }
            val chart = LineChart(requireContext())
            val buttonLayout = createTimeScaleButtons(chart)
            graphContainerContent.addView(titleView)
            graphContainerContent.addView(chart)
            graphContainerContent.addView(buttonLayout)

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500
            )
            layoutParams.setMargins(0, 16, 0, 16)
            chart.layoutParams = layoutParams

            Triple(titleView, chart, buttonLayout)
        }

        val lineData = LineData()
        for (dataSet in overlayDataSets.values) {
            lineData.addDataSet(dataSet)
        }
        overlayChartTriple.second.data = lineData
        overlayChartTriple.second.invalidate()
    }

    private fun updateTimeScale(timeScale: String, graph: LineChart) {
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

    private fun setUpGraph(graphTitle: String, graph: LineChart, color: Int) {
        val dataSet = createDataSet(graphTitle, color)
        if (dataSet != null) {
            val lineData = LineData(dataSet)
            graph.data = lineData

            val xAxis: XAxis = graph.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            graph.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 500
            )
            graph.invalidate()
        }
    }
}
