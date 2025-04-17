package com.zd.study.adapter

import androidx.recyclerview.widget.RecyclerView
import java.util.Arrays
import kotlin.random.Random

class StaggeredGridLayoutManager : RecyclerView.LayoutManager() {
    // 记录四列当前的高度
    private val columnHeights = IntArray(4) { 0 }

    override fun generateDefaultLayoutParams() =
        RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        if (itemCount == 0) return

        detachAndScrapAttachedViews(recycler)
        Arrays.fill(columnHeights,  0)

        val itemWidth = (width - paddingStart - paddingEnd) / 4

        for (i in 0 until itemCount) {
            // 1. 获取复用视图
            val view = recycler.getViewForPosition(i)
            addView(view)

            // 2. 动态计算高度（此处需替换为实际业务逻辑）
            val itemHeight = 200 + Random.nextInt(300)

            // 3. 寻找最短列
            val minColumn = columnHeights.indexOf(columnHeights.minOrNull()!!)

            // 4. 计算布局位置
            val left = minColumn * itemWidth
            val top = columnHeights[minColumn]
            val right = left + itemWidth
            val bottom = top + itemHeight

            // 5. 更新列高度
            columnHeights[minColumn] = bottom

            // 6. 布局子View
            measureChildWithMargins(view, 0, 0)
            layoutDecorated(view, left, top, right, bottom)
        }
    }

    override fun canScrollVertically() = true

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        // 此处需实现滚动逻辑及视图回收
        return dy
    }
}