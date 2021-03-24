package com.project.covid19info.ui.dashboard

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.project.covid19info.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import org.eazegraph.lib.models.PieModel
import org.eazegraph.lib.charts.PieChart





class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

     var pieChart:PieChart? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        pieChart = root.findViewById(R.id.piechart)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDate()
    }

    private fun setDate() {
        tvR.text= 40.toString()
        tvPython.text=30.toString()
        tvCPP.text=5.toString()
        tvJava.text=25.toString()
        pieChart?.addPieSlice(
            PieModel(
                "R", tvR.text.toString().toInt().toFloat(),
                Color.parseColor("#5AC7AA")
            )
        )
        pieChart?.addPieSlice(
            PieModel(
                "Python", tvPython.text.toString().toInt().toFloat(),
                Color.parseColor("#9ADCB9")
            )
        )
        pieChart?.addPieSlice(
            PieModel(
                "C++", tvCPP.text.toString().toInt().toFloat(),
                Color.parseColor("#707070")
            )
        )
        pieChart?.addPieSlice(
            PieModel(
                "Java", tvJava.text.toString().toInt().toFloat(),
                Color.parseColor("#D3D3D3")
            )
        )
        pieChart?.addPieSlice(
                    PieModel(
                        "Java", 20.toFloat(),
                        Color.parseColor("#F24E4E")
                    )
                )

        // To animate the pie chart

        // To animate the pie chart
        pieChart?.startAnimation()
    }

}