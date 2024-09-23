package com.zd.study.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.zd.study.R


class BizViewpageAutoAdapter(context: Context?) : PagerAdapter() {
    private val mContext: Context? = context
    private var scrollData: MutableList<String>? = null
    private var scrollFinalData: MutableList<String> = mutableListOf()
    fun setData(data: MutableList<String>?) {
        scrollData = data
        if (scrollData != null && scrollData?.size!! > 0) {
            scrollFinalData.clear()
            scrollData?.let { data ->
                data?.last()?.let { scrollFinalData.add(it) }
                scrollFinalData?.addAll(data)
                scrollFinalData.add(data[0])
            }
        }
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater: LayoutInflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.biz_image_item_layout, null, false)
        var imageView: ImageFilterView = view.findViewById(R.id.biz_image)
        var title: TextView = view.findViewById(R.id.biz_image_text)
        title.text = "正在轮播的数据是$position"
        mContext?.let { Glide.with(it).load(R.drawable.img).into(imageView) }


        container.addView(view)
        return view
    }

    override fun getCount(): Int {
        if (scrollFinalData == null) {
            return 0
        }
        return scrollFinalData?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if(`object` is View){
            container.removeView(`object`)
        }
    }
}

