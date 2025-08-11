package com.zd.study.biz.home.moreexplorefloor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.zd.study.R
import com.zd.study.autoscrollview.AutoScrollViewPager
import com.zd.study.biz.home.moreexplorefloor.model.BizCardData
import com.zd.study.biz.home.moreexplorefloor.model.BizCardItemData
import com.zd.study.biz.home.moreexplorefloor.model.MoreExploreBizData
import com.zd.study.utils.DisplayUtil


class MoreExploreRecyclerAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mMoreExploreBizData: MoreExploreBizData? = null
    private var mBizDatas: MutableList<BizCardData>? = null
    private val mContext: Context? = context
    fun setListData(datas: MoreExploreBizData?) {
        this.mMoreExploreBizData = datas
        this.mBizDatas = datas?.cardList
    }

    open class BizMoreExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val autoScrollRoot: CardView = itemView.findViewById(R.id.auto_scroll_root)
        val moreExploreAutoScroll: AutoScrollViewPager<BizViewpagerAutoScrollAdapter> = itemView.findViewById(R.id.auto_scroll_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.more_explore_view_pager_layout, parent, false)
        return BizMoreExploreViewHolder(view)
    }

    override fun getItemCount(): Int {
        if (mBizDatas == null) {
            return 0
        }
        return mBizDatas?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val layoutParams = (holder as BizMoreExploreViewHolder).autoScrollRoot.layoutParams
        mContext?.let {
            layoutParams.width =
                (DisplayUtil.getScreenWidth(mContext) - DisplayUtil.dpToPx(mContext, 39f)) / 2
            when (mBizDatas?.get(position)?.cardContentType) {
                1 -> {
                    layoutParams.height = DisplayUtil.dpToPx(mContext, 200f)
                }

                2 -> {
                    layoutParams.height = DisplayUtil.dpToPx(mContext, 220f)
                }

                3 -> {
                    layoutParams.height = DisplayUtil.dpToPx(mContext, 240f)
                }

            }
            holder.autoScrollRoot.layoutParams = layoutParams
        }
        val bizMoreExploreAdapter = BizViewpagerAutoScrollAdapter(mContext)
        val mScrollFinalData: MutableList<BizCardItemData> = mutableListOf()
        if (mBizDatas?.get(position) != null && mBizDatas?.get(position)?.dataList?.size!! > 1) {
            mBizDatas?.get(position)?.dataList?.let { data ->
                mScrollFinalData.add(data.last())
                mScrollFinalData.addAll(data)
                mScrollFinalData.add(data.first())
            }
        } else {
            mBizDatas?.get(position)?.dataList?.let { mScrollFinalData.addAll(it) }
        }
        bizMoreExploreAdapter.setData(mBizDatas?.get(position), mScrollFinalData)
        holder.moreExploreAutoScroll.setAdapter(bizMoreExploreAdapter)
        holder.moreExploreAutoScroll.isAutoPlay = false

    }
}

