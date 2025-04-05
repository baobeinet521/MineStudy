package com.zd.study.biz.home.moreexplorefloor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.zd.study.R
import com.zd.study.biz.home.moreexplorefloor.model.InfoData

class ViewFlipperAdapter : BaseAdapter() {
    private var mInfoList: MutableList<InfoData>? = null
    private var mContext: Context? = null

    fun setFlipperData(context: Context?, infoList: MutableList<InfoData>?) {
        this.mContext = context
        this.mInfoList = infoList
    }

    override fun getCount(): Int {
        return mInfoList?.size ?: 0
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(mContext).inflate(R.layout.item_info_layout, parent, false)
        val textView = view?.findViewById<TextView?>(R.id.info_text)
        textView?.text = mInfoList?.get(position)?.infoTitle ?:""
        return view
    }
}