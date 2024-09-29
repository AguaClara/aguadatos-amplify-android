package com.example.aguadatosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.opencsv.CSVReader
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException
import java.io.InputStreamReader
import com.github.mikephil.charting.components.XAxis

class GraphsFragment : Fragment() {

    private lateinit var graph: LineChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_graphs, container, false)
        graph = view.findViewById(R.id.graph)
        val btnGraph1: Button = view.findViewById(R.id.btnPF)
        val btnGraph2: Button = view.findViewById(R.id.btnRWT)
        val btnGraph3: Button = view.findViewById(R.id.btnCoD)
        val btnGraph4: Button = view.findViewById(R.id.btnFWT)
        val btnGraph5: Button = view.findViewById(R.id.btnCWT)
        val btnGraph6: Button = view.findViewById(R.id.btnChD)
        btnGraph1.setOnClickListener { setUpGraph("raw (L/min)") }  //Plant Flow
        btnGraph2.setOnClickListener { setUpGraph("turbidityInfluent (NTU)") }  //Raw Water Turbidity
        btnGraph3.setOnClickListener { setUpGraph("coagulantDose(mg/L) ()") }  //Coagulant Dosage
        btnGraph4.setOnClickListener { setUpGraph("turbidityEffluent (NTU)") }  //Filtered Water Turbidity
        btnGraph5.setOnClickListener { setUpGraph("turbidityClarified (NTU)") }  //Clarified Water Turbidity
        btnGraph6.setOnClickListener { setUpGraph("coagulantDoseController") }  //Chlorine Dosage
        setUpGraph("raw (L/min)")
        return view
    }
    private fun setUpGraph(columnTitle: String) {
        val filePath = "datalog_8-7-2024.csv"
        try {
            val assetManager = requireContext().assets
            val inputStream = assetManager.open(filePath)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val data = ArrayList<Entry>()
            var index = 0f
            Log.d("CSVReader", "Opened file: $filePath")
            val headerLine = reader.readLine()
            Log.d("CSVReader", "Header: $headerLine")
            val headers = headerLine.split(",")
            val columnIndex = headers.indexOf(columnTitle)
            val dayFractionIndex = headers.indexOf("Day fraction since midnight on 8/7/2024")
            if (columnIndex == -1 || dayFractionIndex == -1) {
                Log.e("CSVReader", "Column '$columnTitle' or 'Day fraction' not found in CSV file.")
                return
            }
            Log.d("CSVReader", "Column Index for $columnTitle: $columnIndex, Day Fraction Index: $dayFractionIndex")
            reader.forEachLine { line ->
                val tokens = line.split(",")
                if (tokens.size > columnIndex) {
                    val dayFraction = tokens[dayFractionIndex].toFloatOrNull() ?: return@forEachLine
                    val value = tokens[columnIndex].toFloatOrNull() ?: return@forEachLine
                    Log.d("g", "Day Fraction: $dayFraction, Value: $value")
                    data.add(Entry(dayFraction, value))
                }
            }
            if (data.isEmpty()) {
                Log.d("g", "No data was read from the CSV.")
                return
            }
            Log.d("g", "Data successfully read from CSV.")
            val dataSet = LineDataSet(data, "Graph Data for $columnTitle")
            val lineData = LineData(dataSet)
            val xAxis = graph.xAxis
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            graph.data = lineData
            graph.invalidate()
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e("g", "Error reading CSV: ${e.message}")
        }
    }
}