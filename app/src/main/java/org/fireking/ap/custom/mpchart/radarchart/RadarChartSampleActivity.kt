package org.fireking.ap.custom.mpchart.radarchart

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.Utils
import kotlinx.android.synthetic.main.activity_radar_chart_sample.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor
import kotlin.random.Random

class RadarChartSampleActivity : AppCompatActivity() {

    companion object {

        private val LABELS = arrayListOf(
            "技术趋势", "价值评估", "交易机会", "舆情分析", "资金流向"
        )
        private val ITEMS = arrayListOf(
            50, 42, 30, 60, 87
        )

        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<RadarChartSampleActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_radar_chart_sample)

        initRadarLayoutV1()
        initRadarLayoutV2()
        initRaderLayoutV3()
    }

    private fun initRaderLayoutV3() {
        radarChart3.setBackgroundColor(Color.parseColor("#80fd3a69"))
        radarChart3.setNoDataText(null)
        radarChart3.description.isEnabled = false
        radarChart3.legend.isEnabled = false
        radarChart3.yAxis.isEnabled = false
        radarChart3.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return LABELS[(value % LABELS.size).toInt()]
            }
        }
        radarChart3.animateX(1000)
        radarChart3.animateY(1000)
        radarChart3.yAxis.axisMinimum = 0F
        val radarDataEntities = ArrayList<RadarEntry>()
        for (index in 0 until 5) {
            radarDataEntities.add(RadarEntry(0F))
        }
        val radarDataSet = RadarDataSet(radarDataEntities, "")
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillColor = Color.parseColor("#F14400")
        radarDataSet.setDrawValues(false)
        radarDataSet.color = Color.parseColor("#F14400")
        val radarData = RadarData(radarDataSet)
        radarChart3.data = radarData
        radarChart3.isHighlightPerTapEnabled = false
        radarChart3.invalidate()
    }

    private fun initRadarLayoutV2() {
        radarChart2.setBackgroundColor(Color.parseColor("#80DDFF33"))
        radarChart2.setNoDataText(null)
        radarChart2.description.isEnabled = false
        radarChart2.legend.isEnabled = false
        radarChart2.yAxis.isEnabled = false
        radarChart2.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return LABELS[(value % LABELS.size).toInt()]
            }
        }
        radarChart2.animateX(1000)
        radarChart2.animateY(1000)
        radarChart2.yAxis.axisMinimum = 0F
        val radarDataEntities = ArrayList<RadarEntry>()
        for (index in 0 until 5) {
            radarDataEntities.add(RadarEntry(ITEMS[index].toFloat()))
        }
        val radarDataSet = RadarDataSet(radarDataEntities, "")
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillColor = Color.parseColor("#F14400")
        radarDataSet.setDrawValues(false)
        radarDataSet.color = Color.parseColor("#F14400")
        val radarData = RadarData(radarDataSet)
        radarChart2.data = radarData
        radarChart2.isHighlightPerTapEnabled = false
        radarChart2.invalidate()
    }

    private fun initRadarLayoutV1() {
        radarChart.setBackgroundColor(Color.parseColor("#80334455"))
        radarChart.setNoDataText(null)
        radarChart.description.isEnabled = false

        val radarDataEntityList = ArrayList<RadarEntry>()
        for (index in 0 until 5) {
            radarDataEntityList.add(RadarEntry((Math.random() * 30 + 20).toFloat()))
        }

        val radarDataSet = RadarDataSet(radarDataEntityList, "")
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillColor = Color.parseColor("#F14400")

        val radarData = RadarData(radarDataSet)
        radarData.setDrawValues(false)

        radarChart.yAxis.axisMinimum = 0F
        radarChart.yAxis.setLabelCount(5, false)
        radarChart.yAxis.setDrawLabels(false)
        radarChart.xAxis.xOffset = Utils.convertDpToPixel(5F)
        radarChart.xAxis.yOffset = Utils.convertDpToPixel(5F)
        val temp = arrayListOf("技术趋势", "价值评估", "交易机会", "舆情分析", "资金流向")
        radarChart.xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return temp[(value % temp.size).toInt()]
            }
        }
        radarChart.legend.isEnabled = false

        radarChart.data = radarData
        radarChart.invalidate()
    }
}