package org.fireking.basic.view.widget

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import org.fireking.ap.R

class LayoutCanvasView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val spaceLayout =
            LayoutInflater.from(context).inflate(R.layout.decoration_space_layout, null)
        spaceLayout.measure(0, 0)
        canvas?.translate(0F, spaceLayout.measuredHeight.toFloat())
        spaceLayout.layout(
            0,
            300,
            measuredWidth,
            300 + spaceLayout.measuredHeight
        )
        spaceLayout.draw(canvas)
    }
}