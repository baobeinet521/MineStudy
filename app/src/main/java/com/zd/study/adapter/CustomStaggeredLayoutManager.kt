package com.zd.study.adapter

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zd.study.utils.DisplayUtil


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
            val screenWidth = DisplayUtil.getScreenWidth(view.context)
            Log.d("debug_rv", "onLayoutChildren: screenWidth = $screenWidth")
            measureChildWithMargins(view, 0, 0)
            val width = getDecoratedMeasuredWidth(view)
            val height = getDecoratedMeasuredHeight(view)
            Log.d(
                "debug_rv",
                "onLayoutChildren: width = $width, height = $height, leftColumnHeight = $leftColumnHeight, rightColumnHeight = $rightColumnHeight"
            )
            if (leftColumn) {
//                leftColumnViews.add(view)
                if (leftColumnHeight > 0){
                    leftColumnHeight += DisplayUtil.dpToPx(view.context, 9f)
                }
                layoutDecorated(view, 0, leftColumnHeight, width, leftColumnHeight + height)
                leftColumnHeight += height
            } else {
//                rightColumnViews.add(view)
                if (rightColumnHeight > 0){
                    rightColumnHeight += DisplayUtil.dpToPx(view.context, 9f)
                }
                layoutDecorated(
                    view,
                    width + DisplayUtil.dpToPx(view.context, 9f),
                    rightColumnHeight,
                    width * 2 + DisplayUtil.dpToPx(view.context, 5f),
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

