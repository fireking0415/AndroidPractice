package org.fireking.basic.mpandroidchart.radarchart

import android.content.Context
import android.util.AttributeSet
import com.github.mikephil.charting.charts.RadarChart

class SimpleRadarChart(context: Context?, attrs: AttributeSet?) : RadarChart(context, attrs) {

    override fun init() {
        super.init()
        mXAxisRenderer = SimpleXAxisRendererRadarChart(mViewPortHandler, mXAxis, this)
        mRenderer = SimpleRadarChartRenderer(this, mAnimator, viewPortHandler)
    }
}