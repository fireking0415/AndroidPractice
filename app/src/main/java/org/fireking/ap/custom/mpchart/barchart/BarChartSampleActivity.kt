package org.fireking.ap.custom.mpchart.barchart

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun initBarChartV1() {
        barChartV1.setBackgroundColor(Color.parseColor("#80F14400"))
        barChartV1.setNoDataText(null)

        val barDataList = ArrayList<BarEntry>()
        barDataList.add(BarEntry(1F, 55F))
        barDataList.add(BarEntry(2F, 45F))
        barDataList.add(BarEntry(3F, 78F))
        barDataList.add(BarEntry(4F, 67F))
        barDataList.add(BarEntry(5F, 76F))
        val barDataSet = BarDataSet(barDataList, "demo1")
        val barData = BarData()
        barData.addDataSet(barDataSet)
        barChartV1.data = barData
        barChartV1.invalidate()
    }
}