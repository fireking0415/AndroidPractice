package org.fireking.ap.custom.mpchart.basic

import android.content.Context
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import kotlinx.android.synthetic.main.simple_marker_view.view.*
import org.fireking.ap.R

class SimpleMarkerView(context: Context?) :
    MarkerView(context, R.layout.simple_marker_view) {

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        if (e?.y == 0F || e?.x == 0F) {
            tv_off.text = "暂无数据"
        } else {
            tv_off.text = "第${e?.x}天支出${e?.y}元"
        }
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF((-width / 2).toFloat(), (-height).toFloat())
    }
}