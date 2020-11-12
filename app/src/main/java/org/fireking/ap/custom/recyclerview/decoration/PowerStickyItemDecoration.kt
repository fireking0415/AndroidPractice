package org.fireking.ap.custom.recyclerview.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PowerStickyItemDecoration(
    private val context: Context,
    private val decorationHeight: Float = 0.5F,
    private val decorationColor: Int = Color.parseColor("#EEEEEE"),
    private val stickyPosition: Int,
    private val stickyView: View
) :
    RecyclerView.ItemDecoration() {

    private var mDecorationHeight: Int = 0
    private val mDecorationPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRect = Rect()

    init {
        mDecorationHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            decorationHeight,
            context.resources.displayMetrics
        ).toInt()

        mDecorationPaint.color = decorationColor
        mDecorationPaint.style = Paint.Style.FILL
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        //在第三个item位置插入一个宽度布局
        if (parent.getChildAdapterPosition(view) == stickyPosition) {
            stickyView.measure(
                View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
            )
            outRect.top = stickyView.measuredHeight
        } else if (parent.getChildAdapterPosition(view) != 0) {
            //跳过第0个位置，其他数据位置，在顶部插入一个分割线
            outRect.top = mDecorationHeight
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val itemCount = parent.childCount
        for (index in 0 until itemCount) {
            val childView = parent.getChildAt(index)
            if (parent.getChildAdapterPosition(childView) == stickyPosition) {
                onDrawLayoutSpace(childView, canvas, parent, false)
            } else if (parent.getChildAdapterPosition(childView) != 0) {
                onDrawItemSpace(childView, canvas, parent)
            }
        }
    }

    private fun onDrawLayoutSpace(
        childView: View,
        canvas: Canvas,
        parent: RecyclerView,
        isDrawOver: Boolean = true
    ) {
        stickyView.measure(
            View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
        )
        canvas.save()
        if (!isDrawOver) {
            canvas.translate(0F, (childView.top - stickyView.measuredHeight).toFloat())
        }
        stickyView.layout(
            0,
            childView.top - stickyView.measuredHeight,
            parent.measuredWidth,
            childView.top
        )
        stickyView.draw(canvas)
        canvas.restore()
    }

    private fun onDrawItemSpace(
        childView: View,
        canvas: Canvas,
        parent: RecyclerView
    ) {
        mRect.left = 0
        mRect.top = childView.top - mDecorationHeight
        mRect.right = parent.measuredWidth - parent.paddingRight
        mRect.bottom = childView.top
        canvas.drawRect(mRect, mDecorationPaint)
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val childView = parent.getChildAt(0)
        if (parent.getChildAdapterPosition(childView) >= stickyPosition) {
            onDrawLayoutSpace(childView, canvas, parent)
        }
    }

    fun findHeaderPositionUnder(xValue: Int, yValue: Int): Int {
        return -1
    }

    fun findStickyClickView(toInt: Int, toInt1: Int): Boolean {
        return false
    }

    fun getStickView(): View {
        return stickyView
    }
}