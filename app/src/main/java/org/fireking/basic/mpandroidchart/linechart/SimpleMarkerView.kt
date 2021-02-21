package org.fireking.basic.mpandroidchart.linechart

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import org.fireking.ap.R

class SimpleMarkerView(context: Context?) :
    MarkerView(context, R.layout.simple_marker_view) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        //这里的e值的是长按位置的数据，hignlight可以获取我们长按位置的x、y坐标
        if (e?.y == 0F || e?.x == 0F) {
            findViewById<TextView>(R.id.tv_off).text = "暂无数据"
        } else {
            findViewById<TextView>(R.id.tv_off).text = "第${e?.x}天支出${e?.y}元"
        }
        super.refreshContent(e, highlight)
    }

    //用来设置marker和长按的时候的指示hignlight的距离
    override fun getOffset(): MPPointF {
        return MPPointF((-width / 2).toFloat(), (-height).toFloat())
    }
}