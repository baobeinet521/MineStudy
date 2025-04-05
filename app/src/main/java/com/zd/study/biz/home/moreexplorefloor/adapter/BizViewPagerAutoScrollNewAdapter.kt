package com.zd.study.biz.home.moreexplorefloor.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.zd.study.R
import com.zd.study.adapter.BizViewpageAdapter
import com.zd.study.autoscrollview.AutoScrollViewPager
import com.zd.study.biz.home.moreexplorefloor.model.BizCardData
import com.zd.study.utils.DisplayUtil


class BizViewPagerAutoScrollNewAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val mContext: Context? = context
    var bannerData: MutableList<BizCardData>? = null

    // 每个页面之间的延迟时间（毫秒）
    private val DELAY_TIME_MS: Long = 3000

    private val viewPagerHandler = Handler(Looper.getMainLooper())
    private lateinit var viewPagerRunnable: Runnable

    class ViewpageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var layout: FrameLayout = itemView.findViewById(R.id.more_explore_item_layout)
        var title: TextView = itemView.findViewById(R.id.biz_ViewPager_title)
//        var viewPager: ViewPager2 = itemView.findViewById(R.id.biz_ViewPager)
        var viewPager1: AutoScrollViewPager<BizViewpagerAutoScrollAdapter> = itemView.findViewById(R.id.biz_ViewPager1)
        var indicatorLayout: TabLayout = itemView.findViewById(R.id.indicator_layout)
    }

    fun setData(data: MutableList<BizCardData>?) {
        bannerData = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewpageViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.biz_auto_scroll_layout, parent, false)
//        BizViewpageItemLayoutBinding.inflate(inflater,  parent, false)
        return ViewpageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var layoutParams = (holder as ViewpageViewHolder).layout.layoutParams
        mContext?.let {
            layoutParams.width =
                (DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dpToPx(mContext, 39f)) / 2
            if (position == 0) {
                layoutParams.height = DisplayUtil.dpToPx(mContext, 200f)
            }
            if (position == 1) {
                layoutParams.height = DisplayUtil.dpToPx(mContext, 180f)
            }
            if (position == 2) {
                layoutParams.height = DisplayUtil.dpToPx(mContext, 247f)
            }
            if (position == 3) {
                layoutParams.height = DisplayUtil.dpToPx(mContext, 180f)
            }
            if (position == 4) {
                layoutParams.height = DisplayUtil.dpToPx(mContext, 200f)
            }
            if (position == 5) {
                layoutParams.height = DisplayUtil.dpToPx(mContext, 280f)
            }

            (holder as ViewpageViewHolder).layout.layoutParams = layoutParams
        }

        holder.title.text = "列表中的第$position 位"

       /* val adapter = BizViewpageAutoAdapter(mContext)
        adapter.setData(bannerData?.get(position)?.dataList)
        holder.viewPager1.setAdapter(adapter)
        holder.viewPager1.isAutoPlay = false
        holder.viewPager1.isNestedScrollingEnabled = false
        holder.viewPager1.setDotGravity(AutoScrollViewPager.CENTER)*/



//        var bizViewpageAdapter = BizViewpageAdapter(mContext)
//        bizViewpageAdapter.setData(bannerData?.get(position)?.scrollData)
//        holder.viewPager.adapter = bizViewpageAdapter
//        var currentItem = holder.viewPager.currentItem
////        updateList(bannerData?.get(position)?.scrollData, bizViewpageAdapter)
//
//        bizViewpageAdapter.updateAllList(bannerData?.get(position)?.scrollData)
//        var size = bannerData?.get(position)?.scrollData?.size ?: 0
//        // 超过一张才需要轮播
//        if (size > 1) {
//            // 默认展示第一张图
//            holder.viewPager.setCurrentItem(1, false)
//            // 初始化下方圆点指示器
//            holder.indicatorLayout.removeAllTabs()
//
//            for (i in 0 until size) {
//                val tab = holder.indicatorLayout.newTab()
//                tab.view.isEnabled = false
//                holder.indicatorLayout.addTab(tab)
//            }
//        } else {
//            holder.indicatorLayout.removeAllTabs()
//        }
//
//
//        val pageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
//            private var lastPage = -1
//            override fun onPageSelected(position: Int) {
//                // 参上
//                // 当前ViewPager切换时，同时计算tab应该如何切换
//                val total = (holder.viewPager.adapter?.itemCount ?: 0)
//                val curPosition = if (total > 1 && position >= 1 && position <= total - 2) {
//                    position - 1
//                } else {
//                    null
//                }
//                if (curPosition != null && lastPage != curPosition) {
////                    listener?.invoke(curPosition)
//                    holder.indicatorLayout.getTabAt(curPosition)?.select()
//                    lastPage = curPosition
//                }
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//                // 参上
//                // 当滑动到最右边时，需要移动到index1的位置
//                val total = (holder.viewPager.adapter?.itemCount ?: 0)
//                if (state == ViewPager2.SCROLL_STATE_IDLE && total > 1 && holder.viewPager.currentItem == total - 1) {
//                    holder.viewPager.setCurrentItem(1, false)
//                }
//
//                // 当滑动到最左边的时候，需要移动到index = total-2的位置
//                if (state == ViewPager2.SCROLL_STATE_IDLE && total > 1 && holder.viewPager.currentItem == 0) {
//                    holder.viewPager.setCurrentItem(total - 2, false)
//                }
//
//            }
//        }
//        holder.viewPager.registerOnPageChangeCallback(pageChangeCallback)
//        viewPagerRunnable = object : Runnable {
//            override fun run() {
//                holder.viewPager.apply {
//                    if (currentItem + 1 < (bizViewpageAdapter?.itemCount ?: 0)) {
//                        setCurrentItem(currentItem + 1, true)
//                    }
//                }
//                viewPagerHandler.postDelayed(this, DELAY_TIME_MS)
//            }
//        }
    }


    /**
     * @param data List<BannerData> : 更新的全量数据
     * 用于更新广告数据
     */
    private fun updateList(data: MutableList<String>?, adapter: BizViewpageAdapter) {
//        adapter.updateAllList(data)
//        // 超过一张才需要轮播
//        if (data?.size > 1) {
//            // 默认展示第一张图
//            binding.viewPager.setCurrentItem(1, false)
//            // 初始化下方圆点指示器
//            binding.indicatorLayout.removeAllTabs()
//            for (i in 0 until data.size) {
//                val tab = binding.indicatorLayout.newTab()
//                tab.view.isEnabled = false
//                binding.indicatorLayout.addTab(tab)
//            }
//        } else {
//            binding.indicatorLayout.removeAllTabs()
//        }
    }

    /**
     * 设置视图是否自动滚动轮播
     * @param autoScroll Boolean 是否自动滚动
     */
    fun autoScroll(autoScroll: Boolean) {
        viewPagerHandler.removeCallbacks(viewPagerRunnable)
        if (autoScroll) {
            viewPagerHandler.postDelayed(viewPagerRunnable, DELAY_TIME_MS)
        }
    }


    override fun getItemCount(): Int {
        if (bannerData == null) {
            return 0
        }
        return bannerData?.size ?: 0
    }
}

