package com.zd.study.biz.home.moreexplorefloor.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterViewFlipper
import android.widget.TextView
import androidx.constraintlayout.utils.widget.ImageFilterView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.zd.study.R
import com.zd.study.biz.home.moreexplorefloor.model.BizCardData
import com.zd.study.biz.home.moreexplorefloor.model.BizCardItemData
import com.zd.study.biz.home.moreexplorefloor.model.InfoData


class BizViewpagerAutoScrollAdapter(context: Context?) : PagerAdapter() {
    private val mContext: Context? = context
    private var mDataList: MutableList<BizCardItemData>? = null
    private var mBizData: BizCardData? = null
    fun setData(bizData: BizCardData?, scrollFinalData: MutableList<BizCardItemData>) {
        this.mBizData = bizData
        mDataList = scrollFinalData
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        when (mBizData?.cardContentType) {
            1 -> {
                //单纯文字
                val inflater: LayoutInflater = LayoutInflater.from(container.context)
                val view = inflater.inflate(R.layout.biz_viewpager_text_item_layout, null, false)
                val title: TextView = view.findViewById(R.id.biz_text)
                val bgView: ImageFilterView = view.findViewById(R.id.bg_item_text_view)
                title.text = "正在轮播单纯文字数据是$position"
                mContext?.let { Glide.with(it).load(mDataList?.get(position)?.cardBg).into(bgView) }
                container.addView(view)
                return view

            }

            2 -> {
                // 2 消息轮播
                val inflater: LayoutInflater = LayoutInflater.from(container.context)
                val view = inflater.inflate(R.layout.biz_viewpager_info_item_layout, null, false)
                val imageView: ImageFilterView = view.findViewById(R.id.biz_image)
                val viewFlipper1: AdapterViewFlipper = view.findViewById(R.id.view_flipper1)
                val viewFlipper2: AdapterViewFlipper = view.findViewById(R.id.view_flipper2)


                val bizCardItemData = mBizData?.dataList?.get(0)
                val infoList = bizCardItemData?.infoList
                val infoList1: MutableList<InfoData> = mutableListOf()
                val infoList2: MutableList<InfoData> = mutableListOf()
                if ((infoList?.size ?: 0) > 0) {
                    for (i in 0 until infoList?.size!!) {
                        if (i % 2 == 0) {
                            infoList1.add(infoList[i])
                        } else {
                            infoList2.add(infoList[i])
                        }
                    }
                }
                if (infoList1.size > 0) {
                    val adapter1 = ViewFlipperAdapter()
                    adapter1.setFlipperData(mContext, infoList1)
                    viewFlipper1.adapter = adapter1
                    viewFlipper1.flipInterval = 3000
                    viewFlipper1.startFlipping()
                }

                if (infoList2.size > 0) {
                    val adapter2 = ViewFlipperAdapter()
                    adapter2.setFlipperData(mContext, infoList2)
                    viewFlipper2.adapter = adapter2
                    viewFlipper2.flipInterval = 3000
                    viewFlipper2.startFlipping()
                }
                mContext?.let { Glide.with(it).load(bizCardItemData?.cardBg).into(imageView) }
                container.addView(view)
                return view

            }

            3 -> {
                // 3图片
                val inflater: LayoutInflater = LayoutInflater.from(container.context)
                val view = inflater.inflate(R.layout.biz_viewpager_image_item_layout, null, false)
                val imageView: ImageFilterView = view.findViewById(R.id.biz_image)
                val title: TextView = view.findViewById(R.id.biz_image_text)
                title.text = "正在轮播广告数据是$position"
                mContext?.let { Glide.with(it).load(mDataList?.get(position)?.cardBg).into(imageView) }
                container.addView(view)
                return view
            }

            else -> {
                val inflater: LayoutInflater = LayoutInflater.from(container.context)
                val view = inflater.inflate(R.layout.biz_viewpager_text_item_layout, null, false)
                container.addView(view)
                return view
            }
        }
    }

    override fun getCount(): Int {
        return mDataList?.size ?: 0
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        if (`object` is View) {
            container.removeView(`object`)
        }
    }
}

