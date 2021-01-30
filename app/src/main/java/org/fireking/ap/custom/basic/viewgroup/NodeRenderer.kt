package org.fireking.ap.custom.basic.viewgroup

import android.content.Context
import android.graphics.Canvas
import android.graphics.PointF
import android.util.TypedValue
import android.widget.LinearLayout

abstract class NodeRenderer(context: Context?, private val xMindNode: XMindNode) :
    LinearLayout(context) {

    fun doRender(canvas: Canvas?) {
        measure(0, 0)
        layout(xMindNode.rect.left, xMindNode.rect.top, xMindNode.rect.right, xMindNode.rect.bottom)
        canvas?.save()
        canvas?.translate(xMindNode.rect.left.toFloat(), xMindNode.rect.top.toFloat())
        draw(canvas)
        canvas?.restore()
    }

    fun getLeftHotSpot(): PointF {
        return PointF(
            xMindNode.rect.left.toFloat() + dip(2),
            (xMindNode.rect.top + xMindNode.rect.height() / 2).toFloat()
        )
    }

    fun getRightHotSpot(): PointF {
        return PointF(
            xMindNode.rect.right.toFloat() - dip(2),
            (xMindNode.rect.top + xMindNode.rect.height() / 2).toFloat()
        )
    }

    fun getLeftX(): Float {
        return xMindNode.rect.left.toFloat() + dip(2)
    }

    fun getLeftY(): Float {
        return (xMindNode.rect.top + xMindNode.rect.height() / 2).toFloat()
    }

    fun getRightX(): Float {
        return xMindNode.rect.right.toFloat() - dip(2)
    }

    fun getRightY(): Float {
        return (xMindNode.rect.top + xMindNode.rect.height() / 2).toFloat()
    }

    private fun dip(value: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value.toFloat(),
            resources.displayMetrics
        ).toInt()
    }
}