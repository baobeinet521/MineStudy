package com.zd.study.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.zd.study.R


class BizViewpageAdapter(context: Context?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val mContext: Context? = context
    private var scrollData: MutableList<String>? = null
    private var scrollFinalData: MutableList<String> = mutableListOf()
    fun setData(data: MutableList<String>?) {
        scrollData = data
    }

    fun updateAllList(data: MutableList<String>?) {
        scrollData = data
        dealScrollData()
    }

    private fun dealScrollData() {
        if (scrollData != null && scrollData?.size!! > 0) {
            scrollFinalData.clear()
            scrollData?.let { data ->
                data?.last()?.let { scrollFinalData.add(it) }
                scrollFinalData?.addAll(data)
                scrollFinalData.add(data[0])
            }
        }
    }

    open class ViewpageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageView: ImageFilterView = itemView.findViewById(R.id.biz_image)
        var title: TextView = itemView.findViewById(R.id.biz_image_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewpageViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.biz_viewpager_image_item_layout, parent, false)
        return ViewpageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewpageViewHolder).title.text = "正在轮播的数据是$position"
        mContext?.let { Glide.with(it).load(R.drawable.img).into(holder.imageView) }
    }

    override fun getItemCount(): Int {
        //实现无限轮播
//        return Integer.MAX_VALUE
        if (scrollFinalData == null) {
            return 0
        }
        return scrollFinalData?.size ?: 0
    }
}

