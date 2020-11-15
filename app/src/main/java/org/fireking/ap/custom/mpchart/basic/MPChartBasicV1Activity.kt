package org.fireking.ap.custom.mpchart.basic

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import kotlinx.android.synthetic.main.activity_m_p_chart_basic_v1.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor
import kotlin.random.Random

class MPChartBasicV1Activity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<MPChartBasicV1Activity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_m_p_chart_basic_v1)

        //饼图
        drawPieChartView()

        //线图
        drawLineChartView()
    }

    private fun drawLineChartView() {
        val lineEntities = ArrayList<Entry>()
        for (index in 0 until 12) {
            lineEntities.add(Entry(index.toFloat(), Random.nextInt(300).toFloat()))
        }
        val lineDataSet = LineDataSet(lineEntities, "label")
        lineChartView.data = LineData(lineDataSet).apply {
            setDrawValues(true)
        }
        lineChartView.invalidate()
    }

    private fun drawPieChartView() {
        val pieEntities = ArrayList<PieEntry>()
        pieEntities.add(PieEntry(30F, "aaa"))
        pieEntities.add(PieEntry(70F, "bbb"))
        pieEntities.add(PieEntry(50F, "ccc"))

        val pieDataSet = PieDataSet(pieEntities, "label")
        pieDataSet.colors = arrayListOf(
            Color.parseColor("#334455"),
            Color.parseColor("#F14400"),
            Color.parseColor("#00FF33")
        )

        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)

        pieChatView.data = pieData
        pieChatView.invalidate()
    }
}