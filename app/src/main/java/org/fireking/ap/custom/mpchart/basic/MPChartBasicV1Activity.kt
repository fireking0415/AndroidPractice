package org.fireking.ap.custom.mpchart.basic

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet
import kotlinx.android.synthetic.main.activity_m_p_chart_basic_v1.*
import org.fireking.ap.R
import org.jetbrains.anko.intentFor
import org.joda.time.format.FormatUtils
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

        //更复杂的线图
        drawLineChartView2()
    }

    private fun drawLineChartView2() {
        //设置lineChart基本属性
        lineChartView2.description.text = ""
        lineChartView2.description.textColor = Color.RED
        lineChartView2.description.textSize = 16F
        lineChartView2.setNoDataText("无可用数据")  //没数据的时候展示
        lineChartView2.setDrawBorders(false)// 是否绘制边框
        lineChartView2.animateX(500)  //x轴动画
        lineChartView2.setTouchEnabled(true)  //设置支持触摸
        lineChartView2.setScaleEnabled(true)  //是否支持缩放，默认true
        lineChartView2.isDragEnabled = true //是否支持拖拽
//        lineChartView2.isScaleXEnabled = true //是否支持x轴缩放
//        lineChartView2.isScaleYEnabled = true //是否支持y轴缩放
//        lineChartView2.setPinchZoom(true)  //是否支持x、y轴同时缩放，默认为false
        lineChartView2.isDoubleTapToZoomEnabled = true //是否支持双击屏幕放大，默认true
        lineChartView2.isHighlightPerDragEnabled = true  //是否拖拽高亮线（数据点和坐标的提示线），默认true
        lineChartView2.isDragDecelerationEnabled = true //拖拽滚动时，手放开是否会持续滚动，默认true，false是拖动到那算那
        lineChartView2.dragDecelerationFrictionCoef = 0.99F //和上面的属性相配合，配置持续滚动的速度快慢，区间【0-1】0表示立即停止

        //绘制x轴
        val xAxis = lineChartView2.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM //设置x轴位置
//        xAxis.axisMaximum = 1F //设置x轴最小值
        xAxis.textSize = 14F
        xAxis.textColor = Color.RED
//        xAxis.isEnabled = true //是否显示x轴是否禁用
//        xAxis.setDrawLabels(true) //设置x轴标签展示
//        xAxis.setDrawGridLines(true) //设置设置x轴上每个对应的点的竖线
//        xAxis.enableAxisLineDashedLine(2F, 2F, 2F) //竖线 -虚线样式
//        xAxis.labelRotationAngle = 30F //设置x轴标签的旋转角度
//
//        //绘制y轴
//        val yAxisLeft = lineChartView2.axisLeft
//        yAxisLeft.textSize = 14F
//        yAxisLeft.axisMaximum = 0F
        val yAxisRight = lineChartView2.axisRight
        yAxisRight.isEnabled = false

        val lineDataEntities = ArrayList<Entry>()
        val random = Random(10)
        for (index in 1 until 20) {
            lineDataEntities.add(Entry(index.toFloat(), random.nextFloat()))
        }

        val lineDataSet = LineDataSet(lineDataEntities, "股票走势曲线")
        lineDataSet.highLightColor = Color.RED  //设置高亮线的颜色
        lineDataSet.color = Color.BLACK  //折线颜色
        lineDataSet.setCircleColor(Color.BLUE)  //设置交点的圆圈颜色
        lineDataSet.setDrawCircles(true)  //是否绘制交点
        lineDataSet.setDrawValues(true)  //是否显示交叉点的数值
        lineDataSet.valueTextColor = Color.CYAN  //设置交叉点上的值的颜色
        lineDataSet.valueTextSize = 14F //设置交叉点上值的字体大小
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER //设置平滑曲线

        val lineData = LineData(lineDataSet)
        lineChartView2.data = lineData
        lineChartView2.invalidate()
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