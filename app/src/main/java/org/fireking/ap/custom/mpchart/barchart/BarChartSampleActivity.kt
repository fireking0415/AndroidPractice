package org.fireking.ap.custom.mpchart.barchart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_bar_chart_sample.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor

class BarChartSampleActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<BarChartSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_chart_sample)

        initBarChartV1()

        btnScroll.setOnClickListener {
            barChartV1.viewPortHandler.setMaximumScaleX(0.5F)
            barChartV1.invalidate()
        }
    }

    private fun initBarChartV1() {
        barChartV1.setBackgroundColor(Color.parseColor("#80F14400"))
        barChartV1.setNoDataText(null)
        barChartV1.legend.isEnabled = false
        barChartV1.axisLeft.isEnabled = false
        barChartV1.axisRight.isEnabled = false

        val barDataList = ArrayList<BarEntry>()
        for (index in 0 until 100) {
            barDataList.add(BarEntry(20 + index.toFloat(), (100 * Math.random()).toFloat()))
        }
        barDataList.add(BarEntry(0F, 0F))
        barDataList.add(BarEntry(150F, 0F))
        val barDataSet = BarDataSet(barDataList, "demo1")
        val barData = BarData()
        barData.addDataSet(barDataSet)
        barChartV1.data = barData
        barChartV1.invalidate()
    }
}