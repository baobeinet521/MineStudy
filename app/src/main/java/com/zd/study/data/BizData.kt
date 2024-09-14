package com.zd.study.data

import android.graphics.drawable.Drawable
class BizData{
    var type: Int? = null
    var text: String? = null//1单纯文字 2 轮播 3图片
    var dra: Drawable? = null
    //瀑布流数据
    var bannerData: MutableList<BizFlowImages>? = null
}

class BizFlowImages{
    var text: String? = null
    //轮播数据
    var scrollData: MutableList<String>? = null
    var type: String? = null
}