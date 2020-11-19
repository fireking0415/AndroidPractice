package org.fireking.ap.custom.mpchart.radarchart

import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.PathEffect
import android.util.Log
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.renderer.RadarChartRenderer
import com.github.mikephil.charting.utils.MPPointF
import com.github.mikephil.charting.utils.Utils
import com.github.mikephil.charting.utils.ViewPortHandler

class SimpleRadarChartRenderer(
    chart: RadarChart?,
    animator: ChartAnimator?,
    viewPortHandler: ViewPortHandler?
) : RadarChartRenderer(chart, animator, viewPortHandler) {

    override fun drawWeb(c: Canvas?) {
        val sliceAngle = mChart.sliceAngle
        val factor = mChart.factor
        val rotationAngle = mChart.rotationAngle
        val center = mChart.centerOffsets
        mWebPaint.strokeWidth = mChart.webLineWidth
        mWebPaint.color = mChart.webColor
        mWebPaint.alpha = mChart.webAlpha
        mWebPaint.pathEffect = DashPathEffect(floatArrayOf(4F, 4F), 0F)

        val xIncrements = 1 + mChart.skipWebLineCount
        val maxEntryCount = mChart.data.maxEntryCountSet.entryCount

        val p: MPPointF = MPPointF.getInstance(0f, 0f)
        var i = 0
        while (i < maxEntryCount) {
            Utils.getPosition(
                center, mChart.yRange * factor, sliceAngle * i + rotationAngle, p
            )
            c?.drawLine(center.x, center.y, p.x, p.y, mWebPaint)
            i += xIncrements
        }
        MPPointF.recycleInstance(p)

        val labelCount = mChart.yAxis.mEntryCount
        val p1out = MPPointF.getInstance(0f, 0f)
        val p2out = MPPointF.getInstance(0f, 0f)

        //绘制中间边框
        var dist = 0F
        drawXWeb(dist, center, sliceAngle, rotationAngle, p1out, p2out, c)

        mWebPaint.strokeWidth = mChart.webLineWidthInner
        mWebPaint.color = mChart.webColorInner
        mWebPaint.alpha = mChart.webAlpha
        mWebPaint.pathEffect = PathEffect()

        //绘制最外层边框
        dist = (mChart.yAxis.mEntries[labelCount - 1] - mChart.yChartMin) * factor
        drawXWeb(dist, center, sliceAngle, rotationAngle, p1out, p2out, c)
        MPPointF.recycleInstance(p1out)
        MPPointF.recycleInstance(p2out)
    }

    /**
     * 绘制水平方向网格
     */
    private fun drawXWeb(
        dist: Float,
        center: MPPointF,
        sliceAngle: Float,
        rotationAngle: Float,
        p1out: MPPointF,
        p2out: MPPointF,
        c: Canvas?
    ) {
        for (index in 0 until mChart.data.entryCount) {
            Utils.getPosition(
                center, dist, sliceAngle * index + rotationAngle, p1out
            )
            Utils.getPosition(
                center, dist, sliceAngle * (index + 1) + rotationAngle, p2out
            )
            c!!.drawLine(p1out.x, p1out.y, p2out.x, p2out.y, mWebPaint)
        }
    }
}