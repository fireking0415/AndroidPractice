package org.fireking.basic.recyclerview.decoration

import android.view.GestureDetector
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class PowerStickyItemTouchListener(
    private val mRecyclerView: RecyclerView,
    private val mItemDecoration: PowerStickyItemDecoration
) : RecyclerView.OnItemTouchListener {

    private val mGestureDetector: GestureDetector

    init {
        mGestureDetector =
            GestureDetector(mRecyclerView.context, SingleTapDetector(mItemDecoration))
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, event: MotionEvent): Boolean {
        val tapDetectorResponse = mGestureDetector.onTouchEvent(event)
        if (tapDetectorResponse) {
            return true
        }
        if (event.action == MotionEvent.ACTION_DOWN) {
            return mItemDecoration.findHeaderPositionUnder(event.x.toInt(), event.y.toInt()) != -1
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    class SingleTapDetector(private val itemDecoration: PowerStickyItemDecoration) :
        GestureDetector.SimpleOnGestureListener() {

        override fun onSingleTapUp(event: MotionEvent): Boolean {
            val position = itemDecoration.findHeaderPositionUnder(event.x.toInt(), event.y.toInt())
            if (position != -1) {
                if (itemDecoration.findStickyClickView(event.x.toInt(), event.y.toInt())) {
                    itemDecoration.getStickView().performClick()
                }
                itemDecoration.getStickView().onTouchEvent(event)
                return true
            }
            return false
        }

        override fun onDoubleTap(e: MotionEvent?): Boolean {
            return true
        }
    }
}