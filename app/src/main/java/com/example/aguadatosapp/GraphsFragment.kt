package com.example.aguadatosapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.example.aguadatosapp.R

class GraphsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        Log.d("FragmentGraphs", "GraphsFragment is created")

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_graphs, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val lineChart: LineChart = view.findViewById(R.id.lineChart)

        val entries = ArrayList<Entry>()
        entries.add(Entry(0f, 1f))  // Point (0,1)
        entries.add(Entry(1f, 2f))  // Point (1,2)

        // dataset
        val dataSet = LineDataSet(entries, "Data Set Label")

        // Set X-axis labels
        lineChart.xAxis.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "cats"
            }
        }

        // Set Y-axis labels
        lineChart.axisLeft.valueFormatter = object : com.github.mikephil.charting.formatter.ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "dogs"
            }
        }

        // Set data
        val lineData = LineData(dataSet)
        lineChart.data = lineData

        // Set chart title
        lineChart.description.text = "meow meow meow"

        // Refresh chart
        lineChart.invalidate()  // Refresh chart with new data

    }
    override fun onResume() {
        super.onResume()
        Log.d("FragmentGraphs", "GraphsFragment is now visible")
    }
}