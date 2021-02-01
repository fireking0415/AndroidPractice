package org.fireking.library.mpchart.linechart

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import org.fireking.ap.databinding.ActivityMPChartBasicV1Binding
import org.fireking.library.kotlin.ext.intentFor
import kotlin.random.Random

class LineChartActivity : AppCompatActivity() {

    private var viewBinding: ActivityMPChartBasicV1Binding? = null

    companion object {
        @JvmStatic
        fun start(context: Context) {
            context.startActivity(context.intentFor<LineChartActivity>())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMPChartBasicV1Binding.inflate(LayoutInflater.from(this))
        setContentView(viewBinding?.root)
        drawLineChartView1()
        drawLineChartView2()
        drawLineChartView3()

        viewBinding?.btnScrollY?.setOnClickListener {
            viewBinding?.lineChartView2?.viewPortHandler?.contentTop()
            viewBinding?.lineChartView2?.viewPortHandler?.setMaximumScaleY(0.9F)
            viewBinding?.lineChartView2?.invalidate()
        }
    }

    private fun drawLineChartView3() {
        viewBinding?.lineChartView3?.setBackgroundColor(Color.parseColor("#80F14400"))
        viewBinding?.lineChartView3?.xAxis?.position = XAxis.XAxisPosition.BOTTOM
    }

    private fun drawLineChartView2() {
        //设置lineChart基本属性
        viewBinding?.lineChartView2?.description?.text = ""
        viewBinding?.lineChartView2?.description?.textColor = Color.RED
        viewBinding?.lineChartView2?.description?.textSize = 16F
        viewBinding?.lineChartView2?.setNoDataText("无可用数据")  //没数据的时候展示
        viewBinding?.lineChartView2?.setDrawBorders(false)// 是否绘制边框
        viewBinding?.lineChartView2?.animateX(500)  //x轴动画
        viewBinding?.lineChartView2?.setTouchEnabled(true)  //设置支持触摸
        viewBinding?.lineChartView2?.setScaleEnabled(true)  //是否支持缩放，默认true
        viewBinding?.lineChartView2?.isDragEnabled = true //是否支持拖拽
        viewBinding?.lineChartView2?.isScaleXEnabled = true //是否支持x轴缩放
        viewBinding?.lineChartView2?.isScaleYEnabled = true //是否支持y轴缩放
        viewBinding?.lineChartView2?.setPinchZoom(true)  //是否支持x、y轴同时缩放，默认为false
        viewBinding?.lineChartView2?.isDoubleTapToZoomEnabled = true //是否支持双击屏幕放大，默认true
        viewBinding?.lineChartView2?.isHighlightPerDragEnabled = true  //是否拖拽高亮线（数据点和坐标的提示线），默认true
        viewBinding?.lineChartView2?.isDragDecelerationEnabled = true //拖拽滚动时，手放开是否会持续滚动，默认true，false是拖动到那算那
        viewBinding?.lineChartView2?.dragDecelerationFrictionCoef = 0.99F //和上面的属性相配合，配置持续滚动的速度快慢，区间【0-1】0表示立即停止

        //绘制x轴
        val xAxis = viewBinding?.lineChartView2?.xAxis
        xAxis?.position = XAxis.XAxisPosition.BOTTOM //设置x轴位置
        xAxis?.axisMinimum = 0F //设置x轴最小值
        xAxis?.textSize = 14F
        xAxis?.textColor = Color.RED
        xAxis?.isEnabled = true //是否显示x轴是否禁用
        xAxis?.setDrawLabels(true) //设置x轴标签展示
        xAxis?.setDrawGridLines(true) //设置设置x轴上每个对应的点的竖线
        xAxis?.enableAxisLineDashedLine(2F, 2F, 2F) //竖线 -虚线样式
        xAxis?.labelRotationAngle = 30F //设置x轴标签的旋转角度

        //绘制y轴
        val yAxisLeft = viewBinding?.lineChartView2?.axisLeft
        yAxisLeft?.textSize = 14F
        yAxisLeft?.axisMinimum = 0F
        val yAxisRight = viewBinding?.lineChartView2?.axisRight
        yAxisRight?.isEnabled = false

        val lineDataEntities = ArrayList<Entry>()
        val random = Random(10)
        for (index in 1 until 20) {
            lineDataEntities.add(Entry(index.toFloat(), random.nextFloat()))
        }

        val lineDataSet = LineDataSet(lineDataEntities, "股票走势曲线")
        lineDataSet.highLightColor = Color.RED  //设置高亮线的颜色
        lineDataSet.color = Color.BLACK  //折线颜色
        lineDataSet.setCircleColor(Color.BLUE)  //设置交点的圆圈颜色
        lineDataSet.setDrawCircles(false)  //是否绘制交点
        lineDataSet.setDrawValues(false)  //是否显示交叉点的数值
        lineDataSet.valueTextColor = Color.CYAN  //设置交叉点上的值的颜色
        lineDataSet.valueTextSize = 14F //设置交叉点上值的字体大小
        lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER //设置平滑曲线
        lineDataSet.fillColor = Color.parseColor("#F14400")
        lineDataSet.setDrawFilled(true)

        val lineData = LineData(lineDataSet)
        viewBinding?.lineChartView2?.data = lineData
        viewBinding?.lineChartView2?.invalidate()
    }

    private fun drawLineChartView1() {
        val lineEntities = ArrayList<Entry>()
        val lineTwoEntities = ArrayList<Entry>()
        for (index in 0 until 12) {
            lineEntities.add(Entry(index.toFloat(), Random.nextInt(300).toFloat()))
            lineTwoEntities.add(Entry(index.toFloat(), Random.nextInt(300).toFloat()))
        }
        val lineDataSet = LineDataSet(lineEntities, "One")
        lineDataSet.setCircleColor(Color.parseColor("#67BCFF"))  //设置链接点的颜色
        lineDataSet.color = Color.parseColor("#67BCFF")  //设置线的颜色
        lineDataSet.setDrawCircleHole(false)  //设置绘制点是空心还是实心，默认true，实心为false
        lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)

        val lineTwoDataSet = LineDataSet(lineTwoEntities, "Two")
        lineTwoDataSet.isHighlightEnabled = true  //设置那一条线在最上面
        lineTwoDataSet.setCircleColor(Color.parseColor("#F14400"))
        lineTwoDataSet.color = Color.parseColor("#F14400")
        lineTwoDataSet.setDrawCircles(false)  //是否绘制链接点，默认为true
        lineTwoDataSet.setDrawValues(false) //是否绘制链接点的文字，默认为true
        lineTwoDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER

        //控制坐标轴属性
        val xAxis = viewBinding?.lineChartView?.xAxis
        xAxis?.position = XAxis.XAxisPosition.BOTTOM //设置x轴的位置
        xAxis?.setLabelCount(lineEntities.size, true)
        xAxis?.granularity = 1F //设置x轴坐标间的最小间距
        xAxis?.axisMaximum = (lineEntities.size - 1).toFloat()
        xAxis?.axisMinimum = 0F
        xAxis?.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                return "${lineEntities[value.toInt()].x}天"
            }
        }
        xAxis?.isAvoidFirstLastClippingEnabled

        val yAxisRight = viewBinding?.lineChartView?.axisRight
        yAxisRight?.isEnabled = false
        val yAxisLeft = viewBinding?.lineChartView?.axisLeft
        yAxisLeft?.axisMinimum = 0F
        yAxisLeft?.setDrawZeroLine(true)
        yAxisLeft?.mAxisMaximum = 300F

        val lineData = LineData()
        lineData.addDataSet(lineDataSet)
        lineData.addDataSet(lineTwoDataSet)
        lineData.setDrawValues(false)

        viewBinding?.lineChartView?.description?.isEnabled = false
        viewBinding?.lineChartView?.setDrawGridBackground(true)
        viewBinding?.lineChartView?.setDrawBorders(true)

        viewBinding?.lineChartView?.marker = SimpleMarkerView(this)
        viewBinding?.lineChartView?.renderer = SimpleLineChartRenderer(
            this,
            viewBinding?.lineChartView,
            viewBinding?.lineChartView?.animator,
            viewBinding?.lineChartView?.viewPortHandler
        )
        viewBinding?.lineChartView?.animateX(1500)
        viewBinding?.lineChartView?.isScaleXEnabled = true
        viewBinding?.lineChartView?.isScaleYEnabled = false

        viewBinding?.lineChartView?.data = lineData
        viewBinding?.lineChartView?.invalidate()
    }
}