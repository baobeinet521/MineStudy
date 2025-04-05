package com.zd.study.biz.home.moreexplorefloor.model

import com.zd.study.biz.home.model.BaseData

class MoreExploreBizData: BaseData() {
    //瀑布流数据
    var cardList: MutableList<BizCardData>? = null
}

class BizCardData {
    //1单纯文字 2 消息轮播 3图片
    var cardContentType: Int? = null
    //卡片内item数据
    var dataList: MutableList<BizCardItemData>? = null
}

class BizCardItemData {
    var title: String? = null
    var cardBg: String? = null
    var infoList: MutableList<InfoData>? = null
}

class InfoData {
    var infoTitle: String? = null
    var infoLink: String? = null
}