package org.fireking.basic.textview.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View


class CanvasDrawTextSample(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.textSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            55F,
            context?.resources?.displayMetrics
        )
        paint.style = Paint.Style.FILL_AND_STROKE
        paint.color = Color.parseColor("#80334555")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val fontMetrics = paint.fontMetrics
        val baseline = fontMetrics.bottom + kotlin.math.abs(fontMetrics.top) - fontMetrics.descent
        canvas?.save()
        //将坐标零点y值移动到baseline
        canvas?.translate(
            0F,
            baseline
        )
        paint.color = Color.BLUE
        val textLabel = "Hello,Python"
        canvas?.drawText(textLabel, 0F, 0F, paint)
        paint.style = Paint.Style.STROKE
        //绘制top
        paint.color = Color.BLUE
        canvas?.drawLine(0F, fontMetrics.top, width.toFloat(), fontMetrics.top, paint)
        paint.color = Color.YELLOW
        //绘制ascent
        canvas?.drawLine(0F, fontMetrics.ascent, width.toFloat(), fontMetrics.ascent, paint)
        paint.color = Color.GRAY
        //绘制descent
        canvas?.drawLine(0F, fontMetrics.descent, width.toFloat(), fontMetrics.descent, paint)
        paint.color = Color.parseColor("#F15500")
        //绘制bottom
        canvas?.drawLine(0F, fontMetrics.bottom, width.toFloat(), fontMetrics.bottom, paint)
        //绘制baseline
        paint.color = Color.parseColor("#8080FF")
        paint.pathEffect = DashPathEffect(floatArrayOf(10F, 10F), 0F)
        canvas?.drawLine(0F, 0F, width.toFloat(), 0F, paint)
        canvas?.restore()
    }
}