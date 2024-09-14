package com.zd.study.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.zd.study.R
import com.zd.study.data.BizData


class BizHomeRecyclerAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var mBizDatas: MutableList<BizData>? = null
    val mContext: Context? = context
    fun setListData(datas: MutableList<BizData>?) {
        this.mBizDatas = datas
    }

    open class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.biz_text)
    }

    open class BizMoreExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moreExploreRecycle: RecyclerView = itemView.findViewById(R.id.scroll_recycle)
    }

    open class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageFilterView = itemView.findViewById(R.id.biz_image)
        var title: TextView = itemView.findViewById(R.id.biz_image_text)
    }

    override fun getItemViewType(position: Int): Int {
        return mBizDatas?.get(position)?.type ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        var view: View
        when (viewType) {
            1 -> {
                view = inflater.inflate(R.layout.biz_text_item_layout, parent, false)
                return TextViewHolder(view)

            }

            2 -> {
                view = inflater.inflate(R.layout.more_explore_recycle_layout, parent, false)
                return BizMoreExploreViewHolder(view)
            }

            else -> {
                view = inflater.inflate(R.layout.biz_banner_item_layout, parent, false)
                return BannerViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        if (mBizDatas == null) {
            return 0
        }
        return mBizDatas?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                var holer = holder as TextViewHolder
                holer.title.text = mBizDatas?.get(position)?.text ?: ""
            }

            2 -> {
                var holer = holder as BizMoreExploreViewHolder
                var bizMoreExploreAdapter = BizMoreExploreAdapter(mContext)
                bizMoreExploreAdapter.setData(mBizDatas?.get(position)?.bannerData)
                val staggeredGridLayoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                holer.moreExploreRecycle.apply {
                    layoutManager = staggeredGridLayoutManager
                    adapter = bizMoreExploreAdapter
                }
            }

            else -> {
                var holer = holder as BannerViewHolder
                holer.title.text = mBizDatas?.get(position)?.text ?: ""
                mContext?.let { Glide.with(it).load(R.drawable.upgrade_bg1).into(holder.image) }
            }
        }
    }
}

