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
import com.zd.study.adapter.BizHomeRecyclerAdapter
import com.zd.study.data.BizData
import com.zd.study.data.BizFlowImages
import com.zd.study.databinding.FragmentTest1Binding

class FragmentTestOne : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    var binding: FragmentTest1Binding? = null
    var bizDatas: MutableList<BizData> = mutableListOf()
    val adpter by lazy { BizHomeRecyclerAdapter(context) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTest1Binding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViewData()
        bizDatas.clear()
        setData()
        adpter.setListData(bizDatas)
        val layoutManager = LinearLayoutManager(context)
        binding?.fragmentOneRecycle?.setLayoutManager(layoutManager);
        binding?.fragmentOneRecycle?.apply {
            animation = null
            adapter = adpter
        }
    }

    private fun setData(){
        // 构造一些数据
        for (i in  0 until 3){
            var data = BizData()
            data.type = 1
            data.text = "单纯文案$i"
            bizDatas.add(data)
        }
        var moreExplorData = BizData()
        moreExplorData.type = 2
        moreExplorData.text = "探索更多瀑布流"

        var scrollBizDatas: MutableList<BizFlowImages> = mutableListOf()
        for (i in  0 until 6){
            var data = BizFlowImages()
            data.text = "探索更多数据$i"

            var bannerData: MutableList<String> = mutableListOf()
            for (i in  0 until 5){
                bannerData.add("轮播数据位置$i")
            }
            data.scrollData = bannerData
            scrollBizDatas.add(data)
            moreExplorData.bannerData = scrollBizDatas
        }
        bizDatas.add(moreExplorData)
        for (i in  0 until 2){
            var data = BizData()
            data.type = 3
            data.text = "单纯图片$i"
            bizDatas.add(data)
        }

        var moreExplorData1 = BizData()
        moreExplorData1.type = 2
        moreExplorData1.text = "探索更多瀑布流2"
        for (i in  0 until 3){
            var data = BizFlowImages()
            data.text = "探索更多数据$i"

            var bannerData: MutableList<String> = mutableListOf()
            for (i in  0 until 5){
                bannerData.add("轮播数据位置$i")
            }
            data.scrollData = bannerData
            scrollBizDatas.add(data)
            moreExplorData1.bannerData = scrollBizDatas
        }
        bizDatas.add(moreExplorData1)


        var moreExplorData2 = BizData()
        moreExplorData2.type = 2
        moreExplorData2.text = "探索更多瀑布流3"
        for (i in  0 until 4){
            var data = BizFlowImages()
            data.text = "探索更多数据$i"

            var bannerData: MutableList<String> = mutableListOf()
            for (i in  0 until 5){
                bannerData.add("轮播数据位置$i")
            }
            data.scrollData = bannerData
            scrollBizDatas.add(data)
            moreExplorData2.bannerData = scrollBizDatas
        }
        bizDatas.add(moreExplorData2)
        Log.i("test", "数据的长度是  " + bizDatas.size)
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
        binding?.swipeLayout?.setColorSchemeResources(R.color.red, R.color.blue, R.color.purple_700);
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