package com.example.aguadatosapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class GraphsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_graphs, container, false)
        val graph1: LineChart = view.findViewById(R.id.graph1)
        val graph2: LineChart = view.findViewById(R.id.graph2)
        val graph3: LineChart = view.findViewById(R.id.graph3)
        val graph4: LineChart = view.findViewById(R.id.graph4)
        val graph5: LineChart = view.findViewById(R.id.graph5)
        val graph6: LineChart = view.findViewById(R.id.graph6)
        val graph7: LineChart = view.findViewById(R.id.graph7)
        val graph8: LineChart = view.findViewById(R.id.graph8)

        //set up graphs
        setUpGraph(graph1)
        setUpGraph(graph2)
        setUpGraph(graph3)
        setUpGraph(graph4)
        setUpGraph(graph5)
        setUpGraph(graph6)
        setUpGraph(graph7)
        setUpGraph(graph8)

        return view
    }

    private fun setUpGraph(graph: LineChart) {
        val entries = ArrayList<Entry>()
        //random data points
        for (i in 0..10) {
            entries.add(Entry(i.toFloat(), (Math.random() * 100).toFloat()))
        }
        val dataSet = LineDataSet(entries, "Graph Data")
        val lineData = LineData(dataSet)
        graph.data = lineData
        graph.invalidate()
    }
}