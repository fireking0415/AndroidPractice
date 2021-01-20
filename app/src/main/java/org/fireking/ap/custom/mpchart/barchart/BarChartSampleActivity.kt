package org.fireking.ap.custom.mpchart.barchart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import org.fireking.ap.databinding.ActivityBarChartSampleBinding
import org.jetbrains.anko.intentFor

class BarChartSampleActivity : AppCompatActivity() {

    private var viewBinding: ActivityBarChartSampleBinding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<BarChartSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBarChartSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        initBarChartV1()

        viewBinding?.btnScroll?.setOnClickListener {
            viewBinding?.barChartV1?.viewPortHandler?.setMaximumScaleX(0.5F)
            viewBinding?.barChartV1?.invalidate()
        }
    }

    private fun initBarChartV1() {
        viewBinding?.barChartV1?.setBackgroundColor(Color.parseColor("#80F14400"))
        viewBinding?.barChartV1?.setNoDataText(null)
        viewBinding?.barChartV1?.legend?.isEnabled = false
        viewBinding?.barChartV1?.axisLeft?.isEnabled = false
        viewBinding?.barChartV1?.axisRight?.isEnabled = false

        val barDataList = ArrayList<BarEntry>()
        for (index in 0 until 100) {
            barDataList.add(BarEntry(20 + index.toFloat(), (100 * Math.random()).toFloat()))
        }
        barDataList.add(BarEntry(0F, 0F))
        barDataList.add(BarEntry(150F, 0F))
        val barDataSet = BarDataSet(barDataList, "demo1")
        val barData = BarData()
        barData.addDataSet(barDataSet)
        viewBinding?.barChartV1?.data = barData
        viewBinding?.barChartV1?.invalidate()
    }
}