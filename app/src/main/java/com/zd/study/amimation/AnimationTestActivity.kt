package com.zd.study.amimation

import android.animation.Animator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.zd.study.R
import com.zd.study.data.ReceiveListQueryDTO
import java.lang.Boolean
import java.text.DecimalFormat
import kotlin.String

class AnimationTestActivity: AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.animation_test_layout)
        var lottieAnimationView = findViewById<LottieAnimationView>(R.id.animationView)

        var lottieAnimationView1 = findViewById<LottieAnimationView>(R.id.image_gif_view)
//        lottieAnimationView.setAnimation("smallcar.json")
//        var url = "http://172.20.10.6/lottie/488f373d-3bcb-4d5b-877d-b6ecdfa9e729.json"
//        var url = "http://172.20.10.6/lottie/data.zip"
//        var url = "http://172.20.10.6/lottie/new.zip"


//        http://192.168.100.115/Day.lottie
//http://192.168.100.115/nighttoday.lottie
//http://192.168.100.115/zhuti.lottie
        var url = "http://192.168.100.115/Day.lottie"

        lottieAnimationView.disableExtraScaleModeInFitXY()

//        lottieAnimationView.setAnimationFromUrl(url, url)

        lottieAnimationView.addAnimatorListener(object: Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator) {
                Log.e("ceshi", "onAnimationStart动画开始了======")
            }

            override fun onAnimationEnd(animation: Animator) {
                Log.e("ceshi", "onAnimationEnd动画结束了======")
            }

            override fun onAnimationCancel(animation: Animator) {
                Log.e("ceshi", "onAnimationCancel动画取消了======")
            }

            override fun onAnimationRepeat(animation: Animator) {
                Log.e("ceshi", "onAnimationRepeat动画重复======")
            }

        })



        val packageName = packageName
        Log.e("ceshi", "packageName   = $packageName")


//        var resources:Resources  = this.resources
//        var drawable = resources.getDrawable(R.drawable.test1)
//
//        val bitmapDrawable: BitmapDrawable = drawable as BitmapDrawable
//        val bitmap: Bitmap = bitmapDrawable.bitmap
//        lottieAnimationView.setImageBitmap(bitmap)
//        lottieAnimationView.repeatMode


//        var drawable1 = resources.getDrawable(R.drawable.test)
//        val bitmapDrawable1: BitmapDrawable = drawable1 as BitmapDrawable
//        val bitmap1: Bitmap = bitmapDrawable1.bitmap

//        lottieAnimationView1.setImageBitmap(bitmap1)

//        lottieAnimationView1.setImageResource(R.drawable.test)

        // 使用Glide播放gif图片
//        var mIvPhoto =  findViewById<ImageView>(R.id.image_view)
//        Glide.with(this)
//            .load(R.drawable.test)
//            .asGif()
//            .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//            .into(mIvPhoto)

        val receiveListQueryDTO = ReceiveListQueryDTO.Builder()
            .isAll(Boolean.TRUE)
            .itemCode("asdasd")
            .page(1)
            .pageSize(123)
            .build()

//        val code = receiveListQueryDTO.itemCode
//        Log.d("ceshi", " code ====    ")
//        test()
    }

    fun test() {
        var display = DisplayMetrics()
//将当前窗口的一些信息放在DisplayMetrics类中，
        this.getWindowManager().getDefaultDisplay().getMetrics(display);


        var density = display.density


        val decimalFormat = DecimalFormat("0.00")

        var floata = 2.0f
        val divide: String = decimalFormat.format(0.75f / 3.0f)
//输出结果是 dens: density is  1.0
        Log.e("dens", "density is  " + density + " a  " + divide);

//获取屏幕像素密度
        var densityDpi = display.densityDpi
//输出结果是 160
        Log.e("dens", "densityDpi is  " + densityDpi);


//获取屏幕的高度 结果单位 px
        var heightPixels = display.heightPixels
//输出结果是 heightPixels is  480
        Log.e("dens", "heightPixels is  " + heightPixels);


//获取屏幕的宽度 结果单位 px
        var widthPixels = display.widthPixels
//输出结果是 widthPixels is  320
        Log.e("dens", "widthPixels is  " + widthPixels);


//获取缩放比例
        var scaledDensity = display.scaledDensity
//输出结果是 scaledDensity is  1.0
        Log.e("dens", "scaledDensity is  " + scaledDensity);

        var xdpi = display.xdpi
        var ydpi = display.ydpi

//输出结果是 xdpi is  160.0  ydpi 160.0
        Log.e("dens", "xdpi is  " + xdpi + "  ydpi " + ydpi);
    }
}