package com.zd.study.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CustomStaggeredLayoutManager : RecyclerView.LayoutManager() {

    private val leftColumnViews = mutableListOf<View>()
    private val rightColumnViews = mutableListOf<View>()
    private var leftColumnHeight = 0
    private var rightColumnHeight = 0

    fun getLeftHeight(): Int {
        return leftColumnHeight
    }

    fun getRightHeight(): Int {
        return rightColumnHeight
    }
    fun getRealHeight(): Int{
        return Math.max(leftColumnHeight, rightColumnHeight)
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        if (itemCount == 0) {
            detachAndScrapAttachedViews(recycler!!)
            return
        }

        detachAndScrapAttachedViews(recycler!!)

        leftColumnViews.clear()
        rightColumnViews.clear()
        leftColumnHeight = 0
        rightColumnHeight = 0

        var leftColumn = true

        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)

            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)
            Log.d(
                "debug_rv",
                "onLayoutChildren: width = $width, height = $height, leftColumnHeight = $leftColumnHeight, rightColumnHeight = $rightColumnHeight"
            )
            if (leftColumn) {
//                leftColumnViews.add(view)
                layoutDecorated(view, 0, leftColumnHeight, width / 2, leftColumnHeight + height)
                leftColumnHeight += height
            } else {
//                rightColumnViews.add(view)
                layoutDecorated(
                    view,
                    width / 2,
                    rightColumnHeight,
                    width,
                    rightColumnHeight + height
                )
                rightColumnHeight += height
            }

            leftColumn = !leftColumn
        }
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        Log.d("debug_rv", "scrollVerticallyBy =====")
        val travel = if (dy + leftColumnHeight > height) height - leftColumnHeight else dy
        offsetChildrenVertical(-travel)
        return travel
    }

}

