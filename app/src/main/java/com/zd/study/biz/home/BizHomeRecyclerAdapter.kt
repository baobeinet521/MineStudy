package com.zd.study.biz.home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zd.study.R
import com.zd.study.adapter.CustomStaggeredLayoutManager
import com.zd.study.biz.home.imagefloor.model.ImageFloorData
import com.zd.study.biz.home.model.HomeData
import com.zd.study.biz.home.moreexplorefloor.adapter.MoreExploreRecyclerAdapter
import com.zd.study.biz.home.moreexplorefloor.model.MoreExploreBizData
import com.zd.study.biz.home.textfloor.model.TextFloorData


class BizHomeRecyclerAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    /*  private var mMoreExploreBizData: MoreExploreBizData? = null*/
    private var mHomeDatas: MutableList<HomeData>? = null
    private val mContext: Context? = context
    fun setListData(datas: MutableList<HomeData>?) {
        /* this.mMoreExploreBizData = datas*/
        this.mHomeDatas = datas
    }

    open class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.biz_floor_text)
    }

    open class BizMoreExploreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var moreExploreRecycler: RecyclerView = itemView.findViewById(R.id.more_explore_recycle)
        var deleteBtn: Button = itemView.findViewById(R.id.delete_btn)
    }

    open class BannerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageFilterView = itemView.findViewById(R.id.banner_floor_image)
        var title: TextView = itemView.findViewById(R.id.banner_floor_text)
    }

    override fun getItemViewType(position: Int): Int {
        return mHomeDatas?.get(position)?.floorType ?: 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View
        when (viewType) {
            1 -> {
                view = inflater.inflate(R.layout.biz_text_floor_layout, parent, false)
                return TextViewHolder(view)

            }

            2 -> {
                view = inflater.inflate(R.layout.more_explore_floor_layout, parent, false)
                return BizMoreExploreViewHolder(view)
            }

            else -> {
                view = inflater.inflate(R.layout.biz_banner_floor_layout, parent, false)
                return BannerViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        if (mHomeDatas == null) {
            return 0
        }
        return mHomeDatas?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val floorData = mHomeDatas?.get(position)?.floorData
        when (holder.itemViewType) {
            1 -> {
                val textHolder = holder as TextViewHolder
                textHolder.title.text = (floorData as TextFloorData).title ?: ""
            }

            2 -> {
                val moreExploreHolder = holder as BizMoreExploreViewHolder
                val staggeredGridLayoutManager = CustomStaggeredLayoutManager()
                val moreExploreAdapter = MoreExploreRecyclerAdapter(mContext)
                moreExploreAdapter.setListData((mHomeDatas?.get(position)?.floorData as? MoreExploreBizData))
                moreExploreHolder.moreExploreRecycler.apply {
                    layoutManager = staggeredGridLayoutManager
                    animation = null
                    adapter = moreExploreAdapter
                }
                moreExploreHolder.moreExploreRecycler.post {
                    val layoutParams = moreExploreHolder.moreExploreRecycler.layoutParams
                    layoutParams?.height = staggeredGridLayoutManager.getRealHeight()
                    Log.d(
                        "debug_rv",
                        "staggeredGridLayoutManager: height = ${staggeredGridLayoutManager.getRealHeight()}"
                    )
                }
            }
            else -> {
                val bannerHolder = holder as BannerViewHolder
                val imageFloorData = (mHomeDatas?.get(position)?.floorData as? ImageFloorData)
                mContext?.let { Glide.with(it).load(imageFloorData?.drawableId).into(bannerHolder.image) }
            }
        }

    }
}

