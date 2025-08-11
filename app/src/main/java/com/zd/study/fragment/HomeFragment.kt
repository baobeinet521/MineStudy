package com.zd.study.fragment

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zd.study.R
import com.zd.study.biz.home.BizHomeRecyclerAdapter
import com.zd.study.biz.home.imagefloor.model.ImageFloorData
import com.zd.study.biz.home.model.HomeData
import com.zd.study.biz.home.moreexplorefloor.model.BizCardData
import com.zd.study.biz.home.moreexplorefloor.model.BizCardItemData
import com.zd.study.biz.home.moreexplorefloor.model.InfoData
import com.zd.study.biz.home.moreexplorefloor.model.MoreExploreBizData
import com.zd.study.biz.home.textfloor.model.TextFloorData
import com.zd.study.databinding.FragmentHomeLayoutBinding

/**
 * 首页
 */
class HomeFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var binding: FragmentHomeLayoutBinding? = null
    private val mHomeAdapter by lazy { BizHomeRecyclerAdapter(context) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewData()
        val bizData = createData()
        mHomeAdapter.setListData(bizData)
        val fragmentOneLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding?.fragmentOneRecycle?.apply {
            layoutManager = fragmentOneLayoutManager
            animation = null
            adapter = mHomeAdapter
        }
    }

    private fun createData(): MutableList<HomeData> {
        val homeList = mutableListOf<HomeData>()

        //单纯文字楼层
        val homeTextData = HomeData()
        homeTextData.floorType = 1
        val textData = TextFloorData()
        textData.title = "单纯文案楼层1"
        homeTextData.floorData = textData
        homeList.add(homeTextData)


        val homeTextData1 = HomeData()
        homeTextData1.floorType = 1
        val textData1 = TextFloorData()
        textData1.title = "单纯文案楼层2"
        homeTextData1.floorData = textData1
        homeList.add(homeTextData1)


        val homeTextData2 = HomeData()
        homeTextData2.floorType = 1
        val textData2 = TextFloorData()
        textData2.title = "单纯文案楼层3"
        homeTextData2.floorData = textData2
        homeList.add(homeTextData2)


        //轮播楼层数据
        val scrollData = HomeData()
        val moreExploreBizData = MoreExploreBizData()
        //瀑布流数据
        val cardList: MutableList<BizCardData> = mutableListOf()

        //单纯文案
        // 构造一些数据
        val titleBiz = BizCardData()
        titleBiz.cardContentType = 1
        val titleDataList: MutableList<BizCardItemData> = mutableListOf()
        for (i in 0 until 3) {
            val data = BizCardItemData()
            data.title = "单纯文案卡片$i"
            when (i) {
                0 -> {
                    data.cardBg =
                        "https://img1.baidu.com/it/u=23013213233361619010&fm=253&fmt=auto&app=138&f=JPEG?w=281&h=500"
                }

                1 -> {
                    data.cardBg =
                        "https://img2.baidu.com/it/u=2876597994,4283249971&fm=253&fmt=auto&app=138&f=JPEG?w=475&h=475"
                }

                2 -> {
                    data.cardBg =
                        "http://img1.baidu.com/it/u=2227905514,1724711623&fm=253&app=138&f=JPEG?w=800&h=1731"
                }
            }
            titleDataList.add(data)
        }
        titleBiz.dataList = titleDataList
        cardList.add(titleBiz)

        //轮播消息卡片数据构造
        val infoBiz = BizCardData()
        infoBiz.cardContentType = 2
        val infoDataList: MutableList<BizCardItemData> = mutableListOf()
        val data = BizCardItemData()
        data.title = "消息轮播卡片"
        data.cardBg =
            "https://img1.baidu.com/it/u=373367614,1747334261&fm=253&fmt=auto&app=138&f=JPEG?w=513&h=912"
        val infoList: MutableList<InfoData> = mutableListOf()
        for (i in 0 until 20) {
            val infoData = InfoData()
            infoData.infoTitle = "今天天气不错$i"
            infoList.add(infoData)
        }
        data.infoList = infoList
        infoDataList.add(data)
        infoBiz.dataList = infoDataList
        cardList.add(infoBiz)

        //广告卡片数据构造
        val advBiz = BizCardData()
        advBiz.cardContentType = 3
        val advDataList: MutableList<BizCardItemData> = mutableListOf()
        for (i in 0 until 3) {
            val advCardItemData = BizCardItemData()
            when (i) {
                0 -> {
                    advCardItemData.cardBg =
                        "https://db.workercn.cn/media/2025/01/01/CD428CD5FF7B4835B1DC8C49AFCD1A9F/thumb.jpg"
                }

                1 -> {
                    advCardItemData.cardBg =
                        "https://b0.bdstatic.com/ugc/6Xe41IbOr_TSDi0VFhIcNw9c29b9f4aa0416703af1919954c18a03.jpg"
                }

                2 -> {
                    advCardItemData.cardBg =
                        "https://b0.bdstatic.com/ugc/6Xe41IbOr_TSDi0VFhIcNw320d11bc4e8cff03188e9e1b179ffa1e.jpg"
                }
            }
            advCardItemData.title = "广告$i"
            advDataList.add(advCardItemData)
        }
        advBiz.dataList = advDataList
        advBiz.isShow = false
        cardList.add(advBiz)

        moreExploreBizData.cardList = cardList
        scrollData.floorType = 2
        scrollData.floorData = moreExploreBizData
        homeList.add(scrollData)


        //图片楼层
        val homeImageData1 = HomeData()
        homeImageData1.floorType = 3
        val imageData1 = ImageFloorData()
        imageData1.drawableId = R.drawable.test
        homeImageData1.floorData = imageData1
        homeList.add(homeImageData1)


        val homeImageData2 = HomeData()
        homeImageData2.floorType = 3
        val imageData2 = ImageFloorData()
        imageData2.drawableId = R.drawable.test1
        homeImageData2.floorData = imageData2
        homeList.add(homeImageData2)

        return homeList
    }

    private fun bindViewData() {
        //设置下拉刷新图标的大小 只支持两种： DEFAULT  和 LARGE
        binding?.swipeLayout?.setSize(CircularProgressDrawable.LARGE);
        //设置刷新图标的颜色，在手指下滑刷新时使用第一个颜色，在刷新中，会一个个颜色进行切换 这里是传入 int... colors
        binding?.swipeLayout?.setColorSchemeColors(
            Color.BLACK,
            Color.GREEN,
            Color.RED,
            Color.YELLOW,
            Color.BLUE
        );
        //设置刷新图标的颜色, 在手指下滑刷新时使用第一个颜色，和 setColorSchemeColors 传递的参数不一样，这里是传入int colorResIds
        binding?.swipeLayout?.setColorSchemeResources(
            R.color.red,
            R.color.blue,
            R.color.purple_700
        );
        //设置刷新图标的背景颜色
        binding?.swipeLayout?.setProgressBackgroundColorSchemeResource(R.color.teal_200);
        //设置动画样式下拉的起始点和结束点，scale设置是否需要放大或者缩小动画
        // 第一个参数：默认为false，设置为true，下拉过程中刷新图标就会从小变大
        // 第二个参数：起始位置，刷新图标距离顶部像素px
        // 第三个参数：结束位置，刷新图标距离顶部像素px
        //swipeRefreshLayout.setProgressViewOffset(false, 100, 200);
        //设置动画样式下拉的结束点  scale设置是否需要放大或者缩小动画
        // 第二个参数：结束位置，刷新图标距离顶部像素px
        //swipeRefreshLayout.setProgressViewEndTarget(false, 500);
        //设置可以将刷新指示器拉出其静止位置的距离（以像素为单位）
        //swipeRefreshLayout.setSlingshotDistance(600);
        //false:禁用手势下拉刷新动画
        //swipeRefreshLayout.setEnabled(false);
        //设置监听器,需要重写onRefresh()方法
        binding?.swipeLayout?.setOnRefreshListener(this);
    }

    override fun onRefresh() {
        Log.e("test", "===是否==正在刷新中====" + binding?.swipeLayout?.isRefreshing)
        binding?.swipeLayout?.postDelayed({
            //注意事项：当完成数据更新后一定要调用 setRefreshing(false)，不然刷新图标会一直转圈，不会消失
            binding?.swipeLayout?.isRefreshing = false
        }, 3000)
    }

    override fun onResume() {
        super.onResume()
    }
}