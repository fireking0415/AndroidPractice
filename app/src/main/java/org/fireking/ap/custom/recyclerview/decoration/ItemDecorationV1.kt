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

class ItemDecorationV1(
    private val context: Context,
    itemSpace: Float,
    private val pinPosition: Int,
    private val layoutResId: Int
) :
    RecyclerView.ItemDecoration() {

    private var spaceHeight: Int = 0
    private val mSpacePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mRect = Rect()

    init {
        spaceHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            itemSpace,
            context.resources.displayMetrics
        ).toInt()

        mSpacePaint.color = Color.parseColor("#F14400")
        mSpacePaint.style = Paint.Style.FILL
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        //在第三个item位置插入一个宽度布局
        if (parent.getChildAdapterPosition(view) == pinPosition) {
            val layout = LayoutInflater.from(context)
                .inflate(layoutResId, parent, false)
            layout.measure(
                View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
            )
            outRect.top = layout.measuredHeight
        } else if (parent.getChildAdapterPosition(view) != 0) {
            //跳过第0个位置，其他数据位置，在顶部插入一个分割线
            outRect.top = spaceHeight
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val itemCount = parent.childCount
        for (index in 0 until itemCount) {
            val childView = parent.getChildAt(index)
            if (parent.getChildAdapterPosition(childView) == pinPosition) {
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
        val spaceLayout = LayoutInflater.from(context)
            .inflate(layoutResId, parent, false)
        spaceLayout.measure(
            View.MeasureSpec.makeMeasureSpec(parent.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(parent.height, View.MeasureSpec.AT_MOST)
        )
        canvas.save()
        if (!isDrawOver) {
            canvas.translate(0F, (childView.top - spaceLayout.measuredHeight).toFloat())
        }
        spaceLayout.layout(
            0,
            childView.top - spaceLayout.measuredHeight,
            parent.measuredWidth,
            childView.top
        )
        spaceLayout.draw(canvas)
        canvas.restore()
    }

    private fun onDrawItemSpace(
        childView: View,
        canvas: Canvas,
        parent: RecyclerView
    ) {
        mRect.left = 0
        mRect.top = childView.top - spaceHeight
        mRect.right = parent.measuredWidth - parent.paddingRight
        mRect.bottom = childView.top
        canvas.drawRect(mRect, mSpacePaint)
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val childView = parent.getChildAt(0)
        if (parent.getChildAdapterPosition(childView) >= pinPosition) {
            onDrawLayoutSpace(childView, canvas, parent)
        }
    }
}