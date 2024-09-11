package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

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

        btnGraph1.setOnClickListener { setUpGraph("Plant Flow") }
        btnGraph2.setOnClickListener { setUpGraph("Raw Water Turbidity") }
        btnGraph3.setOnClickListener { setUpGraph("Coagulant Dosage") }
        btnGraph4.setOnClickListener { setUpGraph("Filtered Water Turbidity") }
        btnGraph5.setOnClickListener { setUpGraph("Clarified Water Turbidity") }
        btnGraph6.setOnClickListener { setUpGraph("Chlorine Dosage") }

        setUpGraph("Plant Flow")

        return view
    }

    // Function to set up graph with data
    private fun setUpGraph(graphName: String) {
        val data = arrayListOf(Entry(1f, 1f), Entry(2f, 2f))
        val dataSet = LineDataSet(data, "$graphName Data")
        val lineData = LineData(dataSet)
        graph.data = lineData
        graph.invalidate()
    }
}