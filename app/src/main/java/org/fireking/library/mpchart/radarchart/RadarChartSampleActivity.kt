package org.fireking.library.mpchart.radarchart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.Utils
import org.fireking.ap.databinding.ActivityRadarChartSampleBinding
import org.fireking.library.kotlin.ext.intentFor

class RadarChartSampleActivity : AppCompatActivity() {

    private var viewBinding: ActivityRadarChartSampleBinding? = null

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
        viewBinding = ActivityRadarChartSampleBinding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)

        initRadarLayoutV1()
        initRadarLayoutV2()
        initRaderLayoutV3()
    }

    private fun initRaderLayoutV3() {
        viewBinding?.radarChart3?.setBackgroundColor(Color.parseColor("#80fd3a69"))
        viewBinding?.radarChart3?.setNoDataText(null)
        viewBinding?.radarChart3?.description?.isEnabled = false
        viewBinding?.radarChart3?.legend?.isEnabled = false
        viewBinding?.radarChart3?.yAxis?.isEnabled = false
        viewBinding?.radarChart3?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return LABELS[(value % LABELS.size).toInt()]
            }
        }
        viewBinding?.radarChart3?.animateX(1000)
        viewBinding?.radarChart3?.animateY(1000)
        viewBinding?.radarChart3?.yAxis?.axisMinimum = 0F
        val radarDataEntities = ArrayList<RadarEntry>()
        for (index in 0 until 5) {
            radarDataEntities.add(RadarEntry(0F))
        }
        val radarDataSet = RadarDataSet(radarDataEntities, "")
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillColor = Color.parseColor("#F14400")
        radarDataSet.setDrawValues(false)
        radarDataSet.fillAlpha = 255
        radarDataSet.color = Color.parseColor("#F14400")
        val radarData = RadarData(radarDataSet)
        viewBinding?.radarChart3?.data = radarData
        viewBinding?.radarChart3?.isHighlightPerTapEnabled = false
        viewBinding?.radarChart3?.invalidate()
    }

    private fun initRadarLayoutV2() {
        viewBinding?.radarChart2?.setBackgroundColor(Color.parseColor("#80DDFF33"))
        viewBinding?.radarChart2?.setNoDataText(null)
        viewBinding?.radarChart2?.description?.isEnabled = false
        viewBinding?.radarChart2?.legend?.isEnabled = false
        viewBinding?.radarChart2?.yAxis?.isEnabled = false
        viewBinding?.radarChart2?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return LABELS[(value % LABELS.size).toInt()]
            }
        }
        viewBinding?.radarChart2?.animateX(1000)
        viewBinding?.radarChart2?.animateY(1000)
        viewBinding?.radarChart2?.yAxis?.axisMinimum = 0F
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
        viewBinding?.radarChart2?.data = radarData
        viewBinding?.radarChart2?.isHighlightPerTapEnabled = false
        viewBinding?.radarChart2?.invalidate()
    }

    private fun initRadarLayoutV1() {
        viewBinding?.radarChart?.setBackgroundColor(Color.parseColor("#80334455"))
        viewBinding?.radarChart?.setNoDataText(null)
        viewBinding?.radarChart?.description?.isEnabled = false

        val radarDataEntityList = ArrayList<RadarEntry>()
        radarDataEntityList.add(RadarEntry(100F))
        radarDataEntityList.add(RadarEntry(50F))
        radarDataEntityList.add(RadarEntry(75F))
        radarDataEntityList.add(RadarEntry(80F))
        radarDataEntityList.add(RadarEntry(90F))

        val radarDataSet = RadarDataSet(radarDataEntityList, "")
        radarDataSet.setDrawFilled(true)
        radarDataSet.fillColor = Color.parseColor("#F14400")

        val radarData = RadarData(radarDataSet)
        radarData.setDrawValues(false)

        viewBinding?.radarChart?.yAxis?.axisMinimum = 0F
        viewBinding?.radarChart?.yAxis?.axisMaximum = 100F
        viewBinding?.radarChart?.yAxis?.setLabelCount(5, true)
        viewBinding?.radarChart?.yAxis?.setDrawLabels(false)
        viewBinding?.radarChart?.xAxis?.xOffset = Utils.convertDpToPixel(5F)
        viewBinding?.radarChart?.xAxis?.yOffset = Utils.convertDpToPixel(5F)
        val temp = arrayListOf("技术趋势", "价值评估", "交易机会", "舆情分析", "资金流向")
        viewBinding?.radarChart?.xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return temp[(value % temp.size).toInt()]
            }
        }
        viewBinding?.radarChart?.legend?.isEnabled = false

        viewBinding?.radarChart?.data = radarData
        viewBinding?.radarChart?.invalidate()
    }
}