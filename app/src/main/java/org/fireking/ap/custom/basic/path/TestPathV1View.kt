package org.fireking.ap.custom.basic.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class TestPathV1View(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.style = Paint.Style.STROKE
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val path = Path()
        path.moveTo(50F, 50F)
        val rectF = RectF(100F, 100F, 200F, 200F)
        path.arcTo(rectF, 0F, 359F, true)
        canvas?.drawPath(path, paint)
    }
}