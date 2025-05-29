package com.bankcomm.module.biz.home.floor.moreexplore

import android.os.Build
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.zd.study.utils.DisplayUtil
import java.util.Arrays


class CustomStaggeredFoldOpenLayoutManager(column: Int, recyclerMarginLeft: Int, recyclerMarginRight: Int, itemMargin: Int): RecyclerView.LayoutManager() {

    private var mColumnHeight: IntArray = IntArray(column){0}
    //默认两列
    private var mColumn: Int = column
    private var mRecyclerMarginLeft: Int = recyclerMarginLeft
    private var mRecyclerMarginRight: Int = recyclerMarginRight
    private var mItemMargin: Int = itemMargin

    fun getRealHeight(): Int {
        return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Arrays.stream(mColumnHeight).max().asInt
        } else {
            getMax(mColumnHeight)
        }
    }

    private fun getMax(arr: IntArray): Int{
        if (arr.isEmpty()) return 0
        var max = arr[0]
        for( i in arr.indices){
            if (arr[i] > max){
                max = arr[i]
            }
        }
        return  max
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        Log.d("debug_rv", "onLayoutChildren: state = ${state?.itemCount}")
        if (itemCount == 0 || state?.isPreLayout == true || mColumn == 0) {
            return
        }
        detachAndScrapAttachedViews(recycler!!)
        Arrays.fill(mColumnHeight, 0)
        var left = mRecyclerMarginLeft
        for (i in 0 until itemCount) {
            val view = recycler.getViewForPosition(i)
            if (view.visibility == View.GONE)
                continue
            addView(view)
            measureChildWithMargins(view, 0, 0)
            val screenWidth = DisplayUtil.getScreenWidth(view.context) - paddingLeft - paddingRight - mRecyclerMarginLeft - mRecyclerMarginRight - mItemMargin * (mColumn - 1)
            val itemViewWidth = screenWidth / mColumn

            val childViewWidth = getDecoratedMeasuredWidth(view)
            val childViewHeight = getDecoratedMeasuredHeight(view)

            //寻找最短列
            /*val minColumHeight = mColumnHeight.minOrNull() ?:0
            val minColum = mColumnHeight.indexOf(minColumHeight)*/
            if (i % mColumn == 0) {
                left = mRecyclerMarginLeft
            } else {
                left = mRecyclerMarginLeft + (i % mColumn) * itemViewWidth + (i % mColumn) * mItemMargin
            }
            var top = mColumnHeight[i % mColumn]
            if (i / mColumn > 0){
                top += mItemMargin
            }
            val right = left + itemViewWidth
            val bottom = top + childViewHeight

            mColumnHeight[i % mColumn] = bottom
            layoutDecorated(view, left, top, right, bottom)
        }
    }

    override fun canScrollVertically(): Boolean {
        return false
    }

    override fun canScrollHorizontally(): Boolean {
        return false
    }

}

